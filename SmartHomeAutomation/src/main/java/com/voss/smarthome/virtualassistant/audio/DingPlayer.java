/**
 * 
 */
package com.voss.smarthome.virtualassistant.audio;

import java.io.*;
import javax.sound.sampled.*;
import com.voss.smarthome.virtualassistant.commands.CommandConstants;

/**
 * @author Brian
 *
 */
public class DingPlayer {

	/**
	 * 
	 */
	public DingPlayer() {}


		public static void playDing()
		{
			  // open the sound file as a Java input stream
		   
			try {
				    AudioInputStream stream = AudioSystem.getAudioInputStream(new File(CommandConstants.NOTIFICATION_SOUND_FILE));
				    AudioFormat format = stream.getFormat();
				    DataLine.Info info = new DataLine.Info(Clip.class, format);
				    Clip clip = (Clip) AudioSystem.getLine(info);
				    clip.open(stream);
				    clip.start();
			}
			catch (Exception e) {
			    System.out.println(e.getStackTrace());
			}
		}
	}


