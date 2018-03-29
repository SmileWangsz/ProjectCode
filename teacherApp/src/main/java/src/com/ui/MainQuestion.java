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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import src.com.adapter.LoadQuestionInfo;
import src.com.entity.courseQuestion;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;

public class MainQuestion extends AppCompatActivity implements View.OnClickListener{

    private ListView questionListView;
    private TextView title;
    private Button btn_back;
    private String newCourseId, courseName, userId;
    private List<Map<String, String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);
        questionListView = (ListView) findViewById(R.id.questionListView);
        title = (TextView) findViewById(R.id.title);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        Intent intent = this.getIntent();
        newCourseId = intent.getStringExtra("newCourseId");
        courseName = intent.getStringExtra("courseName");
        userId = intent.getStringExtra("userId");

        title.setText(courseName);
        getData();
        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainQuestion.this, DoExercise.class);
                intent.putExtra("questionID",list.get(position).get("questionID"));
                intent.putExtra("questionContent",list.get(position).get("questionContent"));
                intent.putExtra("A",list.get(position).get("A"));
                intent.putExtra("B",list.get(position).get("B"));
                intent.putExtra("C",list.get(position).get("C"));
                intent.putExtra("D",list.get(position).get("D"));
                intent.putExtra("rightKey",list.get(position).get("rightKey"));
                startActivity(intent);
            }
        });
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/readCourseAllQuestionServlet";
                String param = "newCourseId="+newCourseId;
                String result;
                result = HttpRequest.sendPost(url,param);
                if(result !=null)
                {
                    list = ParseJson.parseCourseAllQuestion(result);
                    if (list !=null && list.size()>0)
                    {
                        handler.sendEmptyMessage(1);
                    }else
                        handler.sendEmptyMessage(0);
                }else
                    handler.sendEmptyMessage(-1);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                LoadQuestionInfo adapter = new LoadQuestionInfo(list, MainQuestion.this);
                questionListView.setAdapter(adapter);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(MainQuestion.this, "你还没有编辑习题，请先编辑习题。", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainQuestion.this, "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };//这里结束

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back)
        {
            finish();
        }
    }
}
