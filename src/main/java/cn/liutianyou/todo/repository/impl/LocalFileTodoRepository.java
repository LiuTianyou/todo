package cn.liutianyou.todo.repository.impl;

import cn.liutianyou.todo.model.TodoItem;
import cn.liutianyou.todo.repository.TodoRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocalFileTodoRepository implements TodoRepository {
    private File dataFile;
    private final HashMap<Integer, TodoItem> todoItems;


    public LocalFileTodoRepository() {
        todoItems = new HashMap<>();
        init();
    }


    public void init() {
        String property = System.getProperty("user.dir");
        System.out.println("数据将存储在以下位置:" + property);
        dataFile = new File(property, "data.todo");
        loadData();
    }

    public void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.dataFile));
            oos.writeObject(new ArrayList<>(this.todoItems.values()));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadData() {
        if (this.dataFile.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.dataFile));
                List<TodoItem> list = (List<TodoItem>) ois.readObject();
                for (TodoItem todoItem : list) {
                    this.todoItems.put(todoItem.getId(), todoItem);
                }
            } catch (IOException | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void inset(TodoItem todoItem) {
        todoItem.setId(this.todoItems.size() + 1);
        todoItems.put(todoItem.getId(), todoItem);
        saveData();
    }

    @Override
    public void update(TodoItem todoItem) {
        TodoItem item = todoItems.get(todoItem.getId());
        if (item != null) {
            todoItems.put(todoItem.getId(), todoItem);
            saveData();
        }
    }

    @Override
    public List<TodoItem> selectAll() {
        return new ArrayList<>(this.todoItems.values());
    }

    @Override
    public List<TodoItem> selectNotFinished() {
        return this.todoItems.values().stream().filter(todoItem -> !todoItem.isFinished()).collect(Collectors.toList());
    }

    @Override
    public TodoItem getById(int id) {
        return this.todoItems.get(id);
    }
}
