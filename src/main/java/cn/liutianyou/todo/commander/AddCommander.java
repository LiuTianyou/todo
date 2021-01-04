package cn.liutianyou.todo.commander;

import cn.liutianyou.todo.exception.CommonException;
import cn.liutianyou.todo.model.TodoItem;
import cn.liutianyou.todo.repository.TodoRepository;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Liu Tianyou
 */
@Component
public class AddCommander implements BaseCommander {

    final
    TodoRepository todoRepository;
    @Parameter()
    private String name;

    public AddCommander(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void process(JCommander jCommander) {
        Optional.ofNullable(this.name).orElseThrow(()->new CommonException("please input item Name"));
        TodoItem todoItem = new TodoItem(this.name);
        todoRepository.inset(todoItem);
        this.name=null;
        System.out.println("Item <"+todoItem.getId()+"> added");
    }

    @Override
    public String getCommandName() {
        return "add";
    }
}
