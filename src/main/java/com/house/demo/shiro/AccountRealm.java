package com.house.demo.shiro;


import com.house.demo.common.AuthConstant;
import com.house.demo.utils.JwtUtil;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.model.HouseUser;
import lombok.extern.slf4j.Slf4j;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author xjj
 */
@Slf4j

public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    HouseUserMapper userMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("执行认证逻辑");

        String token = (String) authenticationToken.getPrincipal();

        String userName = jwtUtil.getUserNameFromToken(token);

        if(userName==null){
            throw new AuthenticationException(AuthConstant.TOKEN_BLANK);
        }

        HouseUser user = userMapper.getUserByName(userName);

        if(user==null){
            throw new AuthenticationException(AuthConstant.TOKEN_INVALID);
        }else if(!(jwtUtil.validateToken(token,user))){
            throw new AuthenticationException(AuthConstant.TOKEN_EXPIRE);
        }

        return new SimpleAuthenticationInfo(userName, token, "auth_realm");
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

//        log.info("用户角色权限认证");
//
//
//        Object o = principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//
//        return info;

        String principal = (String) principalCollection.getPrimaryPrincipal();



        System.out.println("这是调用AuthorizationInfo"+principal);
        return null;

    }
}
