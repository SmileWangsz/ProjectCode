package src.com.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import src.com.R;
import src.com.util.HttpRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String userName, passWord;
    private Button btn_login, btn_register;
    private EditText login_phone,login_password;
    private boolean prompt = false;
    private boolean checkUpResult = true;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("请稍等");
        mDialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回按键取消
        mDialog.setCancelable(false);
    }

    private void init() {
        login_phone = (EditText) findViewById(R.id.login_phone);
        login_password = (EditText) findViewById(R.id.login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_login)
        {
            prompt = true;
            if(checkUpResult == false)
            {
                checkUpResult = true;
            }
            getData();//获取控件数据方法。
            doRegister();
        }
        if(v.getId() == R.id.btn_register)
        {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void doRegister() {
        if (checkUpResult)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/LoginStudentServlet";
                    String param = "userName="+userName+"&passWord="+passWord;
                    String result;
                    result = HttpRequest.sendPost(url,param);
                    if(result != null)
                    {
                        if(result.equals("success"))
                        {
                            handler.sendEmptyMessage(1);
                        }
                        if (result.equals("failure"))
                        {
                            handler.sendEmptyMessage(0);
                        }
                    }
                    else
                        handler.sendEmptyMessage(-1);
                }
            }).start();
        }
    }

    //这是一个消息处理机制类，因为在run()主线程中不能额外处理另外一个线程，所以
    //把要处理的功能通过handler消息机制，单独拿出来处理。
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("userId",userName);
                startActivity(intent);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                login_phone.setText("");
                login_password.setText("");
            }
            if (msg.what == 2)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };//这里结束

    private void getData() {
        userName = login_phone.getText().toString();
        passWord = login_password.getText().toString();

        if(userName.equals("") && prompt)
        {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
        if(passWord.equals("") && prompt)
        {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
    }
}
