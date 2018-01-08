package src.com.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import src.com.R;
import src.com.adapter.LoadStudentCourse;
import src.com.entity.courseQuestion;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;

public class DoExerciseActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_back, submit;
    private TextView deleteExercise, exerciseContent, A, B, C, D, rightKey;
    private RadioGroup radioGroup;
    private LinearLayout exerciseLinearLayout;
    private String newCourseId, studentCourseId, userId;
    private String answer = null;
    private courseQuestion cq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exercise);
        init();
        btn_back.setOnClickListener(this);
        deleteExercise.setOnClickListener(this);
        submit.setOnClickListener(this);

        Intent intent = this.getIntent();
        newCourseId = intent.getStringExtra("newCourseId");
        studentCourseId = intent.getStringExtra("studentCourseId");
        userId = intent.getStringExtra("userId");

        exerciseLinearLayout.setVisibility(View.INVISIBLE);
        getData();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)DoExerciseActivity.this.findViewById(radioButtonId);
                answer = rb.getText().toString();
            }
        });
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/ShowQuestionServlet";
                String param = "newCourseId="+newCourseId;
                String result;
                result = HttpRequest.sendPost(url,param);
                if(result !=null)
                {
                    if(result.equals("failure"))
                    {
                        handler.sendEmptyMessage(0);
                    }else
                    {
                        cq = ParseJson.parseCourseQuestion(result);
                        if (cq !=null)
                        {
                            handler.sendEmptyMessage(1);
                        }
                    }
                }else
                    handler.sendEmptyMessage(-1);
            }
        }).start();
    }

    //这是一个消息处理机制类，因为在run()主线程中不能额外处理另外一个线程，所以
    //把要处理的功能通过handler消息机制，单独拿出来处理。
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                exerciseLinearLayout.setVisibility(View.VISIBLE);
                rightKey.setVisibility(View.INVISIBLE);
                exerciseContent.setText(cq.getQuestionContent());
                A.setText("A: "+cq.getKeyA());
                B.setText("B: "+cq.getKeyB());
                C.setText("C: "+cq.getKeyC());
                D.setText("D: "+cq.getKeyD());
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(DoExerciseActivity.this, "暂时没有习题，请等待老师发布", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DoExerciseActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
            if (msg.what == 3)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(DoExerciseActivity.this, "此次成绩已经上传，答题结束", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
            if (msg.what == 4)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(DoExerciseActivity.this, "答题超时，此次成绩不被记录，请注意时间。", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
            if (msg.what == 5)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(DoExerciseActivity.this, "您已提交过答案", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
            if (msg.what == -1)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(DoExerciseActivity.this, "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };//这里结束

    private void init() {
        btn_back = (Button) findViewById(R.id.btn_back);
        deleteExercise = (TextView) findViewById(R.id.deleteExercise);
        submit = (Button) findViewById(R.id.submit);
        exerciseContent = (TextView) findViewById(R.id.exerciseContent);
        A = (TextView) findViewById(R.id.A);
        B = (TextView) findViewById(R.id.B);
        C = (TextView) findViewById(R.id.C);
        D = (TextView) findViewById(R.id.D);
        rightKey = (TextView) findViewById(R.id.rightKey);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        exerciseLinearLayout = (LinearLayout) findViewById(R.id.exerciseLinearLayout);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_back)
        {
            finish();
        }
        if(v.getId() == R.id.deleteExercise)
        {
            deleteCourse();
            finish();
        }
        if(v.getId() == R.id.submit)
        {
            rightKey.setText("正确答案："+cq.getRightKey());
            rightKey.setVisibility(View.VISIBLE);
            submitStudentRecoder();
            submit.setEnabled(false);
        }
    }

    private void submitStudentRecoder() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/SubmitStudentRecoderServlet";
                String param = "questionId="+cq.getQuestionID()+"&answer="+answer+"&studentId="+userId;
                String result;
                result = HttpRequest.sendPost(url, param);
                if(result !=null)
                {
                    if(result.equals("success"))
                    {
                        handler.sendEmptyMessage(3);
                    }
                    if(result.equals("redo"))
                    {
                        handler.sendEmptyMessage(5);
                    }
                    else
                        handler.sendEmptyMessage(4);
                }
                else
                    handler.sendEmptyMessage(-1);
            }
        }).start();
    }

    private void deleteCourse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/UpdateCourseTypeServlet";
                String param = "studentCourseId="+studentCourseId;

                String result;
                result = HttpRequest.sendPost(url,param);
                if(result!=null)
                {
                    if(result.equals("success"))
                    {
                        handler.sendEmptyMessage(2);
                    }
                }else
                    handler.sendEmptyMessage(-1);
            }
        }).start();
    }
}
