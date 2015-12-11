package org.weather.impl;

import java.util.List;
import java.util.Random;

import org.weather.WeatherService;
import org.weather.Weather;

public class WeatherImpl implements WeatherService {


	private float result = 90;
	private Random rand = new Random();
	
	
	@Override
	public Weather getWeather() {
    System.out.println("<--Pi4J--> GPIO Blink Example ... started.");
		result += (rand.nextDouble() - 0.5F);
		return new Weather(result,0.0f,0.0f);
	}


	@Override
	public Weather addWeather(Weather weather) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Weather> getAllWeatherSamples() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Weather> getWeatherSamples(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
