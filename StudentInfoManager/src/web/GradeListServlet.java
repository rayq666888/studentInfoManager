package web;

import dao.GradeDao;
import model.Grade;
import model.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.DbUtil;
import util.JsonUtil;
import util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

//@WebServlet(name = "GradeServlet")
public class GradeListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    GradeDao gradeDao = new GradeDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String gradeName = request.getParameter("gradeName");

        if(gradeName==null){
            gradeName = "";
        }
        Grade grade = new Grade();
        grade.setGradeName(gradeName);
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null ;
        try {
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, pageBean,grade));
            int total = gradeDao.getGradeCount(con,grade);
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
