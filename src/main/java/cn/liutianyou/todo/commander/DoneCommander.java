package cn.liutianyou.todo.commander;

import cn.liutianyou.todo.model.TodoItem;
import cn.liutianyou.todo.repository.TodoRepository;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author Liu Tianyou
 */
@Component
public class DoneCommander implements BaseCommander {

    final
    TodoRepository todoRepository;

    @Parameter
    private String  index;

    public DoneCommander(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void process(JCommander jCommander) {
        TodoItem item = this.todoRepository.getById(Integer.parseInt(index));
        if(item==null){
            System.out.println("<"+index+"> not found");
        }else{
            item.finishItem();
            todoRepository.update(item);
            System.out.println("Item <"+index+"> done.");
        }
    }

    @Override
    public String getCommandName() {
        return "done";
    }
}
