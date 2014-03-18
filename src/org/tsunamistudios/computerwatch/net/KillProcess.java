package org.tsunamistudios.computerwatch.net;

import java.io.IOException;

public class KillProcess {
	
	public static void killProcess(String s) {
		try {
			Runtime.getRuntime().exec("taskkill /IM " + s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
