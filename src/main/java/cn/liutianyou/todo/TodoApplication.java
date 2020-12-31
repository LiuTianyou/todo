package cn.liutianyou.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TodoApplication.class, args);
        Terminal bean = applicationContext.getBean(Terminal.class);
        bean.start();
    }



}
