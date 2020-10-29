package com.boran.Server.impl;

import com.boran.Dao.Data_Dao;
import com.boran.Entity.jcb_lottery_data;
import com.boran.Server.Data_Service;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author LT
 * @create 2020-10-27 15:09
 */
@Service
public class Data_ServiceImpl implements Data_Service {

    @PersistenceContext //注解获取session
    private EntityManager entitySession;

    @Autowired
    private Data_Dao j_data;

    @Override
    public void insertJcb_lottery_data(jcb_lottery_data data) {
        j_data.save(data);
    }

    @Override
    public void deleteJcb_lottery_data(int id) {
        j_data.deleteById(id + "");
    }

    @Override
    public void updateJcb_lottery_data(jcb_lottery_data data) {
        j_data.save(data);
    }

    @Override
    public List<jcb_lottery_data> getJcb_lottery_data() {
        return (List<jcb_lottery_data>) j_data.findAll();
    }

    @Override
    public Object lists(String time) {
        /*session.sava(Object obj);
        session.delete(Object obj);
        session.update(Object obj);||先查询对应的数据库中的映射对象，setXXX()进行修改，再session.flush()强制提交
        session.get(Class cls,ID id)  ||session.load(Class cls,ID id) */
        Session session = (Session) entitySession.getDelegate();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("SELECT id,number,data,time from  jcb_lottery_data where id=?0");
        query.setParameter(0,Integer.parseInt(time));
        Object o=query.getSingleResult();
        //Object o= session.get(jcb_lottery_data.class,Integer.parseInt(time));
        transaction.commit();
        return o;
    }
}
