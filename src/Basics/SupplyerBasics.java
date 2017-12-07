package Basics;

import Models.Orders;
import Actions.SupplyerGetway;


public class SupplyerBasics {
    
    SupplyerGetway supplyerGetway = new SupplyerGetway();

    public void save(){
        
    }
    
    public Object delete(Orders supplyer){
        if(supplyerGetway.isNotUse(supplyer)){
            supplyerGetway.delete(supplyer);
        }else{
            
        }
        return supplyer;
    }
}
