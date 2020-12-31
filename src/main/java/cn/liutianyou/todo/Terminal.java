package cn.liutianyou.todo;

import cn.liutianyou.todo.commander.BaseCommander;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class Terminal {


    private final Map<String, BaseCommander> commanders;

    public Terminal(Map<String, BaseCommander> commanders) {
        this.commanders = commanders;
    }

    public void start() {
        JCommander jc = JCommander.newBuilder().programName("todo")
                .build();
        for (BaseCommander value : commanders.values()) {
            jc.addCommand(value.getCommandName(), value);

        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String s = scanner.nextLine();
            String[] strings = prevDeal(s);
            try {
                jc.parse(strings);
                run(jc);
            } catch (MissingCommandException missingCommandException) {
                System.out.println("Unsupported command\"" + missingCommandException.getUnknownCommand() + "\"");
            } catch (com.beust.jcommander.ParameterException parameterException) {
                System.out.println("不支持的参数\"" + parameterException.getLocalizedMessage() + "\"");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("未找到命令");
            }

        }
    }

    public String[] prevDeal(String s) {
        String trim = s.replaceAll("\\s{2,}", " ").trim();
        String[] s1 = trim.split(" ");
        return s1;
    }

    private void run(JCommander jCommander) {
        for (BaseCommander value : commanders.values()) {
            value.run(jCommander);
        }
    }
}