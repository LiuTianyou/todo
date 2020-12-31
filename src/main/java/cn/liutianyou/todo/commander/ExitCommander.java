package cn.liutianyou.todo.commander;

import com.beust.jcommander.JCommander;
import org.springframework.stereotype.Component;

/**
 * @author Liu Tianyou
 */
@Component
public class ExitCommander implements BaseCommander {
    @Override
    public void process(JCommander jCommander) {
        System.out.println("Bye!");
        System.exit(0);

    }

    @Override
    public String getCommandName() {
        return "exit";
    }
}
