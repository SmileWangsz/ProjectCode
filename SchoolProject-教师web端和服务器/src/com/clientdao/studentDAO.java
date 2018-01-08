package com.clientdao;

import com.dao.selectCourseDao;
import com.entity.*;
import com.util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class studentDAO {

    //注册学生信息
    public boolean regInfo(StudentReg sr)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "insert into student_reg value(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,sr.getUsername());
            stmt.setString(2,sr.getPassword());
            stmt.setString(3,sr.getTelenumber());
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读取学生用户信息
    public boolean readInfo(String userName, String passWord)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "select * FROM student_reg WHERE student_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userName);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                if(rs.getString("student_Pwd").equals(passWord))
                    return true;
                else
                    return false;
            }
            else
                return false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
        }
    }
    //读取老师用户信息
    public boolean readTeacherInfo(String userName, String passWord)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "select * FROM teachar_reg WHERE teacher_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userName);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                if(rs.getString("user_pwd").equals(passWord))
                    return true;
                else
                    return false;
            }
            else
                return false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
        }
    }

    //获取学校系部
    public ArrayList<SchoolLevel> getAllLevel()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<SchoolLevel> list = new ArrayList<SchoolLevel>();

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from school_level";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                SchoolLevel sl = new SchoolLevel();
                sl.setId(rs.getInt("level_ID"));
                sl.setLevelName(rs.getString("level_Name"));
                list.add(sl);
            }
            return list;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //获取学校专业
    public ArrayList<SchoolMajor> getAllMajor(int id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<SchoolMajor> list = new ArrayList<SchoolMajor>();

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from school_major WHERE level_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                SchoolMajor sm = new SchoolMajor();
                sm.setLevelId(rs.getInt("level_ID"));
                sm.setMajorId(rs.getInt("major_ID"));
                sm.setMajorName(rs.getString("major_Name"));
                list.add(sm);
            }
            return list;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取学校班级
    public ArrayList<SchoolClass> getAllClass(int id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<SchoolClass> list = new ArrayList<SchoolClass>();

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from school_class WHERE major_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                SchoolClass sc = new SchoolClass();
                sc.setClassId(rs.getInt("class_ID"));
                sc.setMajorId(rs.getInt("major_ID"));
                sc.setClassName(rs.getString("class_Name"));
                list.add(sc);
            }
            return list;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取入学时间
    public ArrayList<SchoolTime> getAllTime()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<SchoolTime> list = new ArrayList<SchoolTime>();

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from school_time";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                SchoolTime st = new SchoolTime();
                st.setTimeId(rs.getInt("time_ID"));
                st.setTimeName(rs.getString("time_Name"));
                list.add(st);
            }
            return list;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //填写学生信息。
    public boolean writeStudentInfo(StudentInfo si)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "insert into student_info value(?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,si.getUserID());
            stmt.setString(2,si.getUserName());
            stmt.setString(3,si.getUserSex());
            stmt.setString(4,si.getUserLevel());
            stmt.setString(5,si.getUserMajor());
            stmt.setString(6,si.getUserClass());
            stmt.setString(7,si.getUserTime());
            stmt.setString(8,si.getUserTele());
            stmt.setString(9,si.getUserEmail());
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取学生信息
    public StudentInfo getStudentInfo(String userId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        StudentInfo si = new StudentInfo();

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from student_info WHERE student_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userId);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                si.setUserName(rs.getString("student_Name"));
                si.setUserLevel(rs.getString("student_Level"));
                si.setUserMajor(rs.getString("student_Major"));
                si.setUserClass(rs.getString("student_Class"));
                si.setUserTime(rs.getString("student_Time"));
            }
            return si;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //通过课程表获取学校课程
    public ArrayList<Course> getCourse()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Course> list = new ArrayList<Course>();

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from course";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();


            while (rs.next())
            {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_Key"));
                course.setCourseName(rs.getString("course_Name"));
                list.add(course);
            }
            return list;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据课程key值获取课程ID
    public String getCourseById(int id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "select course_Id From course where course_key = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getString("course_Id");
            }
            return null;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据课程key值获取课程名称
    public String getCourseNameById(int id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "select course_Name From course where course_key = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getString("course_Name");
            }
            return null;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据老师 ID 获取老师名字
    public String getTeacherNameById(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<selectCourse> list = new ArrayList<selectCourse>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select teacher_name From teacher_info where teacher_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getString("teacher_name");
            }
            return null;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据课程ID获取全部教师的名字
    public ArrayList<TeacherName> getTeacherName(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<TeacherName> list = new ArrayList<TeacherName>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select teacher_id From select_course where course_Id = ? AND course_Type = 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                TeacherName tn = new TeacherName();
                tn.setTeacherId(rs.getString("teacher_id"));
                tn.setTeacherName(getTeacherNameById(rs.getString("teacher_id")));
                list.add(tn);
            }
            return list;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //填写学生选课后的新课程。
    public boolean writeStudentCourse(StudentCourse sc)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "insert into student_course_info(newcourse_ID,student_ID,course_ID,teacher_ID,course_Type) value(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,sc.getNewCourseId());
            stmt.setString(2,sc.getStudentId());
            stmt.setInt(3,sc.getCourseId());
            stmt.setString(4,sc.getTeacherId());
            stmt.setInt(5,sc.getCourseType());
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据学生课程ID判断学生是否已经选过此课程。如果选过更新状态，因为删除是只是更新了状态而已。
    public boolean updateCourseType(String newCourseId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "UPDATE student_course_info SET course_Type = 1 WHERE newcourse_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newCourseId);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据学生课程ID判断这门课是否已经选过了。
    public boolean selectCourseName(String newCourseId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "SELECT * FROM student_course_info WHERE newcourse_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newCourseId);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                return true;
            }
            return false;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //用学生ID到学生选课后的课程表里面获取课程显示在界面上。
    public ArrayList<ShowCourse> showStudentCourse(String userId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ShowCourse> list = new ArrayList<ShowCourse>();
        try
        {
            conn = DBHelper.getConnection();
            String sql = "SELECT * FROM student_course_info WHERE student_ID=? AND course_Type=1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                ShowCourse sc = new ShowCourse();
                sc.setNewCourseId(rs.getString("newcourse_ID"));
                sc.setTeacherId(rs.getString("teacher_ID"));
                sc.setCourseId(getCourseById(rs.getInt("course_ID")));
                sc.setTeacherName(getTeacherNameById(rs.getString("teacher_ID")));
                sc.setCourseName(getCourseNameById(rs.getInt("course_ID")));
                list.add(sc);
            }
            return list;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据习题的ID读取习题的全部资料
    public courseQuestion getQuestionById(String newCourseId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        courseQuestion cq = new courseQuestion();
        try{
            conn = DBHelper.getConnection();
            String sql = "select * From course_subject where newcourse_ID=? AND question_Type = 2";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,newCourseId);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                cq.setQuestionID(rs.getInt("question_ID"));
                cq.setTeacherID(rs.getString("teacher_ID"));
                cq.setNewcourseID(rs.getString("newcourse_ID"));
                cq.setZhanID(rs.getInt("zhang"));
                cq.setJieID(rs.getInt("jie"));
                cq.setQuestionType(rs.getInt("question_Type"));
                cq.setQuestionPoint(rs.getString("question_Point"));
                cq.setQuestionContent(rs.getString("question_Content"));
                cq.setKeyA(rs.getString("A"));
                cq.setKeyB(rs.getString("B"));
                cq.setKeyC(rs.getString("C"));
                cq.setKeyD(rs.getString("D"));
                cq.setRightKey(rs.getString("question_Key"));
                return cq;
            }else
                return null;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //更新课程状态，改掉之后表示删除。
    public boolean updateStudentCourseType(String newCourseId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "UPDATE student_course_info SET course_Type = 0 WHERE newcourse_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newCourseId);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读取一个教师所教的所有课程。但是为了显示在APP界面上，只需要显示课程名称即可，所以我只要拿到课程名称和课程ID即可
    public ArrayList<TeacherCourse> getAllCourse(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<TeacherCourse> list = new ArrayList<TeacherCourse>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select * From select_course where teacher_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            rs = stmt.executeQuery();
            while(rs.next())
            {
                TeacherCourse tc = new TeacherCourse();
                selectCourseDao sDao = new selectCourseDao();
                tc.setNewCourseId(rs.getString("newcourse_ID"));
                tc.setCourseName(sDao.getCourseById(rs.getString("course_Id")));
                list.add(tc);
            }
            return list;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据习题的ID读取习题的全部资料
    public ArrayList<courseQuestion> getAllQuestionById(String newCourseId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<courseQuestion> list = new ArrayList<courseQuestion>();
        try{
            conn = DBHelper.getConnection();
            String sql = "select * From course_subject where newcourse_ID=? AND question_Type = 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,newCourseId);
            rs = stmt.executeQuery();
            while(rs.next())
            {
                courseQuestion cq = new courseQuestion();
                cq.setQuestionID(rs.getInt("question_ID"));
                cq.setTeacherID(rs.getString("teacher_ID"));
                cq.setNewcourseID(rs.getString("newcourse_ID"));
                cq.setZhanID(rs.getInt("zhang"));
                cq.setJieID(rs.getInt("jie"));
                cq.setQuestionType(rs.getInt("question_Type"));
                cq.setQuestionPoint(rs.getString("question_Point"));
                cq.setQuestionContent(rs.getString("question_Content"));
                cq.setKeyA(rs.getString("A"));
                cq.setKeyB(rs.getString("B"));
                cq.setKeyC(rs.getString("C"));
                cq.setKeyD(rs.getString("D"));
                cq.setRightKey(rs.getString("question_Key"));
                list.add(cq);
            }

            return list;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //插入老师每一次发布的记录
    public boolean setQuestionRelease(int releaseId, int questionId, Timestamp startTime, int peopleNumber)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "insert into question_release value(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,releaseId);
            stmt.setInt(2,questionId);
            stmt.setTimestamp(3,startTime);
            stmt.setInt(4,peopleNumber);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //点击发布的时候更新习题表中习题的发布状态。
    public boolean updateQuestionType(int questionId, int type, int releaseId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "UPDATE course_subject SET question_Type = ?, release_ID = ? WHERE question_ID = "+questionId;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, type);
            stmt.setInt(2,releaseId);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //根据习题的ID获取发布的ID
    public int getReleaseId(int id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<selectCourse> list = new ArrayList<selectCourse>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select release_ID From course_subject where question_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getInt("release_ID");
            }
            return 0;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //插入学生答题记录
    public boolean setStudentRecord(String recordId,String studentId, int releaseId, String answer)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            conn = DBHelper.getConnection();
            String sql = "insert into student_record value(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,recordId);
            stmt.setString(2,studentId);
            stmt.setInt(3,releaseId);
            stmt.setString(4,answer);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据发布ID和答案统计这次作答A的人数多少，B的人数多少？……。
    public int getStudentAnswerSum(int releaseId, String answer)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        StudentInfo si = new StudentInfo();

        try {
            conn = DBHelper.getConnection();
            String sql = "select COUNT(*) AS answerSum FROM student_record WHERE release_Id = ? AND answer = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,releaseId);
            stmt.setString(2,answer);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getInt("answerSum");
            }
            return 0;

        }catch (Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
