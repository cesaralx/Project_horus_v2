package Models;

import List.ListSupplyer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * modelo de las ordenes
 * @author alexi
 */
public class Orders {

    /**
     *
     */
    public String id;

    /**
     *
     */
    public String supplyerName;

    /**
     *
     */
    public String supplyerContactNumber;

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
    public String date;

//    public List<ListSupplyer> rowSupplyer;

    /**
     *
     */
    public ObservableList<ListSupplyer> supplyerDetails = FXCollections.observableArrayList();




}
