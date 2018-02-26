package List;

/**
 * el proposito es retornar todos los valoes puestos
 * @author alexi
 */
public class ListBrands{

    /**
     *
     */
    public String id;

    /**
     *
     */
    public String brandName;

    /**
     *
     */
    public String brandComment;

    /**
     *
     */
    public String supplyerName;

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
     * @param brandName
     * @param brandComment
     * @param supplyerName
     * @param creatorId
     * @param date
     */
    public ListBrands(String id, String brandName, String brandComment, String supplyerName, String creatorId, String date) {
        this.id = id;
        this.brandName = brandName;
        this.brandComment = brandComment;
        this.supplyerName = supplyerName;
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
    public String getBrandName() {
        return brandName;
    }

    /**
     *
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     *
     * @return
     */
    public String getBrandComment() {
        return brandComment;
    }

    /**
     *
     * @param brandComment
     */
    public void setBrandComment(String brandComment) {
        this.brandComment = brandComment;
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
