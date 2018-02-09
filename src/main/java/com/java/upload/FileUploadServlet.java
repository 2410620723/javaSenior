package com.java.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileUploadServlet"})
public class FileUploadServlet extends HttpServlet {
    private File uploadFile;
    @Override
    public void init() throws ServletException {
        super.init();
        uploadFile = new File(getServletContext().getRealPath("/fileUpload"));
        if (!uploadFile.exists())
            uploadFile.mkdir();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        // 创建一个FileItem工厂，设置工厂的大小，和存储临时文件
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        factory.setRepository(uploadFile);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1000000000 * 20);
        String filename = "";
        try {
            List fileItems = upload.parseRequest(request);
            for (Object o : fileItems) {
                FileItem item = (FileItem) o;
                if (item.isFormField()) {
                    continue;
                } else {
                    filename = item.getName();
                    if (filename == null || "".equals(filename) || item.getSize() <= 0) {
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
                    item.write(new File(uploadFile, filename));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<script>parent.fileUploadCallBack('" + request.getSession()
                .getServletContext().getContextPath() +
                "/fileUpload/" + filename + "');</script>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
