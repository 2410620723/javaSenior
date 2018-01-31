package com.java.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DoFormServlet", urlPatterns = {"/doFormServlet"})
public class DoFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean isToken = isToken(request);
        if (isToken == true) {
            response.getWriter().write("请不要重复提交");
            System.out.println("请不要重复提交");
            return;
        }
        request.getSession().removeAttribute("token");
        System.out.println("处理用户请求");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public boolean isToken(HttpServletRequest request) {
        String currentToken = request.getParameter("token");
        if (currentToken == null)
            return true;
        String sessionToken = (String) request.getSession().getAttribute("token");
        if (sessionToken == null)
            return true;
        if (!currentToken.equals(sessionToken))
            return true;
        return false;
    }
}
