package com.boran.Server;

import com.boran.Entity.jcb_lottery_data;

import java.util.List;

/**
 * @Author LT
 * @create 2020-10-27 15:06
 */
public interface Data_Service {

    /**
     * 添加
     */
    void insertJcb_lottery_data(jcb_lottery_data data);

    void insert(jcb_lottery_data data) ;
    /**
     * 删除
     */
    void deleteJcb_lottery_data(int id);

    /**
     * 修改
     */
    void updateJcb_lottery_data(jcb_lottery_data data);

    /**
     * 查询
     */
    List<jcb_lottery_data> getJcb_lottery_data();
    //查询匹配的时间数据
    Object lists(String  id);
    //查询最新的号码
    List<jcb_lottery_data> haoma();
    int Selecthaoma(String time);
}
