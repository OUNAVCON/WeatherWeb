package org.weather.console;

import org.weather.TemperatureService;

public class Commands {

	private volatile TemperatureService temperatureService;
	
	public void printTemperature(){
		System.out.println(temperatureService.getTemperature());
	}
	
	
}
