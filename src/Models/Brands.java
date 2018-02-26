package Models;


import List.ListBrands;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * modelo para marcas
 * @author alexi
 */
public class Brands {

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
    public String supplyrId;

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
     */
    public String supplyerName;

    /**
     *
     */
    public String creatorName;

    /**
     *
     */
    public ObservableList<ListBrands> brandDitails = FXCollections.observableArrayList();

}