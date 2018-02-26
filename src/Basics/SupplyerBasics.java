package Basics;

import Models.Orders;
import Actions.SupplyerGetway;

/**
 * Se encarga de llevar acabo las acciones en:
 * @see Actions.SupplyerGetway
 * @author alexi
 */
public class SupplyerBasics {
    
    SupplyerGetway supplyerGetway = new SupplyerGetway();

    /**
     *
     */
    public void save(){
        
    }
    
    /**
     *
     * @param supplyer
     * @return
     */
    public Object delete(Orders supplyer){
        if(supplyerGetway.isNotUse(supplyer)){
            supplyerGetway.delete(supplyer);
        }else{
            
        }
        return supplyer;
    }
}
