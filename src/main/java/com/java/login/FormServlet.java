package com.java.login;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@WebServlet(name = "FormServlet", urlPatterns = {"/formServlet"})
public class FormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String token = TokenProcessor.getInstance().generateToken();
        request.getSession().setAttribute("token", token);

        request.getRequestDispatcher("jsp/form.jsp").forward(request, response);
    }
}

/**
 * 令牌，为了保证随机数的唯一性我们使用单例模式。
 */
class TokenProcessor {
    public static final TokenProcessor instance = new TokenProcessor();
    private TokenProcessor() {

    }
    public static TokenProcessor getInstance() {
        return instance;
    }
    // 产生一个随机方法
    public String generateToken() {
        //构建一个随机数
        String token = System.currentTimeMillis() + new Random(999999999).nextInt() + "";
        // 拿到一个MD5
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] md5 = messageDigest.digest(token.getBytes());
            //base64编码
            // 原理：将每三个字节变成四个字节，不够八位的在前面补0，数值最小是0、最大是63，因此成为base64
            // 然后根据它自定义的码表：0-a、1-b、2-c……
            // 在网络传输中base64的码表不包含开始、结束的传输标志符，因此可以我完整的传输网络内容。
            BASE64Encoder encoder = new BASE64Encoder();
            result = encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
