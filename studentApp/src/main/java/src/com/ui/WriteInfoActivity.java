package src.com.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import src.com.R;
import src.com.util.HttpRequest;
import src.com.util.SchoolInfoFlag;

public class WriteInfoActivity extends Activity implements View.OnClickListener{

    private static String levelName, majorName, className, timeName, userId;
    private String userName, teleNumber, eMail, sex;
    private static int levelId = 0, majorId = 0, classId = 0, timeId = 0;
    private EditText write_name, write_phone, write_email;
    private TextView xibu, zhuanye, banji, time;
    private RadioGroup radioGroup;
    private Button btn_back, btn_ok;
    private int flag = 0;
    private SchoolInfoFlag FLAT_CODE;
    private boolean prompt = false;
    private boolean checkUpResult = true;

    private void init() {
        write_name = (EditText) findViewById(R.id.write_name);
        xibu = (TextView) findViewById(R.id.xibu);
        zhuanye = (TextView) findViewById(R.id.zhuanye);
        banji = (TextView) findViewById(R.id.banji);
        time = (TextView) findViewById(R.id.time);
        write_phone = (EditText) findViewById(R.id.write_phone);
        write_email = (EditText) findViewById(R.id.write_email);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_ok = (Button) findViewById(R.id.btn_ok);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_info);
        init();
        if (FLAT_CODE.userIdCode == 1)
        {
            Intent intent = this.getIntent();
            userId = intent.getStringExtra("userId");
        }
        xibu.setOnClickListener(this);
        zhuanye.setOnClickListener(this);
        banji.setOnClickListener(this);
        time.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)WriteInfoActivity.this.findViewById(radioButtonId);
                sex = rb.getText().toString();
            }
        });

        //获取listview返回的id和名称。
        Intent intent1 = this.getIntent();

        if (FLAT_CODE.levelCode==1)
        {
            levelName = intent1.getStringExtra("levelName");
            levelId = Integer.parseInt(intent1.getStringExtra("levelId"));
            xibu.setText(levelName);
        }
        if (FLAT_CODE.majorCode==1)
        {
            majorName = intent1.getStringExtra("MajorName");
            majorId = Integer.parseInt(intent1.getStringExtra("MajorId"));
            xibu.setText(levelName);
            zhuanye.setText(majorName);
        }
        if (FLAT_CODE.classCode==1)
        {
            className = intent1.getStringExtra("className");
            classId = Integer.parseInt(intent1.getStringExtra("classId"));
            xibu.setText(levelName);
            zhuanye.setText(majorName);
            banji.setText(className);
        }
        if (FLAT_CODE.time==1)
        {
            timeName = intent1.getStringExtra("timeName");
            timeId = Integer.parseInt(intent1.getStringExtra("timeId"));
            xibu.setText(levelName);
            zhuanye.setText(majorName);
            banji.setText(className);
            time.setText(timeName);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.xibu)
        {
            flag = 1;
            Intent intent = new Intent(WriteInfoActivity.this,GetSchoolInfoActivity.class);
            intent.putExtra("flag",flag);
            startActivity(intent);
        }
        if (v.getId() == R.id.zhuanye)
        {
            flag = 2;
            Intent intent = new Intent(WriteInfoActivity.this,GetSchoolInfoActivity.class);
            intent.putExtra("flag",flag);
            intent.putExtra("id",levelId);
            startActivity(intent);
        }
        if (v.getId() == R.id.banji)
        {
            flag = 3;
            Intent intent = new Intent(WriteInfoActivity.this,GetSchoolInfoActivity.class);
            intent.putExtra("flag",flag);
            intent.putExtra("id",majorId);
            startActivity(intent);
        }
        if(v.getId() == R.id.time)
        {
            flag = 4;
            Intent intent = new Intent(WriteInfoActivity.this,GetSchoolInfoActivity.class);
            intent.putExtra("flag",flag);
            intent.putExtra("id",0);
            startActivity(intent);
        }
        if(v.getId() == R.id.btn_ok)
        {
            prompt = true;
            if(checkUpResult == false)
            {
                checkUpResult = true;
            }
            getData();
            doWriteInfo();
        }
        if(v.getId() == R.id.btn_back)
        {
            Intent intent = new Intent(WriteInfoActivity.this,Main2Activity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
        }
    }

    private void getData() {
        userName = write_name.getText().toString();
        teleNumber = write_phone.getText().toString();
        eMail = write_email.getText().toString();

        if (userName.equals("") && prompt) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
        if (levelName.equals("") && prompt) {
            Toast.makeText(this, "请选择系部", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
        if (majorName.equals("") && prompt) {
            Toast.makeText(this, "请选择专业", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
        if (className.equals("") && prompt) {
            Toast.makeText(this, "请选择班级", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
        if (time.equals("") && prompt) {
            Toast.makeText(this, "请选择入学时间", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
        if (teleNumber.equals("") && prompt) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
    }

    private void doWriteInfo() {
        if (checkUpResult)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url ="http://192.168.0.105:8888/SchoolProject/com/androidservlet/WriteStudentInfoServlet";
                    String param = "userID="+userId+"&userName="+userName+"&userSex="+sex+"&userLevel="+levelName
                            +"&userMajor="+majorName+"&userClass="+className+"&userTime="+timeName+"&userTele="+teleNumber
                            +"&userEmail="+eMail;
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
                        Toast.makeText(WriteInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                Intent intent = new Intent(WriteInfoActivity.this,Main2Activity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(WriteInfoActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(WriteInfoActivity.this, "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };
}
