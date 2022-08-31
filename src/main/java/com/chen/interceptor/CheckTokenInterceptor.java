package com.chen.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.chen.util.JWTUtils;
import com.chen.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chen
 * @crete 2022-08-30-22:46
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("token");
        if (StrUtil.isBlankIfStr(token)){
            doResponse(response,ResultVO.error("请先登陆"));
            return false;
        }else {
            try {
                JWTUtils.decode(token);
                return true;
            }catch (TokenExpiredException e){
                doResponse(response, ResultVO.error("登陆过期，请重新登陆"));
            }catch (SignatureVerificationException e){
                doResponse(response,ResultVO.error("Token不合理"));
            }catch (Exception e){
                doResponse(response, ResultVO.error("请先登陆"));
            }

        }
        return false;
    }

    private void doResponse(HttpServletResponse response, ResultVO resultVO) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(resultVO);
        out.print(s);
        out.flush();
        out.close();
    }

}
