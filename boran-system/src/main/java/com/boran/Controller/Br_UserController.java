package com.boran.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boran.Entity.br_User;
import com.boran.Entity.jcb_lottery_data;
import com.boran.Server.Data_Service;
import com.boran.Server.br_UserService;
import com.boran.Util.HttpClientService;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.apache.http.NameValuePair;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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

    @GetMapping(value = "/qqtxffc")
    public String qqtxffc() throws Exception {
        //Thread.sleep(10000);
        Object[] params = new Object[]{"param1", "param2"};
        /**
         * 参数名
         */
        Object[] values = new Object[]{"value1", "value2"};
        /**
         * 获取参数对象
         */
        List<NameValuePair> paramsList = HttpClientService.getParams(params, values);
        /**
         * 发送get    http://free.duocaiapi.com/K25cec7b59e320b/ptxffc-1.json
         */
        Object result = HttpClientService.sendGet("http://qniupin.com/api/tencent/onlineim", paramsList);
        //JSONObject jsonObject= JSONObject.parseObject(result.toString().substring(1, result.toString().length() - 1)); //处理单一效果
        List<Object> list = JSONArray.parseArray(result.toString()); //处理多个json
        /*System.out.println(result);
        System.out.println(list.get(0));*/
        JSONObject jsonObject = JSONObject.parseObject(list.get(0).toString());
        jcb_lottery_data j = new jcb_lottery_data();
        j.setType(117);
        String data = Integer.parseInt(jsonObject.get("onlinenumber").toString()) % 10 + "";
        //j.setNumber(jsonObject.get("issue").toString());
        j.setData(jsonObject.get("onlinenumber").toString());
        j.setTime(data + "" + jsonObject.get("onlinenumber").toString().substring(5));
        //Data_Service.insertJcb_lottery_data(j);
        return "分分彩持续为您开奖中";
    }

    @GetMapping(value = "/test")
    public Object test() {
        //ThreadInsertHaoMa();
        return Data_Service.Selecthaoma("2020-11-15 02:17:00");
    }

    @GetMapping(value = "/txffc")
    public String txffc() throws Exception {
       /* Thread.sleep(10000);
        Calendar calendar = Calendar.getInstance();
        String yyyy = new SimpleDateFormat("yyyy").format(calendar.getTime());
        String MM = new SimpleDateFormat("MM").format(calendar.getTime());
        String dd = new SimpleDateFormat("dd").format(calendar.getTime());
        String shi = new SimpleDateFormat("HH").format(calendar.getTime());
        String fen = new SimpleDateFormat("mm").format(calendar.getTime());
        int sum;
        if (shi.equals("00") && fen.equals("00")) { //修改非部署
            sum = 1440;//当前期数
        } else {
            sum = (Integer.parseInt(shi) * 60) + Integer.parseInt(fen);//当前期数
        }
        String qihao;
        if ((sum + "").length() == 1) {
            qihao = yyyy + MM + dd + "-000" + sum;
        } else if ((sum + "").length() == 2) {
            qihao = yyyy + MM + dd + "-00" + sum;
        } else if ((sum + "").length() == 3) {
            qihao = yyyy + MM + dd + "-0" + sum;
        } else {
            if (sum == 1440) {
                *//*日期最后一月1440可能出错*//*
                String tian = (calendar.get(Calendar.DATE) - 1) + "";
                if (tian.length() == 1) {
                    qihao = yyyy + MM + "0" + tian + "-" + sum;
                } else {
                    qihao = yyyy + MM + tian + "-" + sum;
                }

            } else {
                qihao = yyyy + MM + dd + "-" + sum;
            }
        }
        System.out.println(yyyy + MM + dd + "-" + shi + ":" + fen + ":00");
        System.out.println(qihao);
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
        //System.out.println(object.get("issue") + "--" + object.get("code") + "--" + yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00");
        //System.out.println("GET返回信息：" + object);
        jcb_lottery_data j = new jcb_lottery_data();
        j.setType(117);
        j.setNumber(jsonObject.get("issue").toString());
        j.setData(jsonObject.get("code").toString());
        j.setTime(yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00");
        Data_Service.insertJcb_lottery_data(j);*/
        ThreadInsertHaoMa();

        return "分分彩持续为您开奖中";
    }

    /*Guava 重试*/
    private Callable<Boolean> InsertReimAgentsCall = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            Calendar calendar = Calendar.getInstance();
            String yyyy = new SimpleDateFormat("yyyy").format(calendar.getTime());
            String MM = new SimpleDateFormat("MM").format(calendar.getTime());
            String dd = new SimpleDateFormat("dd").format(calendar.getTime());
            String shi = new SimpleDateFormat("HH").format(calendar.getTime());
            String fen = new SimpleDateFormat("mm").format(calendar.getTime());
            int sum;
            if (shi.equals("00") && fen.equals("00")) { //修改非部署
                sum = 1440;//当前期数
            } else {
                sum = (Integer.parseInt(shi) * 60) + Integer.parseInt(fen);//当前期数
            }
            String qihao;
            if ((sum + "").length() == 1) {
                qihao = yyyy + MM + dd + "-000" + sum;
            } else if ((sum + "").length() == 2) {
                qihao = yyyy + MM + dd + "-00" + sum;
            } else if ((sum + "").length() == 3) {
                qihao = yyyy + MM + dd + "-0" + sum;
            } else {
                if (sum == 1440) {
                    /*日期最后一月1440可能出错*/
                    String tian = (calendar.get(Calendar.DATE) - 1) + "";
                    if (tian.length() == 1) {
                        qihao = yyyy + MM + "0" + tian + "-" + sum;
                    } else {
                        qihao = yyyy + MM + tian + "-" + sum;
                    }

                } else {
                    qihao = yyyy + MM + dd + "-" + sum;
                }
            }
            //查询数据库的数据避免重复
            //System.out.println(o.getTime());
            /*判断为相同时间退出 ---------------------------------------------------------------------------改成查询当前时间有误数据返回1或2*/
            /*if ((yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00").equals(o.getTime())) {*/
            if (Data_Service.Selecthaoma((yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00")) == 1) {
                System.out.println(yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00" + "-数据已存在，结束退出！");
                return true;
            } else {
                //System.out.println(yyyy + MM + dd + "-" + shi + ":" + fen + ":00");
                //System.out.println(qihao);
                Map<String, String> createMap = new HashMap<String, String>();
                createMap.put("authuser", "*****");
                createMap.put("authpass", "*****");
                createMap.put("orgkey", "****");
                createMap.put("orgname", "****");
                String result = new HttpClientService().doPost("https://www.manycai.tj/v1/api/lottery/issue/get/" + qihao + "/txffc.json", createMap, "utf-8");
                JSONObject jsonObject;
                System.out.println(result);
                try {
                    jsonObject = JSONObject.parseObject(result);
                } catch (Exception e) {
                    jsonObject = JSONObject.parseObject(result.toString().substring(1, result.toString().length() - 1));
                }
                String zhuangtai = "true";
                try {
                    zhuangtai = jsonObject.get("satus").toString();//成功返回false，表示过去失败
                } catch (Exception e) {
                    zhuangtai = "true";//异常表示拿取到值
                }
                if (zhuangtai.equals("false")) {
                    System.out.println(yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00" + "-未开奖,退出！");
                    return false;
                } else {
                    jcb_lottery_data j = new jcb_lottery_data();
                    j.setType(117);
                    j.setNumber(jsonObject.get("issue").toString());
                    j.setData(jsonObject.get("code").toString());
                    j.setTime(yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00");
                    Data_Service.insertJcb_lottery_data(j);
                    System.out.println(yyyy + "-" + MM + "-" + dd + " " + shi + ":" + fen + ":00" + ":00-成功");
                    return true;
                }
            }
        }
    };

    public void ThreadInsertHaoMa() throws InterruptedException {
        Retryer<Boolean> retryer = RetryerBuilder
                .<Boolean>newBuilder()
                //抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。
                .retryIfException()
                //返回false也需要重试
                .retryIfResult(Predicates.equalTo(false))
                //重调策略
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                //尝试次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                .build();

        try {
            retryer.call(InsertReimAgentsCall);
        } catch (ExecutionException e) {
            //e.printStackTrace();
            //System.out.println("1");
        } catch (RetryException e) {
            //System.out.println("2");
            //logger.error( "更新可代理报销人异常,需要发送提醒邮件" );
        } finally {
            Thread.sleep(0);
        }
    }

    @GetMapping(value = "/cha")
    public Object cha() {
        System.gc();//回收
        return "GC回收";//Data_Service.lists("66");
    }
}

