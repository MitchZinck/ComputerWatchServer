package org.tsunamistudios.computerwatch.processes;

import java.io.Serializable;

/**
 * 
 * @author Mitchell Zinck <mitchellzinck@yahoo.com>
 *
 */
public class Program implements Serializable { 
	
	/**
	 * ...
	 */
	private static final long serialVersionUID = 1L;
	
	private String name = "";
	private String memory = "";
	private String description = "";
	private byte[] image = null;
	
	/**
	 * An object containing information on a running process on the users computer.
	 * 
	 * @param name			The name of the process
	 * @param memory		The memory usage of the process
	 * @param description	The description provided by tasklist.exe of the process
	 * @param image			The byte[] array of the processes icon
	 */
	public Program(String name, String memory, String description, byte[] image) {
		this.name = name;
		this.memory = memory;
		this.description = description;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
