package com.boran.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author LT
 * @create 2020-10-27 15:00
 */
@Data
/*@Entity(name = "jcb_data")*/
@Entity
public class jcb_lottery_data {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="type")
    private int type;
    @Column(name="number")
    private String number;
    @Column(name="data")
    private String data;
    @Column(name="time")
    private String time;
}
