package cn.liutianyou.todo.repository;

import cn.liutianyou.todo.model.TodoItem;

import java.util.List;

public interface TodoRepository {
    void inset(TodoItem todoItem);
    void update(TodoItem todoItem);
    List<TodoItem> selectAll();
    List<TodoItem> selectNotFinished();
    TodoItem getById(int id);

}
