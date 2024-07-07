package edu.ijse.dto;

public class OrderDetailDto {
    private String orderId;
    private String ItemCode;
    private int qty;
    private int discount;

    public OrderDetailDto() {
    }

    public OrderDetailDto(String orderId, String itemCode, int qty, int discount) {
        this.orderId = orderId;
        ItemCode = itemCode;
        this.qty = qty;
        this.discount = discount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetailDto{" +
                "orderId='" + orderId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                '}';
    }
}
