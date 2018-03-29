package src.com.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import src.com.R;
import src.com.adapter.LoadCourseInfo;
import src.com.adapter.LoadTeacherName;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;

public class CourseDetailActivity extends Activity implements View.OnClickListener{

    private ListView courseListView;
    private String userId, courseId, courseName, newCourseId;
    private List<Map<String, String>> list;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        courseListView = (ListView) findViewById(R.id.courseListView);
        //获取之前页面传递过来的数据
        Intent intent = this.getIntent();
        userId = intent.getStringExtra("userId");
        courseId = intent.getStringExtra("courseId");
        courseName = intent.getStringExtra("courseName");
        newCourseId = userId+courseId;
        //获取数据显示在ListView上
        getData();
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/WriteStudentCourseServlet";
                        String param = "newCourseId="+newCourseId+"&studentId="+userId+"&courseId="+courseId
                                +"&teacherId="+list.get(position).get("teacherId")+"&courseType=1";
                        String result;
                        result = HttpRequest.sendPost(url,param);
                        if(result.equals("success"))
                        {
                            handler.sendEmptyMessage(2);
                        }
                        else if(result.equals("failure"))
                        {
                            handler.sendEmptyMessage(3);
                        }
                        else
                            handler.sendEmptyMessage(-1);
                    }
                }).start();
            }
        });
    }

    private void getData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/GetCourseInfoServlet";
                String param = "flag=2"+"&courseId="+courseId;
                String result;
                result = HttpRequest.sendPost(url,param);
                if(result!=null)
                {
                    list = ParseJson.parseTeacherName(result);
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
                LoadTeacherName adapter = new LoadTeacherName(list,courseName,CourseDetailActivity.this);
                courseListView.setAdapter(adapter);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(CourseDetailActivity.this, "暂时还没有任课老师", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(CourseDetailActivity.this, "通信失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(CourseDetailActivity.this, "选课成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                Intent intent = new Intent(CourseDetailActivity.this, Main2Activity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
            if (msg.what == 3)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(CourseDetailActivity.this, "选课失败", Toast.LENGTH_SHORT).show();
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
            finish();
        }
    }
}
