package com.eazydineapp.menu;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.eazydineapp.menu.controller"})
@ComponentScan
public class MenuApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MenuApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}