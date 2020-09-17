package com.boran.Server.impl;

import com.boran.Dao.br_User_Dao;
import com.boran.Entity.br_User;
import com.boran.Server.br_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @author: Specime
 * @date:2020/9/9
 */
@Service
public class Br_UserServiceImpl implements br_UserService {
    @Autowired
    private br_User_Dao br_user_dao;
    @Override
    public List<br_User> getUser(String name) {
        List<br_User> list = br_user_dao.getbr_user(name);/*.findAll();*//*getbr_user(name);*/
        return list;
    }

    /*登录*/
    @Override
    public int log(br_User user) {
        return br_user_dao.log(user);
    }
}
