package src.com.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import src.com.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton firstBtn, secondBtn;
    private FragmentMain fMain = null;
    private FragmentSecond fSecond = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firstBtn = (ImageButton) findViewById(R.id.btn1);
        secondBtn = (ImageButton) findViewById(R.id.btn2);
        firstBtn.setOnClickListener(this);
        secondBtn.setOnClickListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment(){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fMain = new FragmentMain();
        fMain.setArguments(getIntent().getExtras());
        transaction.replace(R.id.id_content,fMain);
        secondBtn.setBackgroundResource(R.mipmap.byu);
        transaction.commit();
    }
    @Override
    public void onClick(View v) {

        //还有一个getsupportFragmentManageer()这个方法，使用的包是super v4的。
        //如果使用getFragmentManager()方法，就得统一用app.fragment包。
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId())
        {
            case R.id.btn2:
                if(fMain == null)
                {
                    fMain = new FragmentMain();
                    fMain.setArguments(getIntent().getExtras());
                }
                transaction.replace(R.id.id_content,fMain);
                firstBtn.setBackgroundResource(R.mipmap.byn);
                secondBtn.setBackgroundResource(R.mipmap.byu);
                break;

            case R.id.btn1:
                if(fSecond == null)
                {
                    fSecond = new FragmentSecond();
                    fSecond.setArguments(getIntent().getExtras());
                }
                transaction.replace(R.id.id_content,fSecond);
                secondBtn.setBackgroundResource(R.mipmap.byt);
                firstBtn.setBackgroundResource(R.mipmap.byo);
                break;
        }
        transaction.commit();
    }

}
