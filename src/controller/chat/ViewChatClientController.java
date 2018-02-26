/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.chat;

import LogFile.logger;
import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Cliente chat
 * @author alexi
 */
public class ViewChatClientController implements Initializable {
    
    @FXML
    JFXButton b_anonymous;
    @FXML
    JFXButton b_connect;
    @FXML
    JFXButton b_disconnect;
    @FXML
    JFXButton b_send;
    @FXML
    TextField tf_adress;
    @FXML
    TextField tf_port;
    @FXML
    TextField tf_username;
    @FXML
    TextField tf_chat;
    @FXML
    TextArea ta_chat;
    
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    logger log = new logger();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
     //--------------------------//

    /**
     * inicia el hilo de escucha
     */
    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }
         //--------------------------//

    /**
     * grega un nuevo usuario
     * @param data
     */
    public void userAdd(String data) {
        users.add(data);
    }

    //--------------------------//

    /**
     * quita un usuario
     * @param data
     */
    public void userRemove(String data) {
        ta_chat.setText(ta_chat.getText() + data + " esta fuera de linea.\n");
    }

    //--------------------------//

    /**
     * escribe mensaje a todos los usuarios
     */
    public void writeUsers() {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
    }

    //--------------------------//

    /**
     * Manda mensaje desconexion
     */
    public void sendDisconnect() {
        String bye = (username + ": :Disconnect");
        try {
            writer.println(bye);
            writer.flush();
        } catch (Exception e) {
            ta_chat.setText(ta_chat.getText() + "Sin poder enviar mensaje de desconexión.\n");
        }
    }

    //--------------------------//

    /**
     * Se desconecta
     */
    public void Disconnect() {
        try {
            ta_chat.setText(ta_chat.getText() + "Desconectado.\n");
            sock.close();
        } catch (Exception ex) {
            ta_chat.setText(ta_chat.getText() + "Fallo al desconectar. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    
    /**
     *
     */
    public class IncomingReader implements Runnable {

        /**
         * ejecuta el chat
         */
        @Override
        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect";
            String disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {
                    data = stream.split(":");

                    if (data[2].equals(chat)) {
                        ta_chat.setText(ta_chat.getText() + data[0] + ": " + data[1] + "\n");
//                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                    } else if (data[2].equals(connect)) {
                        ta_chat.clear();
                        userAdd(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        userRemove(data[0]);
                    } else if (data[2].equals(done)) {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                    }
                }
            } catch (Exception ex) {
            }
        }//run
    }//clase
     
     
    @FXML
     private void b_connectActionPerformed(ActionEvent evt) {                                          
        address = tf_adress.getText();
        port = Integer.parseInt(tf_port.getText());
        if (isConnected == false) {
            username = tf_username.getText();
            tf_username.setEditable(false);

            try {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            } catch (Exception ex) {
                ta_chat.setText(ta_chat.getText() + "Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true) {
            ta_chat.setText(ta_chat.getText() + "You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed  
     
    @FXML
    private void b_disconnectActionPerformed(ActionEvent evt) throws IOException {     
        log.wirteLogWarning("Desconectando cliente");
        sendDisconnect();
        Disconnect();
    }                                            

    @FXML
    private void b_anonymousActionPerformed(ActionEvent evt) throws IOException {  
        log.wirteLogWarning("Conectando como anonimo");
       address = tf_adress.getText();
        port = Integer.parseInt(tf_port.getText());
        tf_username.setText("");
        if (isConnected == false) {
            String anon = "anon";
            Random generator = new Random();
            int i = generator.nextInt(999) + 1;
            String is = String.valueOf(i);
            anon = anon.concat(is);
            username = anon;

            tf_username.setText(anon);
            tf_username.setEditable(false);

            try {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            } catch (Exception ex) {
                ta_chat.setText(ta_chat.getText() + "Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true) {
            ta_chat.setText(ta_chat.getText() + "You are already connected. \n");
        }
    }//GEN-LAST:event_b_anonymousActionPerformed                                       

    @FXML
    private void b_sendActionPerformed(ActionEvent evt) throws IOException {  
        log.wirteLogInfo("Enviando mensaje");
       String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
                writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
                writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.setText(ta_chat.getText() + "Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
        
    }//GEN-LAST:event_b_sendActionPerformed
    
}
