package BLL;

import DAL.Orders;
import Getway.SupplyerGetway;


public class SupplyerBLL {
    
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
