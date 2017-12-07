package Basics;

import Models.Unit;
import Actions.UnitGetway;

public class UnitBasics {
    
    UnitGetway unitGetway = new UnitGetway();
    
    public Object save(Unit unit){
        if(unitGetway.isUniqName(unit)){
            unitGetway.save(unit);
        }
        return unit;
    }

    public Object delete(Unit unit){
        if(unitGetway.isNotUse(unit)){
            unitGetway.delete(unit);
        }
        return unit;
    }
}
