package com.java.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest({Kitty.class})
@RunWith(PowerMockRunner.class)
public class MockTest {
    /**
     * mock 非静态方法
     */
    @Test
    public void testMockNonStaticMethod() {
        MockModel mockModel = new MockModel();
        Kitty kitty = PowerMockito.mock(Kitty.class);
        mockModel.setKitty(kitty);
        PowerMockito.when(kitty.getName()).thenReturn("true");
        mockModel.mockNonStaticMethod();
    }

    /**
     * mock 静态方法
     * 首先需要PrepareForTest导入静态方法的类
     */
    @Test
    public void testMockStaticMethod() {
        MockModel mockModel = new MockModel();
        PowerMockito.mockStatic(Kitty.class);
        PowerMockito.when(Kitty.getName2()).thenReturn(" test");
        mockModel.mockStaticMethod();
    }

    /**
     * 如果只想mock一个类里面的个别方法，可以使用spy方法
     */
    @Test
    public void testPartialMock() {
        MockModel mockModel = PowerMockito.spy(new MockModel());
        try {
            PowerMockito.doReturn("test").when(mockModel, MemberMatcher
                    .method(MockModel.class, "_partialMock")).withNoArguments();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * mock void 方法
     */
    @Test
    public void testMockVoidMethod() {
        MockModel mockModel = new MockModel();
        Kitty kitty = PowerMockito.mock(Kitty.class);
        mockModel.setKitty(kitty);
        PowerMockito.doNothing().when(kitty).say3();
        mockModel.mockVoidMethod();
    }

}
