package src.com.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.com.entity.ResultRecord;

/**
 * Created by Wangsz on 2017/5/7.
 */

public class ParseJson {

    //解析教师编辑好的课程
    public static List<Map<String, String>> parseTeacherCourse(String result)
    {
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        Map<String,String> map = null;
        try{
            JSONObject object = new JSONObject(result);
            int code = object.getInt("result");
            if(code == 200)
            {
                JSONArray array = object.getJSONArray("list");
                for (int i = 0; i < array.length(); i++) {
                    map = new HashMap<String,String>();
                    JSONObject data = array.getJSONObject(i);
                    map.put("newCourseId",data.getString("newCourseId"));
                    map.put("courseName",data.getString("courseName"));
                    list.add(map);
                }
                return list;
            }
            return null;
        }catch (JSONException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    //解析老师课程对应的全部习题
    public static List<Map<String, String>> parseCourseAllQuestion(String result)
    {
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        Map<String,String> map = null;
        try{
            JSONObject object = new JSONObject(result);
            int code = object.getInt("result");
            if(code == 200)
            {
                JSONArray array = object.getJSONArray("list");
                for (int i = 0; i < array.length(); i++) {
                    map = new HashMap<String,String>();
                    JSONObject data = array.getJSONObject(i);
                    map.put("questionID",data.getString("questionID"));
                    map.put("newcourseID", data.getString("newcourseID"));
                    map.put("teacherID", data.getString("teacherID"));
                    map.put("zhanID", data.getString("zhanID"));
                    map.put("jieID",data.getString("jieID"));
                    map.put("questionPoint",data.getString("questionPoint"));
                    map.put("questionContent",data.getString("questionContent"));
                    map.put("questionType",data.getString("questionType"));
                    map.put("A",data.getString("keyA"));
                    map.put("B",data.getString("keyB"));
                    map.put("C",data.getString("keyC"));
                    map.put("D",data.getString("keyD"));
                    map.put("rightKey",data.getString("rightKey"));
                    list.add(map);
                }
                return list;
            }
            return null;
        }catch (JSONException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    //解析发布结束所得分析结果
    public static ResultRecord parseResultRecord(String result)
    {
        try{
            JSONObject object = new JSONObject(result);
            int code = object.getInt("result");
            if(code == 200)
            {
                ResultRecord rq = new ResultRecord();
                rq.setSumA(object.getInt("sumA"));
                rq.setSumB(object.getInt("sumB"));
                rq.setSumC(object.getInt("sumC"));
                rq.setSumD(object.getInt("sumD"));
                rq.setSumAll(object.getInt("sumAll"));
                rq.setAccuracy(object.getDouble("accuracy"));
                return rq;
            }
            return null;
        }catch (JSONException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
