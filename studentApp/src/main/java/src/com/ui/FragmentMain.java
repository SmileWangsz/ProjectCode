package src.com.ui;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import src.com.R;
import src.com.entity.StudentInfo;
import src.com.util.HttpRequest;
import src.com.util.ParseJson;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment implements View.OnClickListener{


    public FragmentMain() {
        // Required empty public constructor
    }

    private ImageView user_image;
    private TextView username, edit_xibu, edit_zhuanye, edit_banji;
    private Button quitLogin;
    private String userId;
    private StudentInfo studentInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_main, container, false);
        user_image = (ImageView) view.findViewById(R.id.user_image);
        username = (TextView) view.findViewById(R.id.username);
        edit_xibu = (TextView) view.findViewById(R.id.edit_xibu);
        edit_zhuanye = (TextView) view.findViewById(R.id.edit_zhuanye);
        edit_banji = (TextView) view.findViewById(R.id.edit_banji);
        quitLogin = (Button) view.findViewById(R.id.quitLogin);
        userId = getArguments().getString("userId");
        getPersonInfo();
        user_image.setOnClickListener(this);
        quitLogin.setOnClickListener(this);
        return view;
    }

    private void getPersonInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.0.105:8888/SchoolProject/com/androidservlet/GetStudentInfoServlet";
                String param = "userId="+userId;
                String result;
                result = HttpRequest.sendPost(url,param);
                if(result!=null)
                {
                    studentInfo = ParseJson.parseStudentInfo(result);
                    if (studentInfo!=null)
                    {
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                username.setText(studentInfo.getUserName());
                edit_xibu.setText(studentInfo.getUserLevel());
                edit_zhuanye.setText(studentInfo.getUserMajor());
                edit_banji.setText(studentInfo.getUserClass());
            }
        }
    };//这里结束

    @Override
    public void onClick(View v) {
        /*if(v.getId() == R.id.user_image)
        {
            Intent intent = new Intent(getActivity(),WriteInfoActivity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
        }*/
        if(v.getId() == R.id.quitLogin)
        {
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }
    }
}
