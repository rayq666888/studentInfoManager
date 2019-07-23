package model;

/**
 * @author Administrator
 * @date 2019/7/17 22:30:50
 * @description
 */
public class PageBean {
    //第几页
    private int page;
    //每页记录数
    private int row;
    //起始页
    private int start;

    public PageBean(int page, int row) {
        this.page = page;
        this.row = row;
    }

    public int getPage() {
        return page;
    }

    public int getRow() {
        return row;
    }

    public int getStart() {
        return (page-1)*row;
    }
}
