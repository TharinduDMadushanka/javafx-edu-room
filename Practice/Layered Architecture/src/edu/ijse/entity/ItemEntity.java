package edu.ijse.entity;

public class ItemEntity {
    private String itemCode;
    private String description;
    private String pack;
    private Integer qoh;
    private Double unitPrice;

    public ItemEntity() {
    }

    public ItemEntity(String itemCode, String description, String pack, Integer qoh, Double unitPrice) {
        this.itemCode = itemCode;
        this.description = description;
        this.pack = pack;
        this.qoh = qoh;
        this.unitPrice = unitPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public Integer getQoh() {
        return qoh;
    }

    public void setQoh(Integer qoh) {
        this.qoh = qoh;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemEntity{" + "itemCode=" + itemCode + ", description=" + description + ", pack=" + pack + ", qoh=" + qoh + ", unitPrice=" + unitPrice + '}';
    }
}
