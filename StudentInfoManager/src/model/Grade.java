package model;

/**
 * @author Administrator
 * @date 2019/7/17 22:19:38
 * @description
 */
public class Grade {
    private int gradeId;
    private String gradeName;
    private String gradeDesc;

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                ", gradeDesc='" + gradeDesc + '\'' +
                '}';
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }

    public int getGradeId() {
        return gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public Grade(int gradeId, String gradeName, String gradeDesc) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }

    public Grade(String gradeName, String gradeDesc) {
        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }

    public Grade() {
    }
}
