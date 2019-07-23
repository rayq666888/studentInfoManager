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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

//@WebServlet(name = "GradeServlet")
public class GradeComboListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    GradeDao gradeDao = new GradeDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection con = null ;
        try {
            con = dbUtil.getCon();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("gradeId","");
            jsonObject.put("gradeName","请选择...");

            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, null,null));
            jsonArray.add(jsonObject);
            ResponseUtil.write(response,jsonArray);
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
