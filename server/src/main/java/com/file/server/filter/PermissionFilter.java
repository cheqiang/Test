package com.file.server.filter;

import com.file.config.RSAConstant;
import com.file.util.RSASignature;
import com.file.util.RSAUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 *
 * 权限校验
 */
public class PermissionFilter implements Filter {

    Logger logger = Logger.getLogger(PermissionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter初始化成功！");
    }

    /**
     * 过滤器 : 权限验证（对客服端签名（x-signature）进行验证）
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("过滤器测试成功！");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //客户端生成的随机值
        String sid = request.getHeader("X-SID");
        //客户端生成的签名
        String signature = request.getHeader("X-Signature");
        try {
            //验签
            if(RSASignature.doCheck(sid, URLDecoder.decode(signature,"utf-8"),RSAConstant.PUBLIC_KEY,"UTF-8")){
                filterChain.doFilter(request,response);
            }else{
                //验证失败
                response.setStatus(403);
                response.getWriter().write("签名验证失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(403);
            response.getWriter().write("签名验证失败！");
        }
    }

    /**
     * 销毁时调用
     */
    public void destroy() {
        System.out.println("filter销毁！");
    }
}
