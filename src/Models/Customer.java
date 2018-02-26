package Models;


import List.ListCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alexi
 */
public class Customer {

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
    public String customerConNo;

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
    public String date;

    /**
     *
     */
    public String userId;

    /**
     *
     */
    public String userName;

    /**
     *
     */
    public ObservableList<ListCustomer> customerList = FXCollections.observableArrayList();

}