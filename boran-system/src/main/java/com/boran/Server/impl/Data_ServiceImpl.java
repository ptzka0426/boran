package com.boran.Server.impl;

import com.boran.Dao.Data_Dao;
import com.boran.Entity.jcb_lottery_data;
import com.boran.Server.Data_Service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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
    private Data_Dao Data_Dao;

    @Override
    public void insertJcb_lottery_data(jcb_lottery_data data) {
        Data_Dao.save(data);
    }

    @Override
    public void deleteJcb_lottery_data(int id) {
        Data_Dao.deleteById(id + "");
    }

    @Override
    public void updateJcb_lottery_data(jcb_lottery_data data) {
        Data_Dao.save(data);
    }

    @Override
    public List<jcb_lottery_data> getJcb_lottery_data() {
        return (List<jcb_lottery_data>) Data_Dao.findAll();
    }

    //增删改查
        /*session.sava(Object obj);
        session.delete(Object obj);
        session.update(Object obj);||先查询对应的数据库中的映射对象，setXXX()进行修改，再session.flush()强制提交
        session.get(Class cls,ID id)  ||session.load(Class cls,ID id) */

    @Override
    public Object lists(String id) {
        Session session = (Session) entitySession.getDelegate();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT id,number,data,time from  jcb_lottery_data where id=?0");
        query.setParameter(0, Integer.parseInt(id));
        Object o = query.getSingleResult();
        //Object o= session.get(jcb_lottery_data.class,Integer.parseInt(time));
        transaction.commit();
        return o;
    }

    @Override
    public List<jcb_lottery_data> haoma() {
        Session session = (Session) entitySession.getDelegate();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT id,number,data,time from  jcb_lottery_data order by id desc");
        query.setFirstResult(0);//设置最大的limit的开始位置
        query.setMaxResults(1);//设置limit的个数
        List o = query.getResultList();// query.getSingleResult();//.getResultList();
        List<jcb_lottery_data> dcnList = null;
        if (o != null && o.size() > 0) {
            dcnList = new ArrayList<jcb_lottery_data>();
            jcb_lottery_data dcn = new jcb_lottery_data();
            for (int i = 0; i < o.size(); i++) {
                //我在此处遍历返回的结果将数据放到我的实体类jcb_lottery_data里
                Object[] obj = (Object[]) o.get(i);
                if (obj[0] != null) {
                    dcn.setId((Integer) obj[0]);
                }
                if (obj[1] != null) {
                    dcn.setNumber(obj[1].toString());
                }
                if (obj[2] != null) {
                    dcn.setData(obj[2].toString());
                }
                if (obj[3] != null) {
                    dcn.setTime(obj[3].toString());
                }
            }
            dcnList.add(dcn);
        }
        transaction.commit();
        return dcnList;
    }
}
