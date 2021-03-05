package com.house.demo.controller.BackController;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xjj
 */
@RestController
@RequestMapping("admin")
@RequiresRoles("admin")

public class AdminController {



}
