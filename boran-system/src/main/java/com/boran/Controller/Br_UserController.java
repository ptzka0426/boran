package com.boran.Controller;

import com.alibaba.fastjson.JSONObject;
import com.boran.Entity.br_User;
import com.boran.Entity.jcb_lottery_data;
import com.boran.Server.Data_Service;
import com.boran.Server.br_UserService;
import com.boran.Server.impl.Br_UserServiceImpl;
import com.boran.Util.HttpRequestUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description
 * @author: Lt
 * @date:2020/9/9
 */
@RestController
public class Br_UserController {

    @Autowired
    private br_UserService br_userService;
    @Autowired
    private Data_Service Data_Service;

    @RequestMapping(value = "/index")
    public HashMap<String, Object> getUser(br_User user) {
        HashMap<String, Object> map = new HashMap<>();
        List<br_User> list = br_userService.getUser("超级管理员");
        map.put("user:", list);
        return map;
    }

    @PostMapping(value = "/login")
    public int logIn(br_User user) {
        return br_userService.log(user);
    }

    @GetMapping(value = "/txffc")
    public String txffc() throws InterruptedException {
        Thread.sleep(8000);
        JSONObject jsonObject = HttpRequestUtil.httpRequest("http://free.duocaiapi.com/K25cec7b59e320b/ptxffc-1.json", "GET", null);
        //System.out.println(jsonObject.get("issue") + "--" + jsonObject.get("code") + "--" + jsonObject.get("opendate"));
        jcb_lottery_data j = new jcb_lottery_data();
        j.setType(117);
        j.setNumber(jsonObject.get("issue").toString());
        j.setData(jsonObject.get("code").toString());
        j.setTime(jsonObject.get("opendate").toString());
        Data_Service.insertJcb_lottery_data(j);
        return "分分彩持续为您开奖中";
    }
    @GetMapping(value = "/cha")
    public Object cha() {
        return Data_Service.lists("66");
    }
}

