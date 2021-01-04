package cn.liutianyou.todo;

import cn.liutianyou.todo.commander.BaseCommander;
import cn.liutianyou.todo.exception.CommonException;
import cn.liutianyou.todo.util.Util;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
            try {
            System.out.print(">");
            String s = scanner.nextLine();
            String[] strings = prevDeal(s);
            String[] removeProgramName = new String[strings.length-1];
            if(strings.length==1&&strings[0].trim().equals("")){
                continue;
            }
            if(!strings[0].trim().equals("todo")){
                throw  new CommonException("Please enter the application name at the beginning of the command");
            }else{
                System.arraycopy(strings,1,removeProgramName,0,strings.length-1);
            }
                jc.parse(removeProgramName);
                run(jc);

            }catch (CommonException commonException){
                System.out.println("- "+commonException.getMessage());
            } catch (MissingCommandException missingCommandException) {
                System.out.println("- Unsupported command\"" + missingCommandException.getUnknownCommand() + "\"");
            } catch (com.beust.jcommander.ParameterException parameterException) {
                System.out.println("- Command '"+jc.getParsedCommand()+"' does not support parameter '"+Util.pareFromParameterException(parameterException)+"'");
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