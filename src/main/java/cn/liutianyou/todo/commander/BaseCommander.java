package cn.liutianyou.todo.commander;

import com.beust.jcommander.JCommander;

import java.util.Objects;

/**
 * @author Liu Tianyou
 */
public interface BaseCommander {
    /**
     * Commander的处理逻辑
     * @param jCommander
     */
    void process(JCommander jCommander);

    /**
     * 返回当前Commander的名称
     * @return
     */
    String getCommandName();

    default void run(JCommander jCommander){
        if(Objects.equals(jCommander.getParsedCommand(),this.getCommandName())){
            process(jCommander);
        }
    }
}
