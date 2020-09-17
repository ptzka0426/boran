package com.boran.Controller;

import com.boran.Entity.br_User;
import com.boran.Server.br_UserService;
import com.boran.Server.impl.Br_UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;

/**
 * @description
 * @author: Specime
 * @date:2020/9/9
 */
@RestController
public class Br_UserController {
    @Autowired
    private br_UserService br_userService;

    @RequestMapping(value = "/index")
    public HashMap<String, Object> getUser(br_User user) {
        HashMap<String, Object> map = new HashMap<>();
        List<br_User> list = br_userService.getUser("超级管理员");
        map.put("user:", list);
        return map;
    }
}
