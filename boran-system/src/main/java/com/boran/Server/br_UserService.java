package com.boran.Server;

import com.boran.Entity.br_User;

import java.util.List;

/**
 * @description
 * @author: Specime
 * @date:2020/9/9
 */
public interface br_UserService {
    List<br_User> getUser(String name);
}
