package com.house.demo.shiro;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.AuthConstant;
import com.house.demo.common.MyResult;
import com.house.demo.utils.ApplicationContextUtil;
import com.house.demo.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author xjj
 */

@Slf4j

public class JwtFilter extends AuthenticatingFilter {

    private JwtUtil jwtUtil;


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");

        if(jwtUtil == null){
            jwtUtil = ApplicationContextUtil.getBean("jwtUtil");
        }

        if(token!=null){

            if(!(jwtUtil.getBlockedToken(token))){

                log.info("执行登录");
                executeLogin(request,response);
                return true;
            }
        }

        log.info("isAccessAllowed拦截");

        return false;

    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        log.info("onAccessDenied拦截");
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().print(JSONObject.toJSONString(MyResult.fail(AuthConstant.AUTHENTICATE_FAIL)));

        return false;
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws AuthenticationException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");

        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }



    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {


        return null;
    }
}
