package src.com.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.com.entity.StudentInfo;
import src.com.entity.courseQuestion;

/**
 * Created by Wangsz on 2017/5/7.
 */

public class ParseJson {

    //解析学校系部表
    public static List<Map<String, String>> parseSchoolLevel(String result)
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
                    map.put("id",data.getString("id"));
                    map.put("Name",data.getString("levelName"));
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

    //解析学校专业表
    public static List<Map<String, String>> parseSchoolMajor(String result)
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
                    map.put("levelId",data.getString("levelId"));
                    map.put("majorId",data.getString("majorId"));
                    map.put("Name",data.getString("majorName"));
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

    //解析学校班级表
    public static List<Map<String, String>> parseSchoolClass(String result)
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
                    map.put("classId",data.getString("classId"));
                    map.put("majorId",data.getString("majorId"));
                    map.put("Name",data.getString("className"));
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

    //解析入学时间
    public static List<Map<String, String>> parseSchoolTime(String result)
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
                    map.put("timeId",data.getString("timeId"));
                    map.put("Name",data.getString("timeName"));
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

    //解析学生信息
    public static StudentInfo parseStudentInfo(String result)
    {
        StudentInfo studentInfo = new StudentInfo();
        try{
            JSONObject object = new JSONObject(result);
            int code = object.getInt("result");
            if(code == 200)
            {
                studentInfo.setUserName(object.getString("userName"));
                studentInfo.setUserLevel(object.getString("userLevel"));
                studentInfo.setUserMajor(object.getString("userMajor"));
                studentInfo.setUserClass(object.getString("userClass"));
            }
            return studentInfo;
        }catch (JSONException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    //解析课程表
    public static List<Map<String, String>> parseCourseInfo(String result)
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
                    map.put("courseId",data.getString("courseId"));
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
    //解析任课教师名字
    public static List<Map<String, String>> parseTeacherName(String result)
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
                    map.put("teacherId", data.getString("teacherId"));
                    map.put("teacherName",data.getString("teacherName"));
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

    //解析学生课程表课程
    public static List<Map<String, String>> parseStudentCourse(String result)
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
                    map.put("studentCourseId",data.getString("newCourseId"));
                    map.put("teacherId", data.getString("teacherId"));
                    map.put("courseId", data.getString("courseId"));
                    map.put("courseName", data.getString("courseName"));
                    map.put("teacherName",data.getString("teacherName"));
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
    //解析老师发布的习题
    public static courseQuestion parseCourseQuestion(String result)
    {
        try{
            JSONObject object = new JSONObject(result);
            int code = object.getInt("result");
            if(code == 200)
            {
                courseQuestion cq = new courseQuestion();
                String resultData = object.optString("cq");
                JSONObject rs = new JSONObject(resultData);
                cq.setQuestionID(rs.getInt("questionID"));
                cq.setTeacherID(rs.getString("teacherID"));
                cq.setNewcourseID(rs.getString("newcourseID"));
                cq.setZhanID(rs.getInt("zhanID"));
                cq.setJieID(rs.getInt("jieID"));
                cq.setQuestionType(rs.getInt("questionType"));
                cq.setQuestionPoint(rs.getString("questionPoint"));
                cq.setQuestionContent(rs.getString("questionContent"));
                cq.setKeyA(rs.getString("keyA"));
                cq.setKeyB(rs.getString("keyB"));
                cq.setKeyC(rs.getString("keyC"));
                cq.setKeyD(rs.getString("keyD"));
                cq.setRightKey(rs.getString("rightKey"));
                return cq;
            }
            return null;
        }catch (JSONException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
