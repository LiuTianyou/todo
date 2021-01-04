package cn.liutianyou.todo.util;

import com.beust.jcommander.MissingCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static final Pattern parameterPatter = Pattern.compile(".+'(.+)'.+");

    public static  String pareFromParameterException(com.beust.jcommander.ParameterException parameterException){
        String message = parameterException.getMessage();

        Matcher matcher =parameterPatter.matcher(message);
        matcher.matches();
        return matcher.group(1);
    }

    public static void main(String[] args) {

        System.out.println(pareFromParameterException(new MissingCommandException("111'--p'1111","js")));;

    }

}
