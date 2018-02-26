/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import javafx.beans.property.SimpleStringProperty;

/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.CatagoryGetway
 * @author alexi
 */
public class ListCatagory {

    /**
     *
     */
    public String id;

    /**
     *
     */
    public String catagoryName;

    /**
     *
     */
    public String catagoryDescription;

    /**
     *
     */
    public String brandId;

    /**
     *
     */
    public String supplyerId;

    /**
     *
     */
    public String creatorId;

    /**
     *
     */
    public String date;

    /**
     *
     * @param id
     * @param catagoryName
     * @param catagoryDescription
     * @param brandId
     * @param supplyerId
     * @param creatorId
     * @param date
     */
    public ListCatagory(String id, String catagoryName, String catagoryDescription, String brandId, String supplyerId, String creatorId, String date) {
        this.id = id;
        this.catagoryName = catagoryName;
        this.catagoryDescription = catagoryDescription;
        this.brandId = brandId;
        this.supplyerId = supplyerId;
        this.creatorId = creatorId;
        this.date = date;
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
    public String getCatagoryName() {
        return catagoryName;
    }

    /**
     *
     * @param catagoryName
     */
    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    /**
     *
     * @return
     */
    public String getCatagoryDescription() {
        return catagoryDescription;
    }

    /**
     *
     * @param catagoryDescription
     */
    public void setCatagoryDescription(String catagoryDescription) {
        this.catagoryDescription = catagoryDescription;
    }

    /**
     *
     * @return
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     *
     * @param brandId
     */
    public void setBrandId(String brandId) {
        this.brandId = brandId;
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
    public String getCreatorId() {
        return creatorId;
    }

    /**
     *
     * @param creatorId
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
}