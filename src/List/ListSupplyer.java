/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;


/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.SupplyerGetway
 * @author alexi
 */
public class ListSupplyer {
    
    /**
     *
     */
    public String supplyerId;

    /**
     *
     */
    public String supplyerName;

    /**
     *
     */
    public String supplyerPhoneNumber;

    /**
     *
     */
    public String supplyerAddress;

    /**
     *
     */
    public String supplyerDescription;

    /**
     *
     */
    public String creatorId;

    /**
     *
     */
    public String dataOfjoining;

    /**
     *
     * @param supplyerId
     * @param supplyerName
     */
    public ListSupplyer(String supplyerId, String supplyerName) {
        this.supplyerId = supplyerId;
        this.supplyerName = supplyerName;
    }

    /**
     *
     * @param supplyerAddress
     */
    public void setSupplyerAddress(String supplyerAddress) {
        this.supplyerAddress = supplyerAddress;
    }

    /**
     *
     * @return
     */
    public String getSupplyerPhoneNumber() {
        return supplyerPhoneNumber;
    }

    /**
     *
     * @return
     */
    public String getSupplyerAddress() {
        return supplyerAddress;
    }

    /**
     *
     * @return
     */
    public String getSupplyerDescription() {
        return supplyerDescription;
    }

    /**
     *
     * @return
     */
    public String getDataOfjoining() {
        return dataOfjoining;
    }

    /**
     *
     * @param supplyerId
     * @param supplyerName
     * @param supplyerPhoneNumber
     * @param supplyerAddress
     * @param supplyerDescription
     * @param dataOfjoining
     */
    public ListSupplyer(String supplyerId, String supplyerName, String supplyerPhoneNumber, String supplyerAddress, String supplyerDescription, String dataOfjoining) {
        this.supplyerId = supplyerId;
        this.supplyerName = supplyerName;
        this.supplyerPhoneNumber = supplyerPhoneNumber;
        this.supplyerAddress = supplyerAddress;
        this.supplyerDescription = supplyerDescription;
        this.dataOfjoining = dataOfjoining;
    }

    /**
     *
     * @return
     */
    public String getSupplyerId() {
        return supplyerId;
    }

    /**
     *
     * @param supplyerId
     */
    public void setSupplyerId(String supplyerId) {
        this.supplyerId = supplyerId;
    }

    /**
     *
     * @return
     */
    public String getSupplyerName() {
        return supplyerName;
    }

    /**
     *
     * @param supplyerName
     */
    public void setSupplyerName(String supplyerName) {
        this.supplyerName = supplyerName;
    }

}
