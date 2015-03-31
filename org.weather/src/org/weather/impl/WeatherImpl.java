package org.weather.impl;

import java.util.List;
import java.util.Random;

import org.weather.WeatherService;
import org.weather.Weather;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

public class WeatherImpl implements WeatherService {


	private float result = 90;
	private Random rand = new Random();
	
	
	@Override
	public Weather getWeather() {
    System.out.println("<--Pi4J--> GPIO Blink Example ... started.");
        
        // create gpio controller
        final com.pi4j.io.gpio.GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #01 as an output pins and blink
        final com.pi4j.io.gpio.GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18);

		if (led1.isHigh()) {
			led1.setState(false);
		} else {
			led1.setState(true);
		}
          
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

}
