/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserLogged;

/**
 * esta clase guarda el usuario logeado y se usa en toda la interfaz
 * @author alexi
 */

//obtiene datos del usuario logeado
public class userNameMedia {
    
   String Id;
   String usrName;

    /**
     *
     */
    public userNameMedia() {
    }

    /**
     *
     * @param Id
     */
    public userNameMedia(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @param id
     * @param usrName
     */
    public userNameMedia(String id, String usrName) {
        this.Id = id;
        this.usrName = usrName;
    }

    /**
     *
     * @return id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.Id = id;
    }

    /**
     *
     * @return username
     */
    public String getUsrName() {
        return usrName;
    }

    /**
     *
     * @param usrName
     */
    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
    
}
