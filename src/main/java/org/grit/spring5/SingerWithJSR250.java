package org.grit.spring5;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author Grit
 * @date 2021-09-03
 */
public class SingerWithJSR250 {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 初始化方法的缺陷：不能传入任何参数
     */
    @PostConstruct()
    public void init() {
        System.out.println("Initializing Bean");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "Your must set the age property of any beans of type " + SingerWithJSR250.class
            );
        }
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-content-xml.xml");
        ctx.refresh();

        getBean(ctx, "singerOne");
        getBean(ctx, "singerTwo");
        getBean(ctx, "singerThree");
        ctx.close();
    }

    public static SingerWithJSR250 getBean(ApplicationContext context, String beanName) {
        try {
            SingerWithJSR250 bean = (SingerWithJSR250) context.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex) {
            System.out.println("An error occurred in bean configuration: " + ex.getMessage());
            return null;
        }
    }
}
