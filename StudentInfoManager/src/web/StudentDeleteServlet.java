package web;

import dao.StudentDao;
import net.sf.json.JSONObject;
import util.DbUtil;
import util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class StudentDeleteServlet extends HttpServlet {
    private DbUtil dbUtil = new DbUtil();
    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds = request.getParameter("delIds");
        Connection con = null ;
        try {
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            int delNums = studentDao.studentDelete(con,delIds);
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
