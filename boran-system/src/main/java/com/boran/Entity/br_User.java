package com.boran.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @description
 * @author: Specime
 * @date:2020/9/9 用户表
 **/
@Data                    //生成get set方法
@NoArgsConstructor      //自动生成无参数构造函数。
@AllArgsConstructor     // 自动生成全参数构造函数。
@Entity                  //注解实体类
@Table(name = "br_user")
public class br_User implements Serializable {
    //持久化参数
    private static final long serialVersionUID = -8447170587360305408L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;//用户id
    private String uuser;//用户名
    private String upassword;//用户名密码
    private String uname;//姓名
    private Integer coid;//公司id
    private Integer did;//部门id
    private String uphone;//电话号码
    private String uidcard;//身份证号码
    private char uflag;//删除标志
    private Date uupdate;//日期
}
