package com.java.Statically;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class CreateStaticHTMLPage {
    public void createStaticHTMLPage(HttpServletRequest request, HttpServletResponse response,
                   ServletContext servletContext, String fileName, String fileFullPath, String jspPath)
            throws Exception {
        response.setContentType("text/html;charset=utf-8");
        // 得到jsp资源
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(jspPath);
        // 用于从ServletOutputStream中接收资源
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 用于从HttpServletResponse中接收资源
        final ServletOutputStream servletOutputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            public void write(byte[] b, int off, int len){
                byteArrayOutputStream.write(b, off, len);
            }
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }
        };
        // 把转换字节流转换成字符流
        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream));
        HttpServletResponse httpServletResponse = new HttpServletResponseWrapper(response){//用于从response获取结果流资源(重写了两个方法)
            public ServletOutputStream getOutputStream(){
                return servletOutputStream;
            }
            public PrintWriter getWriter(){
                return printWriter;
            }
        };
        // 发送结果流
        dispatcher.include(request, httpServletResponse);
        // 刷新缓冲区，把缓冲区的数据输出
        printWriter.flush();
        FileOutputStream fileOutputStream = new FileOutputStream(fileFullPath);
        // 把byteArrayOuputStream中的资源全部写入到fileOuputStream中
        byteArrayOutputStream.writeTo(fileOutputStream);
        fileOutputStream.close();
        response.sendRedirect(fileName);
    }
}
