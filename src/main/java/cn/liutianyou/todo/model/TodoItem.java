package cn.liutianyou.todo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Liu Tianyou
 */
public class TodoItem implements Serializable {
    private Integer id;
    private String name;
    private Date createTime;
    private Date finishTime;
    private boolean finished;

    public  TodoItem(String name){
        this.name = name;
        this.createTime = new Date();
    }

    public Integer getId() {
        return id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public  void finishItem(){
        this.finished=true;
        this.finishTime = new Date();
    }
    @Override
    public  String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.id).append(". ");
        if (finished){
            builder.append("[Done] ");
        }
        builder.append(this.name);
        return builder.toString();
    }

}
