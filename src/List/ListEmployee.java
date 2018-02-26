/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

/**
 * el proposito es retornar todos los valoes puestos
 * @see Actions.UserGetway
 * @author alexi
 */
public class ListEmployee {
    
    /**
     *
     */
    public String employeeId;

    /**
     *
     */
    public String employeeName; 

    /**
     *
     * @param employeeId
     * @param employeeName
     */
    public ListEmployee(String employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    /**
     *
     * @return
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     *
     * @param employeeId
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     *
     * @return
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     *
     * @param employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}
