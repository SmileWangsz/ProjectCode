package src.com.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import src.com.entity.ResultRecord;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;

public class DoExercise extends AppCompatActivity implements View.OnClickListener{

    private TextView exerciseContent,questionA, questionB, questionC, questionD, text2, text4, text5, text6;
    private EditText time, number;//获取控件
    private Button submit,btn_back, submitOver;
    private String time1, peopleNumber;//获取EditText控件里面的值
    //获取传过来的数据字段
    private String questionContent, A, B, C, D, rightKey;
    private int questionID;
    //用于倒计时字段
    private int recLen = -1, flag = 0;
    private boolean prompt = false;
    private boolean checkUpResult = true;
    private RelativeLayout releaseResult;
    private ResultRecord rr;
    private String releaseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exercise);
        //绑定界面控件
        exerciseContent = (TextView) findViewById(R.id.exerciseContent);
        questionA = (TextView) findViewById(R.id.A);
        questionB = (TextView) findViewById(R.id.B);
        questionC = (TextView) findViewById(R.id.C);
        questionD = (TextView) findViewById(R.id.D);
        time = (EditText) findViewById(R.id.time);
        number = (EditText) findViewById(R.id.number);
        submit = (Button) findViewById(R.id.submit);
        submitOver = (Button) findViewById(R.id.submitOver);
        submitOver.setEnabled(false);
        btn_back = (Button) findViewById(R.id.btn_back);
        text2 = (TextView) findViewById(R.id.text2);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        releaseResult = (RelativeLayout) findViewById(R.id.releaseResult);
        releaseResult.setVisibility(View.INVISIBLE);
        Intent intent = this.getIntent();
        //获取传过来的数据
        questionID = Integer.parseInt(intent.getStringExtra("questionID"));
        questionContent = intent.getStringExtra("questionContent");
        A = intent.getStringExtra("A");
        B = intent.getStringExtra("B");
        C = intent.getStringExtra("C");
        D = intent.getStringExtra("D");
        rightKey = intent.getStringExtra("rightKey");
        //把获取的数据显示在界面上
        exerciseContent.setText(questionContent);
        questionA.setText("A："+A);
        questionB.setText("B："+B);
        questionC.setText("C："+C);
        questionD.setText("D："+D);

        submit.setOnClickListener(this);
        submitOver.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    private void getData() {
        //获取数据
        time1 = time.getText().toString();
        peopleNumber = number.getText().toString();

        if(time1.equals("") && prompt)
        {
            Toast.makeText(this, "请填写时间", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
        if(peopleNumber.equals("") && prompt)
        {
            Toast.makeText(this, "请填写人数", Toast.LENGTH_SHORT).show();
            prompt = false;
            checkUpResult = false;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submit)
        {
            prompt = true;
            flag = 1;
            if(checkUpResult == false)
            {
                checkUpResult = true;
            }
            releaseResult.setVisibility(View.INVISIBLE);
            getData();//获取控件数据方法。
            releaseQuestion();
        }
        if(v.getId() == R.id.btn_back)
        {
            if (flag == 1)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DoExercise.this);
                builder.setTitle("温馨提示");
                builder.setMessage("您的发布还未结束，你确定要放弃此次发布吗？");
                builder.setIcon(R.mipmap.message);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        releaseOver();
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
            else
                finish();
        }
        if(v.getId() == R.id.submitOver)
        {
            releaseOver();//修改习题表中习题的状态。从发布状态改成未发布状态，还有发布ID改成0.
            showResult();
        }
    }

    private void showResult() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/RecordCountServlet";
                String param = "rightKey="+rightKey+"&peopleNumber="+peopleNumber+"&releaseId="+releaseId;
                String result = null;
                result = HttpRequest.sendPost(url,param);
                if(result != null)
                {
                    rr = ParseJson.parseResultRecord(result);
                    handler.sendEmptyMessage(3);
                }
                else
                    handler.sendEmptyMessage(-1);
            }
        }).start();
    }

    private void releaseOver() {
        flag = 0;//设置结束状态。
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/QuestionReleaseOverServlet";
                String param = "questionId="+questionID;
                String result;
                result = HttpRequest.sendPost(url,param);
                if(result!=null)
                {
                    if(result.equals("success"))
                    {
                        handler.sendEmptyMessage(2);
                    }
                    else
                    {
                        handler.sendEmptyMessage(0);
                    }
                }else
                    handler.sendEmptyMessage(-1);
            }
        }).start();
    }

    private void releaseQuestion() {
        if (checkUpResult)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/QuestionReleaseServlet";
                    String param = "questionId="+questionID+"&peopleNumber="+peopleNumber;
                    String result;
                    result = HttpRequest.sendPost(url,param);
                    if(result!=null)
                    {
                        releaseId = result;
                        handler.sendEmptyMessage(1);
                    }else
                        handler.sendEmptyMessage(-1);
                }
            }).start();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(DoExercise.this, "发布成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                recLen = Integer.parseInt(time1)*60;
                handler.postDelayed(runnable,1000);
                submit.setEnabled(false);
                submitOver.setEnabled(true);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(DoExercise.this, "发布失败，请调整重新发布", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DoExercise.this, "通信失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DoExercise.this, "发布结束", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                handler.removeCallbacks(runnable);
                submit.setEnabled(true);
                submitOver.setEnabled(false);
                submitOver.setText("结束");
            }
            if (msg.what == 3)
            {
                releaseResult.setVisibility(View.VISIBLE);
                text2.setText(peopleNumber);
                text4.setText(rr.getSumAll()+"");
                text5.setText("正确率："+rr.getAccuracy()+"%");
                text6.setText("A:"+rr.getSumA()+"人 B:"+rr.getSumB()+"人 C:"+rr.getSumC()+"人 D:"+rr.getSumD()+"人");
            }
        }
    };//这里结束

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen--;
            if (recLen >= 0)
            {
                submitOver.setText("倒计时"+recLen+"秒");
                handler.postDelayed(this,1000);
            }
            else
            {
                releaseOver();
                showResult();
            }
        }
    };
}