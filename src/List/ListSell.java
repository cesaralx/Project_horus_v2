/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.SellCartGerway
 * @author alexi
 */
public class ListSell {
    String Id;
    String sellId;
    String productId;
    String productGId;
    String customerId;
    String customerName;
    String pursesPrice;
    String sellPrice;
    String oldQuantity;
    String quantity;
    String totalPrice;
    String pursrsDate;
    String warrentyVoidDate;
    
    String sellerID;
    String sellerName;
    String sellDate;

    /**
     *
     * @param Id
     * @param sellId
     * @param productId
     * @param productGId
     * @param customerId
     * @param customerName
     * @param pursesPrice
     * @param sellPrice
     * @param oldQuantity
     * @param quantity
     * @param totalPrice
     * @param pursrsDate
     * @param warrentyVoidDate
     * @param sellerID
     * @param sellerName
     * @param sellDate
     */
    public ListSell(String Id, String sellId, String productId, String productGId, String customerId, String customerName, String pursesPrice, String sellPrice, String oldQuantity, String quantity, String totalPrice, String pursrsDate, String warrentyVoidDate, String sellerID, String sellerName, String sellDate) {
        this.Id = Id;
        this.sellId = sellId;
        this.productId = productId;
        this.productGId = productGId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.pursesPrice = pursesPrice;
        this.sellPrice = sellPrice;
        this.oldQuantity = oldQuantity;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.pursrsDate = pursrsDate;
        this.warrentyVoidDate = warrentyVoidDate;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellDate = sellDate;
    }

    /**
     *
     * @return
     */
    public String getSellId() {
        return sellId;
    }

    /**
     *
     * @param sellId
     */
    public void setSellId(String sellId) {
        this.sellId = sellId;
    }
    
    /**
     *
     * @return
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     */
    public String getProductId() {
        return productId;
    }

    /**
     *
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     *
     * @return
     */
    public String getProductGId() {
        return productGId;
    }

    /**
     *
     * @param productGId
     */
    public void setProductGId(String productGId) {
        this.productGId = productGId;
    }

    /**
     *
     * @return
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return
     */
    public String getPursesPrice() {
        return pursesPrice;
    }

    /**
     *
     * @param pursesPrice
     */
    public void setPursesPrice(String pursesPrice) {
        this.pursesPrice = pursesPrice;
    }

    /**
     *
     * @return
     */
    public String getSellPrice() {
        return sellPrice;
    }

    /**
     *
     * @param sellPrice
     */
    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     *
     * @return
     */
    public String getOldQuantity() {
        return oldQuantity;
    }

    /**
     *
     * @param oldQuantity
     */
    public void setOldQuantity(String oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    /**
     *
     * @return
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public String getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     * @param totalPrice
     */
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     *
     * @return
     */
    public String getPursrsDate() {
        return pursrsDate;
    }

    /**
     *
     * @param pursrsDate
     */
    public void setPursrsDate(String pursrsDate) {
        this.pursrsDate = pursrsDate;
    }

    /**
     *
     * @return
     */
    public String getWarrentyVoidDate() {
        return warrentyVoidDate;
    }

    /**
     *
     * @param warrentyVoidDate
     */
    public void setWarrentyVoidDate(String warrentyVoidDate) {
        this.warrentyVoidDate = warrentyVoidDate;
    }

    /**
     *
     * @return
     */
    public String getSellerID() {
        return sellerID;
    }

    /**
     *
     * @param sellerID
     */
    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    /**
     *
     * @return
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     *
     * @param sellerName
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     *
     * @return
     */
    public String getSellDate() {
        return sellDate;
    }

    /**
     *
     * @param sellDate
     */
    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }
    
    
}
