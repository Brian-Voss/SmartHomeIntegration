/**
 * 
 */
package com.voss.smarthome.main;

import com.voss.smarthome.virtualassistant.audio.InfiniteStreamRecognize;
import com.voss.smarthome.virtualassistant.audio.options.InfiniteStreamRecognizeOptions;

/**
 * @author Brian
 *
 */
public class Main {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		
		
		 
		InfiniteStreamRecognizeOptions options = InfiniteStreamRecognizeOptions.fromFlags(args);
	    if (options == null) {
	      // Could not parse.
	      System.out.println("Failed to parse options.");
	      System.exit(1);
	    }

	    try {
	        InfiniteStreamRecognize.infiniteStreamingRecognize(options.langCode);
	    } catch (Exception e) {
	      System.out.println("Exception caught: " + e);
	    }
	 
	    
	        
	        //AsisstantVoicePlayer.playResponse(fileName);
	       // AsisstantVoicePlayer.translateToText("Recording.wav");
      }
}

		  

		
		


