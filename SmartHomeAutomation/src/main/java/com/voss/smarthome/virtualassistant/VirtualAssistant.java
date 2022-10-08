/**
 * 
 */
package com.voss.smarthome.virtualassistant;

import java.io.IOException;

import com.voss.smarthome.virtualassistant.audio.AsisstantVoicePlayer;
import com.voss.smarthome.virtualassistant.commands.CommandConstants;

/**
 * @author Brian
 *
 */
public class VirtualAssistant {

	public VirtualAssistant(){}

	public void speakToUser(String command)
	{
		
		//first we need to translate the response to a file
		try {
			AsisstantVoicePlayer.translateToSpeech(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//then we vocalize it
		AsisstantVoicePlayer.playResponse(CommandConstants.ASSISTANT_RESPONSE_FILE);
		
		//TODO: How do we stop the player after it's finished? Do we have to?
		
		
	}
	
	public String disectCommand(String transcript)
	{
		transcript = transcript.trim();
		
		//Check to see if our name was said
		if(transcript.contains(CommandConstants.INCOMING_GOOD_MORNING) && transcript.contains(CommandConstants.ASSISTANT_NAME))
		{
			//then we need to respond with the blanket response
			//TODO: Split this into morning/evening etc by clock time
			return CommandConstants.COMMAND_GOOD_MORNING;
		}
		
		else if(transcript.contains(CommandConstants.INCOMING_TIME) && transcript.contains(CommandConstants.ASSISTANT_NAME))
		{
			//then we need to respond with the blanket response
			//TODO: Split this into morning/evening etc by clock time
			return CommandConstants.COMMAND_TIME;
		}
		else if(transcript.contains(CommandConstants.INCOMING_WEATHER) && transcript.contains(CommandConstants.ASSISTANT_NAME))
		{
			//then we need to respond with the blanket response
			//TODO: Split this into morning/evening etc by clock time
			return CommandConstants.COMMAND_WEATHER;
		}
		else if(transcript.contains(CommandConstants.INCOMING_FORCAST) && transcript.contains(CommandConstants.ASSISTANT_NAME))
		{
			//then we need to respond with the blanket response
			//TODO: Split this into morning/evening etc by clock time
			return CommandConstants.COMMAND_FORECAST;
		}
		else if(transcript.contains(CommandConstants.ASSISTANT_NAME))
		{
			//then we need to respond with the blanket response
			//TODO: Split this into morning/evening etc by clock time
			return CommandConstants.COMMAND_INTITALIZE_CONVERSATION;
		}
		
		return null;
	}

}
