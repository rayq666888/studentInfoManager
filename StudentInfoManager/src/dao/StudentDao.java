package dao;

import model.Student;
import model.PageBean;
import util.DateUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Administrator
 * @date 2019/7/18 15:58:11
 * @description
 */
public class StudentDao {
    public ResultSet studentList(Connection con, PageBean pageBean,Student student,String bbirthday,String nnirthday) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_student s,t_grade g where s.gradeId = g.gradeId");
        if(student!=null&&StringUtil.isNotEmpty(student.getStuNo())){
            sb.append(" and s.stuNo like '%"+student.getStuNo()+"%'");
        }
        if(student!=null&&StringUtil.isNotEmpty(student.getStuName())){
            sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
        }
        if(student!=null&&StringUtil.isNotEmpty(student.getSex())){
            sb.append(" and s.sex = '"+student.getSex()+"'");
        }
        if(student!=null&&StringUtil.isNotEmpty(bbirthday)){
            sb.append(" and TO_DAYS(s.birthday)>= TO_DAYS( '"+bbirthday+"')");
        }
        if(student!=null&&StringUtil.isNotEmpty(nnirthday)){
            sb.append(" and TO_DAYS(s.birthday)<= TO_DAYS( '"+nnirthday+"')");
        }
        if(student!=null&&student.getGradeId()!=-1){
            sb.append(" and s.gradeId = '"+student.getGradeId()+"'");
        }

        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRow());
        }
        System.out.println("sb:"+sb);
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int getStudentCount(Connection con,Student student,String bbirthday,String nnirthday) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId = g.gradeId");

        if(student!=null&&StringUtil.isNotEmpty(student.getStuNo())){
            sb.append(" and s.stuNo like '%"+student.getStuNo()+"%'");
        }
        if(student!=null&&StringUtil.isNotEmpty(student.getStuName())){
            sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
        }
        if(student!=null&&StringUtil.isNotEmpty(student.getSex())){
            sb.append(" and s.sex = '"+student.getSex()+"'");
        }
        if(student!=null&&StringUtil.isNotEmpty(bbirthday)){
            sb.append(" and TO_DAYS(s.birthday)>= TO_DAYS( '"+bbirthday+"')");
        }
        if(student!=null&&StringUtil.isNotEmpty(nnirthday)){
            sb.append(" and TO_DAYS(s.birthday)<= TO_DAYS( '"+nnirthday+"')");
        }
        if(student!=null&&student.getGradeId()!=-1){
            sb.append(" and s.gradeId = '"+student.getGradeId()+"'");
        }

        PreparedStatement ps = con.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int total = rs.getInt("total");
            return total;
        } else {
            return 0;
        }

    }

    public int studentDelete(Connection con,String delIds) throws Exception {
        String sql = "delete from t_student where stuId in ("+delIds+")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();

    }

    public int studentAdd(Connection con,Student student) throws Exception{
        String sql = "insert into t_student values(null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,student.getStuNo());
        pstmt.setString(2,student.getStuName());
        pstmt.setString(3,student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(),"yyyy-MM-dd"));
        pstmt.setInt(5,student.getGradeId());
        pstmt.setString(6,student.getEmail());
        pstmt.setString(7,student.getStuDesc());

        return pstmt.executeUpdate();
    }

    public int studentModify(Connection con,Student student) throws Exception{
        String sql = "update t_student set stuNo = ? ,stuName = ?,sex = ?," +
                "birthday = ? ,gradeId = ? ,email = ?,stuDesc = ? where stuId = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,student.getStuNo());
        pstmt.setString(2,student.getStuName());
        pstmt.setString(3,student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(),"yyyy-MM-dd"));
        pstmt.setInt(5,student.getGradeId());
        pstmt.setString(6,student.getEmail());
        pstmt.setString(7,student.getStuDesc());
        pstmt.setInt(8,student.getStuId());

        return pstmt.executeUpdate();
    }

    public boolean getStudentByGradeId(Connection con,String gradeId) throws Exception{
        String sql = "select * from t_student where gradeId = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,gradeId);
        ResultSet rs = pstmt.executeQuery() ;
        if(rs.next()){
            return true;
        }else{
            return false ;
        }
    }
}
