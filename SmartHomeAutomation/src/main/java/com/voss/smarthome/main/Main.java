/**
 * 
 */
package com.voss.smarthome.main;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import com.voss.smarthome.virtualassistant.VirtualAssistant;
import com.voss.smarthome.virtualassistant.audio.AsisstantVoicePlayer;

/**
 * @author Brian
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		//Create the virtual assistant
		VirtualAssistant vAssistant = new VirtualAssistant();
		
		 /**
		   * Demonstrates using the Text-to-Speech API.
		   */
		 
	 
	        
	        //AsisstantVoicePlayer.playResponse(fileName);
	        AsisstantVoicePlayer.translateToText("Recording.wav");
      }
}
		  

		
		

	}

}
