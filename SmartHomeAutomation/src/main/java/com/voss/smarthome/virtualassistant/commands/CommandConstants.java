/**
 * 
 */
package com.voss.smarthome.virtualassistant.commands;

/**
 * @author Brian
 *
 */
public class CommandConstants {
		
		public static final String ASSISTANT_NAME = "Heather";
		public static final String NOTIFICATION_SOUND_FILE = "./resources/boop.wav";
		public static final String ASSISTANT_RESPONSE_FILE = "./resources/assistantResponse.wav";
		public static final String INCOMMING_RESPONSE_FILE = "./resources/Recording.wav";		
		
		//incomming command requests
		public static final String INCOMING_GOOD_MORNING = "good morning";
		public static final String INCOMING_TIME = "time";
		public static final String INCOMING_WEATHER = "weather";
		public static final String INCOMING_FORCAST = "forecast";
		
		//outgoing command requests 
		public static final String COMMAND_GOOD_MORNING = "Good Morning!";
		public static final String COMMAND_INTITALIZE_CONVERSATION = "I'm here. what can I do for you?";
		public static final String COMMAND_TIME = "look at your phone lazyass!";
		public static final String COMMAND_WEATHER = "Please continue on to an outer door, open it, walk outside and see for yourself.";
		public static final String COMMAND_FORECAST = "Meteorologists can't even get it right. What makes you think I can predict weather? ";
	

}
