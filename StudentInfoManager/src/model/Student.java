package model;

import java.util.Date;

/**
 * @author Administrator
 * @date 2019/7/18 15:56:11
 * @description
 */
public class Student {
    private int stuId;
    private String stuNo;
    private String stuName;
    private String sex ;
    private Date birthday;
    private int gradeId = -1;

    public Student() {
    }

    private String email;
    private String stuDesc;
    private String gradeName;

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", gradeId=" + gradeId +
                ", email='" + email + '\'' +
                ", stuDesc='" + stuDesc + '\'' +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeName() {
        return gradeName;
    }




    public Student(int stuId, String stuNo, String stuName, String sex, Date birthday, int gradeId, String email, String stuDesc) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.sex = sex;
        this.birthday = birthday;
        this.gradeId = gradeId;
        this.email = email;
        this.stuDesc = stuDesc;
    }

    public Student(String stuNo, String stuName, String sex, Date birthday, int gradeId, String email, String stuDesc) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.sex = sex;
        this.birthday = birthday;
        this.gradeId = gradeId;
        this.email = email;
        this.stuDesc = stuDesc;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStuDesc(String stuDesc) {
        this.stuDesc = stuDesc;
    }

    public int getStuId() {
        return stuId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getGradeId() {
        return gradeId;
    }

    public String getEmail() {
        return email;
    }

    public String getStuDesc() {
        return stuDesc;
    }
}
