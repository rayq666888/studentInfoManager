package web;

import dao.GradeDao;
import model.Grade;
import net.sf.json.JSONObject;
import util.DbUtil;
import util.ResponseUtil;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class GradeSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    GradeDao gradeDao = new GradeDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gradeName = request.getParameter("gradeName");
        String gradeDesc = request.getParameter("gradeDesc");
        String id = request.getParameter("id");
        Grade grade = new Grade(gradeName, gradeDesc);
        if (StringUtil.isNotEmpty(id)) {
            grade.setGradeId(Integer.parseInt(id));
        }
        Connection con = null;
        try {
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNums = 0;
            if (StringUtil.isNotEmpty(id)) {
                saveNums = gradeDao.gradeModify(con, grade);
            } else {
                saveNums = gradeDao.gradeAdd(con, grade);
            }
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                if (StringUtil.isNotEmpty(id)) {
                    result.put("errorMsg", "修改失败");
                } else {
                    result.put("errorMsg", "添加失败");
                }

            }

            ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
