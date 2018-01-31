package com.java.test;

public class TestEnum {
    public static void main(String[] args) {
        for (EnumDemo e : EnumDemo.values()) {
            System.out.println(e.toString());
        }
        System.out.println("=======================");
        EnumDemo enumDemo = EnumDemo.TUE;
        switch (enumDemo) {
            case MON:
                System.out.println("周一");
                break;
            case TUE:
                System.out.println("周二");
                break;
            case WED:
                System.out.println("周三");
                break;
            case THU:
                System.out.println("周四");
                break;
            case FRI:
                System.out.println("周五");
                break;
            case SAT:
                System.out.println("周六");
                break;
            case SUN:
                System.out.println("周日");
                break;
            default:
                System.out.println(enumDemo);
        }
    }
}
