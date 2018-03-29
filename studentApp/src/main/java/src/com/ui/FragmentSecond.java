package src.com.ui;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import src.com.R;
import src.com.adapter.LoadStudentCourse;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSecond extends Fragment implements View.OnClickListener{


    public FragmentSecond() {
        // Required empty public constructor
    }
    private Button addCourse;
    private String userId;
    private List<Map<String, String>> list;
    private ListView courseListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_second, container, false);
        addCourse = (Button) view.findViewById(R.id.addCourse);
        userId = getArguments().getString("userId");
        courseListView = (ListView)view.findViewById(R.id.courseListView);
        addCourse.setOnClickListener(this);
        getData();
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),DoExerciseActivity.class);
                String newCourseId = list.get(position).get("teacherId")+list.get(position).get("courseId");
                intent.putExtra("newCourseId",newCourseId);
                intent.putExtra("studentCourseId",list.get(position).get("studentCourseId"));
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/ShowStudentCourseServlet";
                String param = "userId="+userId;
                String result;
                result = HttpRequest.sendPost(url,param);

                if(result != null)
                {
                    list = ParseJson.parseStudentCourse(result);
                    if(list !=null && list.size() >0)
                    {
                        handler.sendEmptyMessage(1);
                    }else
                    {
                        handler.sendEmptyMessage(0);
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
                LoadStudentCourse adapter = new LoadStudentCourse(list,getActivity());
                courseListView.setAdapter(adapter);
            }
            if (msg.what == 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(getActivity(), "暂时没有课程,请添加课程", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), "通信失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        }
    };//这里结束

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addCourse)
        {
            Intent intent = new Intent(getActivity(), CourseActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    }
}
