package com.boran.Dao;

import com.boran.Entity.jcb_lottery_data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author LT
 * @create 2020-10-27 15:04
 */

//继承CrudRepository和JpaRepository都可
@Repository
public interface Data_Dao extends JpaRepository<jcb_lottery_data, String> {
    //List<jcb_lottery_data> lists(String time);
}
