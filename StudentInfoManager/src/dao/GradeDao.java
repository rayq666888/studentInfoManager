package dao;

import model.Grade;
import model.PageBean;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Administrator
 * @date 2019/7/17 22:35:57
 * @description
 */
public class GradeDao {
    public ResultSet gradeList(Connection con, PageBean pageBean, Grade grade) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_grade");
        if(grade!=null&&StringUtil.isNotEmpty(grade.getGradeName())){
            sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRow());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }

    public int getGradeCount(Connection con,Grade grade) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
        if(StringUtil.isNotEmpty(grade.getGradeName())){
            sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
        }
        PreparedStatement ps = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int total = rs.getInt("total");
            return total;
        } else {
            return 0;
        }

    }
    public int gradeDelete(Connection con,String delIds) throws Exception {
        String sql = "delete from t_grade where gradeId in ("+delIds+")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();

    }
    public int gradeAdd(Connection con,Grade grade) throws  Exception{
        String sql = "insert into t_grade values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,grade.getGradeName());
        pstmt.setString(2,grade.getGradeDesc());
        return pstmt.executeUpdate();
    }

    public int gradeModify(Connection con,Grade grade) throws  Exception{
        String sql = "update t_grade set gradeName = ?,gradeDesc = ? where id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,grade.getGradeName());
        pstmt.setString(2,grade.getGradeDesc());
        System.out.println(grade.getGradeId());
        pstmt.setInt(3,grade.getGradeId());
        return pstmt.executeUpdate();
    }


}
