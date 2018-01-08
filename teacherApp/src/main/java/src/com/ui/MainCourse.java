package src.com.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import src.com.adapter.LoadCourseInfo;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;

public class MainCourse extends AppCompatActivity implements View.OnClickListener{


    private ListView courseListView;
    private Button btn_back;
    private List<Map<String, String>> list;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course);

        courseListView = (ListView) findViewById(R.id.courseListView);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        //接收学生ID，这个ID应该一直被使用。
        Intent intent = this.getIntent();
        userId = intent.getStringExtra("userId");

        getData();
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainCourse.this,MainQuestion.class);
                intent.putExtra("newCourseId", list.get(position).get("newCourseId"));
                intent.putExtra("courseName",list.get(position).get("courseName"));
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/GetCourseInfoServlet";
                String param = "flag=3&userId="+userId;
                String result;
                result = HttpRequest.sendPost(url,param);
                if(result!=null)
                {
                    list = ParseJson.parseTeacherCourse(result);
                    if(list !=null && list.size()>0)
                    {
                        handler.sendEmptyMessage(1);
                    }else
                    {
                        handler.sendEmptyMessage(0);
                    }
                }else
                {
                    handler.sendEmptyMessage(-1);
                }
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
                LoadCourseInfo adapter = new LoadCourseInfo(list,MainCourse.this);
                courseListView.setAdapter(adapter);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(MainCourse.this, "暂时没有课程，请在web端编辑课程后再访问此APP", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainCourse.this, "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };//这里结束

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_back)
        {
            Intent intent = new Intent(MainCourse.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
