package com.voss.smarthome.virtualassistant.audio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.media.Manager;
import javax.media.Player;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import com.voss.smarthome.virtualassistant.commands.CommandConstants;





public class AsisstantVoicePlayer {
	 
	    // to store current position 
	    Long currentFrame; 
	    static Clip clip; 
	      
	    // current status of clip 
	    String status; 
	      
	    static AudioInputStream audioInputStream; 
	    static String filePath; 
	  
	    // constructor to initialize streams and clip 
		public AsisstantVoicePlayer()
		
	        throws UnsupportedAudioFileException, 
	        IOException, LineUnavailableException  
	    { 
	      
	          
	
	    } 
		
		public static void translateToSpeech(String text) throws IOException  
		{
		  
			   try {
				TextToSpeechClient textToSpeechClient = TextToSpeechClient.create();

				      // Set the text input to be synthesized
				      SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

				      // Build the voice request, select the language code ("en-US") and the ssml voice gender
				      // ("neutral")
				      VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
				          .setLanguageCode("en-US")
				          .setName("en-US-Wavenet-F")
				          .setSsmlGender(SsmlVoiceGender.FEMALE)
				          .build();

				      // Select the type of audio file you want returned
				      AudioConfig audioConfig = AudioConfig.newBuilder()
				              .setAudioEncoding(com.google.cloud.texttospeech.v1.AudioEncoding.LINEAR16)
				              .build();


				      // Perform the text-to-speech request on the text input with the selected voice parameters and
				      // audio file type
				      SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

				      // Get the audio contents from the response
				      ByteString audioContents = response.getAudioContent();

				      // Write the response to the output file.
				      String fileName = CommandConstants.ASSISTANT_RESPONSE_FILE;
				  
				        OutputStream out = new FileOutputStream(fileName);
				        out.write(audioContents.toByteArray());
				        System.out.println("Audio content written to file " + fileName);
				        out.close();
			} 
			   catch (IOException e) 
			   {
				
				 throw new IOException();
			   }
			        
			      
		   
	 }
		
		public static void translateToText(String fileName) throws IOException  
	    { 
		 try (SpeechClient speechClient = SpeechClient.create()) {


		      // Reads the audio file into memory
		      Path path = Paths.get(fileName);
		      byte[] data = Files.readAllBytes(path);
		      ByteString audioBytes = ByteString.copyFrom(data);

		      // Builds the sync recognize request
		      RecognitionConfig config = RecognitionConfig.newBuilder()
		          .setEncoding(AudioEncoding.LINEAR16)
		          .setSampleRateHertz(48000)
		          .setLanguageCode("en-US")
		          .build();
		      RecognitionAudio audio = RecognitionAudio.newBuilder()
		          .setContent(audioBytes)
		          .build();

		      // Performs speech recognition on the audio file
		      RecognizeResponse response = speechClient.recognize(config, audio);
		      List<SpeechRecognitionResult> results = response.getResultsList();

		      for (SpeechRecognitionResult result : results) {
		        // There can be several alternative transcripts for a given chunk of speech. Just use the
		        // first (most likely) one here.
		        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
		        System.out.printf("Transcription: %s%n", alternative.getTranscript());
		      }
		    }
	    }
	  
	    public static void playResponse(String fileName)  
	    { 
	        try
	        {           
	        	 File responseFile = new File(fileName);
	         // Create a Player object that realizes the audio
	            final Player p=Manager.createRealizedPlayer(responseFile.toURI().toURL());


	             // Start the speech
	             p.start();            
	          
	        }  
	          
	        catch (Exception ex)  
	        { 
	            System.out.println("Error with playing sound."); 
	            ex.printStackTrace(); 
	          
	          } 
	    } 
	      
	      
	    // Method to play the audio 
	    public void play()  
	    { 
	        //start the clip 
	        clip.start(); 
	          
	        status = "play"; 
	    } 
	      
	    // Method to pause the audio 
	    public void pause()  
	    { 
	        if (status.equals("paused"))  
	        { 
	            System.out.println("audio is already paused"); 
	            return; 
	        } 
	        this.currentFrame =  
	        AsisstantVoicePlayer.clip.getMicrosecondPosition(); 
	        clip.stop(); 
	        status = "paused"; 
	    } 
	      
	    // Method to resume the audio 
	    public void resumeAudio() throws UnsupportedAudioFileException, 
	                                IOException, LineUnavailableException  
	    { 
	        if (status.equals("play"))  
	        { 
	            System.out.println("Audio is already "+ 
	            "being played"); 
	            return; 
	        } 
	        clip.close(); 
	        resetAudioStream(); 
	        clip.setMicrosecondPosition(currentFrame); 
	        this.play(); 
	    } 
	      
	    // Method to restart the audio 
	    public void restart() throws IOException, LineUnavailableException, 
	                                            UnsupportedAudioFileException  
	    { 
	        clip.stop(); 
	        clip.close(); 
	        resetAudioStream(); 
	        currentFrame = 0L; 
	        clip.setMicrosecondPosition(0); 
	        this.play(); 
	    } 
	      
	    // Method to stop the audio 
	    public void stop() throws UnsupportedAudioFileException, 
	    IOException, LineUnavailableException  
	    { 
	        currentFrame = 0L; 
	        clip.stop(); 
	        clip.close(); 
	    } 
	      
	    // Method to jump over a specific part 
	    public void jump(long c) throws UnsupportedAudioFileException, IOException, 
	                                                        LineUnavailableException  
	    { 
	        if (c > 0 && c < clip.getMicrosecondLength())  
	        { 
	            clip.stop(); 
	            clip.close(); 
	            resetAudioStream(); 
	            currentFrame = c; 
	            clip.setMicrosecondPosition(c); 
	            this.play(); 
	        } 
	    } 
	      
	    // Method to reset audio stream 
	    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
	                                            LineUnavailableException  
	    { 
	        audioInputStream = AudioSystem.getAudioInputStream( 
	        new File(filePath).getAbsoluteFile()); 
	        clip.open(audioInputStream); 
	        clip.loop(1); 
	    } 
	}


