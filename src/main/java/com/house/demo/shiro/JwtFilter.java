package com.house.demo.shiro;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;
import com.house.demo.common.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xjj
 */
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Throwable throwable = e.getCause()== null ? e : e.getCause();

        MyResult fail = MyResult.fail(throwable.getMessage());

        String json = JSONObject.toJSONString(fail);

        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String s = request.getHeader("Authorization");

        if(StringUtils.isEmpty(s)){
            return null;
        }


        return new JwtToken(s);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String s = request.getHeader("Authorization");

        if(StringUtils.isEmpty(s)){
            return true;
        }else {




        }

        return executeLogin(servletRequest,servletResponse);
    }
}
