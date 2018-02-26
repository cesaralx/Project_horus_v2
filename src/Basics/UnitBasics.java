package Basics;

import Models.Unit;
import Actions.UnitGetway;

/**
 * Se encarga de ejecutar los metodos de 
 * @see Actions.UnitGetway
 * @author alexi
 */
public class UnitBasics {
    
    UnitGetway unitGetway = new UnitGetway();
    
    /**
     *
     * @param unit
     * @return
     */
    public Object save(Unit unit){
        if(unitGetway.isUniqName(unit)){
            unitGetway.save(unit);
        }
        return unit;
    }

    /**
     *
     * @param unit
     * @return
     */
    public Object delete(Unit unit){
        if(unitGetway.isNotUse(unit)){
            unitGetway.delete(unit);
        }
        return unit;
    }
}
