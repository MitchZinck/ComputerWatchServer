package org.tsunamistudios.computerwatch.net;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import org.tsunamistudios.computerwatch.Main;

/**
 * 
 * @author Mitchell Zinck <mitchellzinck@yahoo.com>
 *
 */
public class Net implements Serializable {
	
	/**
	 * Idk why this is needed...
	 */
	private static final long serialVersionUID = 1L;
	
	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	
	/**
	 * Manages the serversocket.
	 * 
	 * @param i		The port number to open the socket on
	 */
	public Net(int i) {
		try {
			ServerSocket serverSocket = new ServerSocket(i);
			
			while(true) {
				sendObject(serverSocket);
			}
            
//		    String inputLine;
//		    
//            while((inputLine = in.readLine()) != null) {            	
//            	System.out.println("Client: " + inputLine);            	
//            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendObject(ServerSocket s) {
		try {
			clientSocket = s.accept();
			System.out.println("Waiting for new connection."); 
			
		    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));		    
		    outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

	        outToClient.writeObject(Main.getPrograms());
	        
	        System.out.println("Sent data.");
	        
	        String s1 = null;
	        
	        while(true) {
	        	if((s1 = in.readLine()) != null) {
	        		KillProcess.killProcess(s1);
	        		System.out.println("Killed process " + s1 + " succesffully.");
	        		break;
	        	}
	        }
	        
//	        out.close();
//	        in.close();
	        outToClient.close();
	        clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
        
		System.out.println("Sent objects succesfully");
		
	}
}
