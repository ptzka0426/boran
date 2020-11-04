package com.boran.Controller;

import com.alibaba.fastjson.JSONObject;
import com.boran.Entity.br_User;
import com.boran.Entity.jcb_lottery_data;
import com.boran.Server.Data_Service;
import com.boran.Server.br_UserService;
import com.boran.Util.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String txffc() throws Exception {
        Thread.sleep(5000);
        Calendar calendar = Calendar.getInstance();
        String yyyy = new SimpleDateFormat("yyyy").format(calendar.getTime());
        String MM = new SimpleDateFormat("MM").format(calendar.getTime());
        String dd = new SimpleDateFormat("dd").format(calendar.getTime());
        String shi = new SimpleDateFormat("HH").format(calendar.getTime());
        String fen = new SimpleDateFormat("mm").format(calendar.getTime());
        System.out.println(yyyy + MM + dd + "-" + shi + ":" + fen + ":00");
        int sum = (Integer.parseInt(shi) * 60) + Integer.parseInt(fen);//当前期数
        String qihao;
        if ((sum + "").length() == 4) {
            qihao = yyyy + MM + dd + "-" + sum;
        } else {
            qihao = yyyy + MM + dd + "-0" + sum;
        }
        Map<String, String> createMap = new HashMap<String, String>();
        createMap.put("authuser", "*****");
        createMap.put("authpass", "*****");
        createMap.put("orgkey", "****");
        createMap.put("orgname", "****");
        String result = new HttpClientService().doPost("https://www.manycai.tj/v1/api/lottery/issue/get/" + qihao + "/txffc.json", createMap, "utf-8");
        //JSONObject jsonObject= JSONObject.parseObject(result.toString().substring(1, result.toString().length() - 1)); //处理单一效果
        //List<Object> list = JSONArray.parseArray(result.toString()); //处理多个json
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result.toString().substring(1, result.toString().length() - 1));
        jcb_lottery_data j = new jcb_lottery_data();
        j.setType(117);
        j.setNumber(jsonObject.get("issue").toString());
        j.setData(jsonObject.get("code").toString());
        j.setTime(yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00");
        Data_Service.insertJcb_lottery_data(j);
        return "分分彩持续为您开奖中";
    }
    //测试
    public static void main(String[] args) throws Exception {
        //计算当前开奖
        Calendar calendar = Calendar.getInstance();
        String yyyy = new SimpleDateFormat("yyyy").format(calendar.getTime());
        String MM = new SimpleDateFormat("MM").format(calendar.getTime());
        String dd = new SimpleDateFormat("dd").format(calendar.getTime());
        String shi = new SimpleDateFormat("HH").format(calendar.getTime());
        String fen = new SimpleDateFormat("mm").format(calendar.getTime());
        System.out.println(yyyy + MM + dd + "-" + shi + ":" + fen + ":00");
        int sum = (Integer.parseInt(shi) * 60) + Integer.parseInt(fen);//当前期数
        String qihao;
        if ((sum + "").length() == 4) {
            qihao = yyyy + MM + dd + "-" + sum;
        } else {
            qihao = yyyy + MM + dd + "-0" + sum;
        }
        Map<String, String> createMap = new HashMap<String, String>();
        createMap.put("authuser", "*****");
        createMap.put("authpass", "*****");
        createMap.put("orgkey", "****");
        createMap.put("orgname", "****");
        String result = new HttpClientService().doPost("https://www.manycai.tj/v1/api/lottery/issue/get/" + qihao + "/txffc.json", createMap, "utf-8");
        //JSONObject jsonObject= JSONObject.parseObject(result.toString().substring(1, result.toString().length() - 1)); //处理单一效果
        //List<Object> list = JSONArray.parseArray(result.toString()); //处理多个json
        System.out.println(result);
        JSONObject object = JSONObject.parseObject(result.toString().substring(1, result.toString().length() - 1));
        System.out.println(object.get("issue") + "--" + object.get("code") + "--" + yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00");
        System.out.println("GET返回信息：" + object);
    }

    @GetMapping(value = "/cha")
    public Object cha() {
        return Data_Service.lists("66");
    }
}

