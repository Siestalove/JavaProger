package org.example.homework3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class Homework3Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework3Application.class, args);
    }

    @Controller
    public static class IndexController {
        @RequestMapping("/")
        public ModelAndView getIndex() {
            return new ModelAndView("index");
        }
    }

}
