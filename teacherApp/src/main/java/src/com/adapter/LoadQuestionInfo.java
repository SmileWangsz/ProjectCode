package src.com.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import src.com.ui.R;
import src.com.util.ViewHolder;

/**
 * Created by Wangsz on 2017/5/7.
 */

public class LoadQuestionInfo extends BaseAdapter{

    private List<Map<String,String>> list;
    private LayoutInflater mInflater;

    public LoadQuestionInfo(List<Map<String, String>> list, Context context) {
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        //这里本来有iF判断convertView是否为空，如果为空创建新的holder 如果不为空加载holder。
        //但是经测试后，如果用了IF判断操作，就会造成加载listView出现异常，导致奔溃。
        convertView = mInflater.inflate(R.layout.questionview,null);

        holder.questionName = (TextView) convertView.findViewById(R.id.questionName);
        holder.questionPoint = (TextView) convertView.findViewById(R.id.questionPoint);

        holder.questionName.setText("第"+list.get(position).get("zhanID")+"章 第"+list.get(position).get("jieID")+"节");
        holder.questionPoint.setText(list.get(position).get("questionPoint"));
        return convertView;
    }
}
