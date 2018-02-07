package com.java.Statically;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "StaticalServlet", urlPatterns = {"/staticalServlet"})
public class StaticalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chapterId = request.getParameter("chapterId");
        System.out.println("chapterId=======" + chapterId);
        if (chapterId != null) {
            String chapterFileName = "jsp/statical/bookChapterRead_" + chapterId + ".htm";
            String chapterFilePath = getServletContext().getRealPath("/") + chapterFileName;
            File chapterFile = new File(chapterFilePath);
            if (chapterFile.exists()) {
                System.out.println("有此静态页面=====");
                response.sendRedirect(chapterFileName);
                return;
            }
            System.out.println("没有此静态页面======");
            request.setAttribute("novelChapter", "测试单节信息");
            request.setAttribute("lastPageId", "lastPageId111");
            request.setAttribute("nextPageId", "nextPageId222");
            //============
            System.out.println("getServletContext()==========//========="+getServletContext());
            try {
                new CreateStaticHTMLPage().createStaticHTMLPage(request, response, getServletContext(),
                        chapterFileName, chapterFilePath, "/jsp/statical/bookRead.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
