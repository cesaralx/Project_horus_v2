/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author alexi
 */
public class ViewChatController implements Initializable {
    ArrayList clientOutputStreams;
    ArrayList<String> users;
    
    @FXML
    public TextArea ta_chat;
    @FXML
    public Button b_start;
    @FXML
    public Button b_end;
    @FXML
    public Button b_users;
    @FXML
    public Button b_clear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;
        PrintWriter client;
        
        public ClientHandler(Socket clientSocket, PrintWriter user) {
            client = user;
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception ex) {
                ta_chat.appendText("Un error ha ocurrido...\n");
            }
        }

        @Override
        public void run() {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat";
            String[] data;

            try {
                while ((message = reader.readLine()) != null) {
                    ta_chat.appendText("Recibido:" + message + "\n");
                    data = message.split(":");

                    for (String token : data) {
                        ta_chat.appendText(token + "\n");
                    }

                    if (data[2].equals(connect)) {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        tellEveryone((data[0] + ":esta desconcetado." + ":" + chat));
                        userRemove(data[0]);
                    } else if (data[2].equals(chat)) {
                        tellEveryone(message);
                    } else {
                        ta_chat.appendText("Sin condiciones conocidas. \n");
                    }
                }//while 
            } //try
            catch (Exception ex) {
                ta_chat.appendText("Conexion perdida. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
            }//catch 
        }//run//Run 
    }////ClientClientHandler
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  
            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);
                while (true){
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);

                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
                    ta_chat.appendText("Conexion establecida. \n");
                }//while
            }//try
            catch (Exception ex)
            {
                ta_chat.appendText("Error al realizar la conexión. \n");
            }
        }//Run
    }//ServerStart
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        //ta_chat.append("Before " + name + " added. \n");
        users.add(name);
        //ta_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }//userAdd
    
    public void userRemove (String data) 
    {
        String message, add = ": :Disconnect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }//userRemove
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		ta_chat.appendText("Sending: " + message + "\n");
                writer.flush();
//                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                
            } 
            catch (Exception ex) 
            {
		ta_chat.appendText("Error telling everyone. \n");
            }
        } 
    }//tellEveryone
    
    @FXML
     private void b_startActionPerformed(ActionEvent evt) {                                        
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.appendText("Servicio iniciado...\n");
    }                                       

     @FXML
    private void b_endActionPerformed(ActionEvent evt) {                                      
        // TODO add your handling code here:
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        
        ta_chat.setText("");
        ta_chat.appendText("Deteniendo servicio... \n");
    }                                     

    @FXML
    private void b_usersActionPerformed(ActionEvent evt) {                                        
       ta_chat.appendText("\n Usuario en linea: \n");
        for (String current_user : users)
        {
            ta_chat.appendText(current_user);
            ta_chat.appendText("\n");
        } 
    }                                       

    @FXML
    private void b_clearActionPerformed(ActionEvent evt) {                                        
       ta_chat.setText("");
    }   
    
}
