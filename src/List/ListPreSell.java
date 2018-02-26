/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

/**
 *  el proposito es retornar todos los valoes puestos
 * @see Actions.SellCartGetway
 * @author alexi
 */
public class ListPreSell {

    String Id;
    String productID;
    String customerID;
    String pursesPrice;
    String sellPrice;
    String oldQuantity;
    String quantity;
    String totalPrice;
    String pursrsDate;
    String warrentyVoidDate;
    String sellerID;
    String sellDate;

    /**
     *
     * @param Id
     * @param productID
     * @param customerID
     * @param pursesPrice
     * @param sellPrice
     * @param oldQuantity
     * @param quantity
     * @param totalPrice
     * @param pursrsDate
     * @param warrentyVoidDate
     * @param sellerID
     * @param sellDate
     */
    public ListPreSell(String Id, String productID, String customerID, String pursesPrice, String sellPrice, String oldQuantity, String quantity, String totalPrice, String pursrsDate, String warrentyVoidDate, String sellerID, String sellDate) {
        this.Id = Id;
        this.productID = productID;
        this.customerID = customerID;
        this.pursesPrice = pursesPrice;
        this.sellPrice = sellPrice;
        this.oldQuantity = oldQuantity;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.pursrsDate = pursrsDate;
        this.warrentyVoidDate = warrentyVoidDate;
        this.sellerID = sellerID;
        this.sellDate = sellDate;
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
    public String getProductID() {
        return productID;
    }

    /**
     *
     * @param productID
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     *
     * @return
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     *
     * @param customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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
