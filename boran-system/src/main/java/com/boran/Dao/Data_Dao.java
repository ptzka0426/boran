package com.boran.Dao;

import com.boran.Entity.jcb_lottery_data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author LT
 * @create 2020-10-27 15:04
 */

//继承CrudRepository和JpaRepository都可
@Repository
public interface Data_Dao<T> extends CrudRepository<jcb_lottery_data, String> {
    //Object lists(String time);
    //List<jcb_lottery_data> haoma();
}
