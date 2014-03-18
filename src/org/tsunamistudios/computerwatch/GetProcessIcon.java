package org.tsunamistudios.computerwatch;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

/**
 * 
 * @author Mitchell Zinck <mitchellzinck@yahoo.com>
 * 
 */

public class GetProcessIcon {
	
	private static String line;
	
	/**
	 * Returns a byte[] array of a running processes icon.
	 * 
	 * @param exeName The name of an .exe file that you want the icon of
	 * @return 		  The byte[] array of the .exe files Icon
	 */
	public byte[] getImage(String exeName) {
        try {
			Process p = Runtime.getRuntime().exec("./res/getPrograms.bat " + exeName);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			int count = 0;
			
            while(true) {
            	line = input.readLine();
            	if(line != null) {
	            	if(count == 4) {
	            		if(line.contains("Windows")) {
	            			return null;
	            		}
	            		System.out.println(line);
	            		break;
	            	}
            	}
            	count++;
            }
            
            p.destroy();
            input.close();
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    File file = new File(line);
	    Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);	  
	    BufferedImage bim = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] by = null;
	    try {
			ImageIO.write(bim, "jpg", baos);
		    baos.flush();
		    by = baos.toByteArray();
		    baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return by;		
	}
	
}
