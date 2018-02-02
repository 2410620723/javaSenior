package com.java.regExp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpDemo {
    public static void main(String[] args) {
        String qq = "1043509962";
        System.out.println(checkQQ(qq));
        String findString = "baike.xsoftlab.net";
        System.out.println(findStr(findString));
    }

    /**
     * 校验QQ号
     * 需求1：5-15位；需求2：全是数字；需求3：不能以0开头
     */
    private static boolean checkQQ(String qq) {
        String regExp = "[1-9]\\d{4,14}";
        return qq.matches(regExp);
    }

    private static String findStr(String str) {
        String regExp = "baike";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
    }

    @Test
    public void testGroup() {
        String str = "营业时间： 11:30-21:30 修改 分类标签： 无线上网(8) 可以刷卡(7) 朋友聚餐(5) 家庭聚会(5) 商务宴请(4) 情侣约会(4) 可自带酒水(2)";
        Pattern p = Pattern.compile("\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.println(m.groupCount());// 0
            System.out.println(m.group());// 11:30-21:30
            System.out.println(m.start());// 6
        }
    }
    @Test
    public void testGetString() {
        String string = "da jia hao,ming tian zhou mo le!";
        // 将连续三个的字符获取出来
        String regExp = "\\b[a-z]{3}\\b";
        // 将正则表达式规则封装到对象里面，并返回一个对象
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void testSplit() {
        String string = "zhangsanyyyyylisi";
        String regExp = "(.)\\1+";
        String[] strings = string.split(regExp);
        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void testReplace() {
        // 将叠词替换成一个
        String string = "zhangsanyyyyylisidddd";
        String s = string.replaceAll("(.)\\1+","$1");
        System.out.println(s);
        // 150****2312
        String number = "15056542312";
        number = number.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println(number);
    }

    /**
     * 治疗结巴
     */
    @Test
    public void interview1() {
        String string = "我我...我要...学学学...编...编编...程";
        string = string.replaceAll("\\.+","");
        string = string.replaceAll("(.)\\1+","$1");
        System.out.println(string);
    }

    /**
     * IP 排序
     */
    @Test
    public void testIP() {

    }

    /**
     * 邮箱校验
     * 用户名@公司名.com.cn
     */
    @Test
    public void checkEmail() {
        String regExp = "[a-zA-Z0-9_]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{1,3})+";
//        regExp = "\\w+@\\w+(\\.\\w+)+";
        String email1 = "oauser1@cm-inv.com";
        String email2 = "2410620723@qq.com";

        System.out.println(email1.matches(regExp));
        System.out.println(email2.matches(regExp));
    }
}
