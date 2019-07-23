package web;

import dao.GradeDao;
import dao.StudentDao;
import model.Grade;
import model.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.DbUtil;
import util.JsonUtil;
import util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class GradeDeleteServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    GradeDao gradeDao = new GradeDao();
    StudentDao studentDao = new StudentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds = request.getParameter("delIds");
        Connection con = null ;
        JSONObject result = new JSONObject();
        try {
            con = dbUtil.getCon();
            String[] str = delIds.split(",");
            for (int i = 0; i < str.length ; i++) {
                boolean f = studentDao.getStudentByGradeId(con,str[i]);
                if(f){
                    result.put("errorIndex",i);
                    result.put("errorMsg","班级下面还有学生,不能删除!");
                    ResponseUtil.write(response,result);
                    return ;
                }
            }
            int delNums = gradeDao.gradeDelete(con,delIds);
            if(delNums>0){
                result.put("success","true");
                result.put("delNums",delNums);
            }else{
                result.put("errorMsg","删除失败");
            }

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
