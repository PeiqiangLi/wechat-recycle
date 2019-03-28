package com.wechat.recycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 11:33 2019/3/11
 * @Modify By:
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String orderId;

    // 用户openId
    private String citId;

    // 回收站点id
    private Integer recId;

    private Double money;

    // 订单状态：1-待回收，2-交易成功
    private Integer orderState;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    private Integer addressId;

    private String remarks;

    private String delFlag;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCitId() {
        return citId;
    }

    public void setCitId(String citId) {
        this.citId = citId;
    }

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
