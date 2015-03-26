package org.weather.sim.impl;

import java.util.Random;

import org.weather.TemperatureService;

public class TemperatureImpl implements TemperatureService {


	private double result = 90;
	private Random rand = new Random();
	
	
	@Override
	public double getTemperature() {
    System.out.println("<--Pi4J--> GPIO Blink Example ... started.");
          
		result += (rand.nextDouble() - 0.5F);
		return result;
	}

}
