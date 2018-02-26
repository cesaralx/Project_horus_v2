package List;

import javafx.beans.property.SimpleStringProperty;

/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.RmaGetway
 * @author alexi
 */
public class ListRma {

    /**
     *
     */
    public String ramId;

    /**
     *
     */
    public String rmaName;

    /**
     *
     */
    public String rmaDays;

    /**
     *
     */
    public String rmaComment;

    /**
     *
     */
    public String creatorName;

    /**
     *
     */
    public String date;

    /**
     *
     * @param ramId
     * @param rmaName
     * @param rmaDays
     * @param rmaComment
     * @param creatorName
     * @param date
     */
    public ListRma(String ramId, String rmaName, String rmaDays, String rmaComment, String creatorName, String date) {
        this.ramId = ramId;
        this.rmaName = rmaName;
        this.rmaDays = rmaDays;
        this.rmaComment = rmaComment;
        this.creatorName = creatorName;
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getRamId() {
        return ramId;
    }

    /**
     *
     * @param ramId
     */
    public void setRamId(String ramId) {
        this.ramId = ramId;
    }

    /**
     *
     * @return
     */
    public String getRmaName() {
        return rmaName;
    }

    /**
     *
     * @param rmaName
     */
    public void setRmaName(String rmaName) {
        this.rmaName = rmaName;
    }

    /**
     *
     * @return
     */
    public String getRmaDays() {
        return rmaDays;
    }

    /**
     *
     * @param rmaDays
     */
    public void setRmaDays(String rmaDays) {
        this.rmaDays = rmaDays;
    }

    /**
     *
     * @return
     */
    public String getRmaComment() {
        return rmaComment;
    }

    /**
     *
     * @param rmaComment
     */
    public void setRmaComment(String rmaComment) {
        this.rmaComment = rmaComment;
    }

    /**
     *
     * @return
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     *
     * @param creatorName
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
