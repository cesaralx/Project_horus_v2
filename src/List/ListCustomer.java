/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.CustomerGetway
 * @author alexi
 */
public class ListCustomer {
    
    /**
     *
     */
    public String id;

    /**
     *
     */
    public String customerName;

    /**
     *
     */
    public String customerContNo;

    /**
     *
     */
    public String customerAddress;

    /**
     *
     */
    public String totalBuy;

    /**
     *
     */
    public String addBy;

    /**
     *
     */
    public String addedDate;

    /**
     *
     * @param id
     * @param customerName
     * @param customerContNo
     * @param customerAddress
     * @param totalBuy
     * @param addBy
     * @param addedDate
     */
    public ListCustomer(String id, String customerName, String customerContNo, String customerAddress, String totalBuy, String addBy, String addedDate) {
        this.id = id;
        this.customerName = customerName;
        this.customerContNo = customerContNo;
        this.customerAddress = customerAddress;
        this.totalBuy = totalBuy;
        this.addBy = addBy;
        this.addedDate = addedDate;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
    public String getCustomerContNo() {
        return customerContNo;
    }

    /**
     *
     * @param customerContNo
     */
    public void setCustomerContNo(String customerContNo) {
        this.customerContNo = customerContNo;
    }

    /**
     *
     * @return
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     *
     * @return
     */
    public String getTotalBuy() {
        return totalBuy;
    }

    /**
     *
     * @param totalBuy
     */
    public void setTotalBuy(String totalBuy) {
        this.totalBuy = totalBuy;
    }

    /**
     *
     * @return
     */
    public String getAddBy() {
        return addBy;
    }

    /**
     *
     * @param addBy
     */
    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    /**
     *
     * @return
     */
    public String getAddedDate() {
        return addedDate;
    }

    /**
     *
     * @param addedDate
     */
    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
    
    
    
}
