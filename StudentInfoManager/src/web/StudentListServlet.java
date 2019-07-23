package web;

import dao.StudentDao;
import model.Student;
import model.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.DbUtil;
import util.JsonUtil;
import util.ResponseUtil;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

//@WebServlet(name = "StudentServlet")
public class StudentListServlet extends HttpServlet {
    private DbUtil dbUtil = new DbUtil();
    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String bbirthday = request.getParameter("bbirthday");
        String nnirthday = request.getParameter("nnirthday");
        String gradeId = request.getParameter("gradeId");

        Student student = new Student();
        if(stuNo!=null){
            student.setStuNo(stuNo);
            student.setStuName(stuName);
            student.setSex(sex);
            if(StringUtil.isNotEmpty(gradeId)){
                student.setGradeId(Integer.parseInt(gradeId));
            }
        }else{
            student = null ;
        }


        String page = request.getParameter("page");
        String rows = request.getParameter("rows");


        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null ;
        try {
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(studentDao.studentList(con, pageBean,student,bbirthday,nnirthday));
            int total = studentDao.getStudentCount(con,student,bbirthday,nnirthday);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
