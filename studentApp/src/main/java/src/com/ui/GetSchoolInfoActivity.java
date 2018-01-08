package src.com.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import src.com.R;
import src.com.adapter.LoadSchoolLevel;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;
import src.com.util.SchoolInfoFlag;

/**
 * Created by Wangsz on 2017/5/7.
 */

public class GetSchoolInfoActivity extends Activity{

    private ListView infoListView;
    private List<Map<String, String>> list;
    private int flag = 0;
    private int id = 0;
    private SchoolInfoFlag FLAG_CODE;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        infoListView = (ListView) findViewById(R.id.infoListView);
        infoListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        infoListView.setCacheColorHint(0);
        Intent intent = this.getIntent();
        flag = intent.getIntExtra("flag",0);
        id = intent.getIntExtra("id",0);

        getSchoolInfo(flag, id);//获取学校信息

        infoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent1 = new Intent(GetSchoolInfoActivity.this,WriteInfoActivity.class);
                if(flag == 1)
                {
                    intent1.putExtra("levelName",list.get(position).get("Name"));
                    intent1.putExtra("levelId",list.get(position).get("id"));
                    FLAG_CODE.levelCode = 1;
                    FLAG_CODE.majorCode = 0;
                    FLAG_CODE.classCode = 0;
                    FLAG_CODE.time = 0;
                    FLAG_CODE.userIdCode = 0;
                }
                if(flag == 2)
                {
                    intent1.putExtra("MajorName",list.get(position).get("Name"));
                    intent1.putExtra("MajorId",list.get(position).get("majorId"));
                    FLAG_CODE.levelCode = 0;
                    FLAG_CODE.majorCode = 1;
                    FLAG_CODE.classCode = 0;
                    FLAG_CODE.time = 0;
                    FLAG_CODE.userIdCode = 0;
                }
                if(flag == 3)
                {
                    intent1.putExtra("className",list.get(position).get("Name"));
                    intent1.putExtra("classId",list.get(position).get("classId"));
                    FLAG_CODE.levelCode = 0;
                    FLAG_CODE.majorCode = 0;
                    FLAG_CODE.classCode = 1;
                    FLAG_CODE.time = 0;
                    FLAG_CODE.userIdCode = 0;
                }
                if(flag == 4)
                {
                    intent1.putExtra("timeName",list.get(position).get("Name"));
                    intent1.putExtra("timeId",list.get(position).get("timeId"));
                    FLAG_CODE.levelCode = 0;
                    FLAG_CODE.majorCode = 0;
                    FLAG_CODE.classCode = 0;
                    FLAG_CODE.time = 1;
                    FLAG_CODE.userIdCode = 0;
                }
                startActivity(intent1);
            }
        });
    }

    private void getSchoolInfo(final int flag, final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/getSchoolInfoServlet";
                String param = "flag="+flag+"&id="+id;
                String result;
                result = HttpRequest.sendPost(url,param);
                if(result!=null)
                {
                    if(flag == 1)
                    {
                        list = ParseJson.parseSchoolLevel(result);
                    }
                    if(flag == 2)
                    {
                        list = ParseJson.parseSchoolMajor(result);
                    }
                    if (flag ==3)
                    {
                        list = ParseJson.parseSchoolClass(result);
                    }
                    if (flag ==4)
                    {
                        list = ParseJson.parseSchoolTime(result);
                    }
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
                LoadSchoolLevel adapter = new LoadSchoolLevel(list,GetSchoolInfoActivity.this);
                infoListView.setAdapter(adapter);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(GetSchoolInfoActivity.this, "list为空", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(GetSchoolInfoActivity.this, "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };//这里结束
}
