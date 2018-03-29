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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.com.R;
import src.com.util.HttpRequest;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private String id, phone, password;
    private EditText userID;
    private EditText userPhone;
    private EditText userPassword;
    private Button registerOk;
    private Button back;
    private ProgressDialog mDialog;
    private boolean prompt = false;
    private boolean checkUpResult = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();//初始化控件方法。

        registerOk.setOnClickListener(this);
        back.setOnClickListener(this);

        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("请稍等");
        mDialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回按键取消
        mDialog.setCancelable(false);
    }

    private void getData() {
        id = userID.getText().toString();
        phone = userPhone.getText().toString();
        password = userPassword.getText().toString();

        if (id.equals("") && prompt) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }

        if (phone.equals("") && prompt) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }

        if (!checkPhoneNumber(phone) && prompt) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }

        if (password.equals("") && prompt) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
    }
    //初始化控件
    private void init()
    {
        userID = (EditText) findViewById(R.id.register_id);
        userPhone = (EditText) findViewById(R.id.register_phone);
        userPassword = (EditText) findViewById(R.id.register_password);
        registerOk = (Button) findViewById(R.id.btn_ok);
        back = (Button)findViewById(R.id.btn_back);
    }

    //这是一个检查手机号是否正确的公共方法，本来要封装到类里面，暂时就放在这里。
    public static boolean checkPhoneNumber(String mobiles) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(mobiles);
        b = m.matches();
        return b;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back)
        {
            finish();
        }
        if (v.getId() == R.id.btn_ok)
        {
            prompt = true;
            if(checkUpResult == false)
            {
                checkUpResult = true;
            }
            getData();//获取控件数据方法。
            doRegister();
        }
    }

    private void doRegister() {
        if(checkUpResult)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/registerStudentServlet";
                    String param = "id="+id+"&phone="+phone+"&password="+password;
                    String result;
                    result = HttpRequest.sendPost(url,param);
                    if(result.equals("success"))
                    {
                        handler.sendEmptyMessage(1);
                    }
                    else if(result.equals("failure"))
                    {
                        handler.sendEmptyMessage(0);
                    }
                    else
                        handler.sendEmptyMessage(2);
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
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                Intent intent = new Intent(RegisterActivity.this,WriteInfoActivity.class);
                intent.putExtra("userId",id);
                startActivity(intent);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
            if (msg.what == 2)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };

}
