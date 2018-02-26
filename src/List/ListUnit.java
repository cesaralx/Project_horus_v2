package List;

import javafx.beans.property.SimpleStringProperty;

/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.UsersGetway
 * @author alexi
 */
public class ListUnit {

    /**
     *
     */
    public String unitId;

    /**
     *
     */
    public String unitName;

    /**
     *
     */
    public String unitDescription;

    /**
     *
     */
    public String creatorName;

    /**
     *
     */
    public String dateOfCreation;

    /**
     *
     * @param unitId
     * @param unitName
     * @param unitDescription
     * @param creatorName
     * @param dateOfCreation
     */
    public ListUnit(String unitId, String unitName, String unitDescription, String creatorName, String dateOfCreation) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.unitDescription = unitDescription;
        this.creatorName = creatorName;
        this.dateOfCreation = dateOfCreation;
    }

    /**
     *
     * @return
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     *
     * @param unitId
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     *
     * @return
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     *
     * @param unitName
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     *
     * @return
     */
    public String getUnitDescription() {
        return unitDescription;
    }

    /**
     *
     * @param unitDescription
     */
    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
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
    public String getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     *
     * @param dateOfCreation
     */
    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
