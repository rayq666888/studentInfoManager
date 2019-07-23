package web;

import dao.StudentDao;
import model.Student;
import net.sf.json.JSONObject;
import util.DateUtil;
import util.DbUtil;
import util.ResponseUtil;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

public class StudentSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    StudentDao studentDao = new StudentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex    = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String gradeId = request.getParameter("gradeId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String stuId = request.getParameter("stuId");
        Student student = new Student(stuNo, stuName,sex, DateUtil.formatString(birthday,"yyyy-MM-dd"),Integer.parseInt(gradeId), email, stuDesc);
        if (StringUtil.isNotEmpty(stuId)) {
            student.setStuId(Integer.parseInt(stuId));
        }
        Connection con = null;
        try {
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNums = 0;
            if (StringUtil.isNotEmpty(stuId)) {
                saveNums = studentDao.studentModify(con, student);
            } else {
                saveNums = studentDao.studentAdd(con, student);
            }
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                if (StringUtil.isNotEmpty(stuId)) {
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
