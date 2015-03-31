package org.weather.console;

import org.weather.WeatherService;

public class Commands {

	private volatile WeatherService temperatureService;
	
	public void printTemperature(){
		System.out.println(temperatureService.getWeather());
	}
	
	
}
