package com.boran.Dao;

import com.boran.Entity.br_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @description
 * @author: Specime
 * @date:2020/9/9
 */
@Repository
public interface br_User_Dao extends JpaRepository<br_User,Integer>/*, JpaSpecificationExecutor<br_User> */{

    /*@Query(value = "select b from br_User b where br_User =?1")
    List<br_User> getbr_user(String name);*/

}
