package com.java.upload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "PartServlet", urlPatterns = {"/partServlet"})
@MultipartConfig
public class PartServlet extends HttpServlet {
    private File uploadFile;
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        uploadFile = new File(context.getRealPath("/partUpload"));
        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        Part part = request.getPart("partFile");
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        File uploadFile = new File(request.getSession().getServletContext().getRealPath("/partUpload"));
        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }
        part.write(request.getSession().getServletContext().getRealPath("/partUpload") + "/" + fileName);
        request.setAttribute("imgSrc", "/partUpload/" + fileName);
        request.getRequestDispatcher("/jsp/upload/upload.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
