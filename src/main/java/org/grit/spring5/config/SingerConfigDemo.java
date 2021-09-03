package org.grit.spring5.config;

import org.grit.spring5.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

import static org.grit.spring5.Singer.getBean;

/**
 * @author Grit
 * @date 2021-09-03
 */
public class SingerConfigDemo {

    @Configuration
    static class SingerConfig {

        @Lazy
        @Bean(initMethod = "init")
        Singer singerOne() {
            Singer singerOne = new Singer();
            singerOne.setName("John Mayer");
            singerOne.setAge(39);
            return singerOne;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singerTwo() {
            Singer singerTwo = new Singer();
            singerTwo.setAge(72);
            return singerTwo;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singerThree() {
            Singer singerThree = new Singer();
            singerThree.setName("John Butler");
            return singerThree;
        }
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(SingerConfig.class);

        getBean(ctx, "singerOne");
        getBean(ctx, "singerTwo");
        getBean(ctx, "singerThree");
    }
}
