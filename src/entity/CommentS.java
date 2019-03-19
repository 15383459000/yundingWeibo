/**
 * FileName:wangran
 */
package entity;

public class CommentS {
    //前端传过来的操作
    private String action;
    //Comment类型的变量
    private Comment comment;

    //无参构造器
    public CommentS() {

    }

    //getter setter方法
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}