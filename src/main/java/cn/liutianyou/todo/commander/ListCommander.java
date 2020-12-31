package cn.liutianyou.todo.commander;

import cn.liutianyou.todo.model.TodoItem;
import cn.liutianyou.todo.repository.TodoRepository;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Liu Tianyou
 */
@Component
public class ListCommander implements BaseCommander {

    @Parameter(names = {"--all", "-a"}, required = false, arity = 0, description = "print product version and exit")
    private boolean all;

    final
    TodoRepository todoRepository;

    public ListCommander(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public void process(JCommander jCommander) {
        if (all) {
            all = false;
            List<TodoItem> todoItems = todoRepository.selectAll();
            int finishedCount=0;
            for (TodoItem todoItem : todoItems) {
                if(todoItem.isFinished()){
                    finishedCount++;
                }
                System.out.println(todoItem);
            }
            System.out.println("\nTotal: "+todoItems.size()+" items, "+finishedCount+" item done");
            return;
        }
        //默认情况下执行这个
        List<TodoItem> todoItems = todoRepository.selectNotFinished();
        for (TodoItem todoItem : todoItems) {
            System.out.println(todoItem);
        }
        System.out.println("\nTotal: "+todoItems.size()+" items");
    }

    @Override
    public String getCommandName() {
        return "list";
    }
}
