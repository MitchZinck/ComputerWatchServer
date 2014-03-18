package org.tsunamistudios.computerwatch;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Icon;

import org.tsunamistudios.computerwatch.net.Net;
import org.tsunamistudios.computerwatch.processes.Program;

/**
 * 
 * @author Mitchell Zinck <mitchellzinck@yahoo.com>
 *
 * The Main class provides management of all other class in org.computerwatch.
 *
 */

public class Main {
	
	private transient static ArrayList<Program> programs = new ArrayList<Program>();
	private static GetProcessIcon prIcon = new GetProcessIcon();
	private static Main m;
	final static boolean debug = true;

	/**
	 * Instance of the class Main
	 */
	public Main() {
		long y = System.currentTimeMillis();
		setPrograms();
		long u = System.currentTimeMillis();
		log("Programs loaded in " + ((u - y) / 1000 ) + " seconds.");
		new Net(Constants.PORT_NUMBER);
		log("Connection opened");
		//printPrograms();
	}
	
	/**
	 * Sets programs value to the current processes running in tasklist.exe.
	 */	
	public static void setPrograms() {
		getPrograms().clear();		
		try {
	        String line, name = "";
	        String[] str = null;
	        Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe /v /fo csv");
	        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        
	        while ((line = input.readLine()) != null) {
	        	if(!line.contains("N/A") && !line.contains("PID")) {
	        		str = line.split(",");
	        		name = str[0].substring(1, str[0].length() - 1);
	        		if(str.length == 10) {
		        		Program program = new Program(name, 
		        				str[4].substring(1) + " " + str[5].substring(0, str[5].length() - 1), 
		        				str[9].substring(1, str[9].length() - 1), null);
		        		byte[] ic = prIcon.getImage(name);
		        		if(ic != null) {
		        			program.setImage(ic);
			        		getPrograms().add(program);
		        		}
	        		} else {
	        			Program program = new Program(str[0].substring(1, str[0].length() - 1), 
		        				str[4].substring(1, str[4].length() - 1), 
		        				str[8].substring(1, str[8].length() - 1), null);
	        			byte[] ic = prIcon.getImage(name);
	        			if(ic != null) {
		        			program.setImage(ic);
			        		getPrograms().add(program);
		        		}
	        		}
	        	}
	        }
	   	                     
	        input.close();
	    } catch (Exception err) {
	        err.printStackTrace();
	    }
	}

	/**	
	 * Returns an ArrayList of Program's set by the method setPrograms().
	 * @return an ArrayList of Program's
	 */
	public static ArrayList<Program> getPrograms() {
		return programs;
	}
	
	public void log(String message) {
		if(debug != false) {
			System.out.println(message);
		}
	}
	
	public void printPrograms() {
		for(Program s : programs) {
			System.out.println(s.getName());
			System.out.println(s.getMemory());
			System.out.println(s.getDescription());
			System.out.println(s.getImage());
		}
	}
	
	public static Main getM() {
		return m;
	}

	public static void setM(Main m) {
		Main.m = m;
	}
	
	public static void main(String[] args) {
		m = new Main();
	}
} 
