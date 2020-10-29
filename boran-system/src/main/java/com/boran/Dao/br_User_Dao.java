package com.boran.Dao;


import com.boran.Entity.br_User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * @description
 * @author: Specime
 * @date:2020/9/9
 */
@Repository
public interface br_User_Dao extends JpaRepository<br_User, Integer>/*, JpaSpecificationExecutor<br_User> */ {

    @Query(value = "select a from br_User a where a.uname =?1")
    List<br_User> getbr_user(String name);

    //@Modifying
    @Query(value = "select count(a.coid) from br_User a where a.uuser= :#{#user.uuser} and a.upassword= :#{#user.upassword}")
    int log(br_User user);

   /* @SQLInsert(sql = "into `jcb_lottery_data`(type,number,`data`,time) VALUES(?1,?2,?3,?4)")
    int insert(int type,String number,String data,String time);*/
}
