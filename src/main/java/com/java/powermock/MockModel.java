package com.java.powermock;

public class MockModel extends Hi {
    private Kitty kitty;
    public MockModel(){}
    public void mockParentMethod() {
        super.mockParentMethod();
        System.out.println("mockSuperMethod MockModel");
    }
    public void mockNonStaticMethod() {
        String name = kitty.getName();
        System.out.println("mockNonStaticMethod" + name);
    }
    public void mockStaticMethod() {
        String name = Kitty.getName2();
        System.out.println("mockStaticMethod" + name);
    }

    public void partialMock() {
        _partialMock();
        System.out.println("partialMock");
    }

    private void _partialMock() {
        System.out.println("method3");
    }

    public void mockVoidMethod() {
        kitty.say3();
        System.out.println("partialMock");
    }

    public Kitty getKitty() {
        return kitty;
    }

    public void setKitty(Kitty kitty) {
        this.kitty = kitty;
    }
}
class Hi {
    public void mockParentMethod() {
        System.out.println("mockSuperMethod Hi");
    }
}
class Kitty {
    public String getName() {
        return "Kitty";
    }

    public static String getName2() {
        return "Kitty";
    }

    public void say3() {
        System.out.println("say3");
    }
}
