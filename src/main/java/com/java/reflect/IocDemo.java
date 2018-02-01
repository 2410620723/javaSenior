package com.java.reflect;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class IocDemo {
    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("D:\\Program Files\\JetBrains\\workspace\\javaSenior\\src\\main\\resources\\config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String className = properties.getProperty("className");
            System.out.println(className);
            List arrayList = (List) Class.forName(className).newInstance();
            Person person1 = new Person();
            Person person2 = new Person();
            person1.setName("test1");
            person1.setAge(21);
            person2.setName("test2");
            person2.setAge(22);
            arrayList.add(person1);
            arrayList.add(person2);
            System.out.println(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
