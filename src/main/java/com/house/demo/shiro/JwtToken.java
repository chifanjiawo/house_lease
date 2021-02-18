package com.house.demo.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xjj
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    JwtToken(String jwt){
        this.token = jwt;

    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
