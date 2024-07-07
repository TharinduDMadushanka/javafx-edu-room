package edu.ijse.dto;

import java.util.ArrayList;

public class OrderDto {

    private String orederId;
    private String custId;
    private String date;

    private ArrayList<OrderDetailDto> orderDetailDtos;

    public OrderDto() {
    }

    public OrderDto(String orederId, String custId, String date, ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orederId = orederId;
        this.custId = custId;
        this.date = date;
        this.orderDetailDtos = orderDetailDtos;
    }

    public String getOrederId() {
        return orederId;
    }

    public void setOrederId(String orederId) {
        this.orederId = orederId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<OrderDetailDto> getOrderDetailDtos() {
        return orderDetailDtos;
    }

    public void setOrderDetailDtos(ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDetailDtos = orderDetailDtos;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orederId='" + orederId + '\'' +
                ", custId='" + custId + '\'' +
                ", date='" + date + '\'' +
                ", orderDetailDtos=" + orderDetailDtos +
                '}';
    }
}
