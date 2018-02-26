package List;

/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.CurrentProductGetway
 * @author alexi
 */
public class ListProduct {

    /**
     *
     */
    public String id;

    /**
     *
     */
    public String productId;

    /**
     *
     */
    public String productName;

    /**
     *
     */
    public String quantity;

    /**
     *
     */
    public String description;

    /**
     *
     */
    public String suppliedBy;

    /**
     *
     */
    public String brand;

    /**
     *
     */
    public String catagory;

    /**
     *
     */
    public String unit;

    /**
     *
     */
    public String pursesPrice;

    /**
     *
     */
    public String sellPrice;

    /**
     *
     */
    public String discountInCash;

    /**
     *
     */
    public String discountInPersent;

    /**
     *
     */
    public String rma;

    /**
     *
     */
    public String user;

    /**
     *
     */
    public String date;

    /**
     *
     * @param id
     * @param productId
     * @param productName
     * @param quantity
     * @param description
     * @param suppliedBy
     * @param brand
     * @param catagory
     * @param unit
     * @param pursesPrice
     * @param sellPrice
     * @param rma
     * @param user
     * @param date
     */
    public ListProduct(String id, String productId, String productName, String quantity, String description, String suppliedBy, String brand, String catagory, String unit, String pursesPrice, String sellPrice, String rma, String user, String date) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.description = description;
        this.suppliedBy = suppliedBy;
        this.brand = brand;
        this.catagory = catagory;
        this.unit = unit;
        this.pursesPrice = pursesPrice;
        this.sellPrice = sellPrice;
        this.rma = rma;
        this.user = user;
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
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getSuppliedBy() {
        return suppliedBy;
    }

    /**
     *
     * @param suppliedBy
     */
    public void setSuppliedBy(String suppliedBy) {
        this.suppliedBy = suppliedBy;
    }

    /**
     *
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @return
     */
    public String getCatagory() {
        return catagory;
    }

    /**
     *
     * @param catagory
     */
    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    /**
     *
     * @return
     */
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
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
    public String getDiscountInCash() {
        return discountInCash;
    }

    /**
     *
     * @param discountInCash
     */
    public void setDiscountInCash(String discountInCash) {
        this.discountInCash = discountInCash;
    }

    /**
     *
     * @return
     */
    public String getDiscountInPersent() {
        return discountInPersent;
    }

    /**
     *
     * @param discountInPersent
     */
    public void setDiscountInPersent(String discountInPersent) {
        this.discountInPersent = discountInPersent;
    }

    /**
     *
     * @return
     */
    public String getRma() {
        return rma;
    }

    /**
     *
     * @param rma
     */
    public void setRma(String rma) {
        this.rma = rma;
    }

    /**
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
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
