package org.weather.impl;

import java.util.Random;

import org.weather.TemperatureService;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

public class TemperatureImpl implements TemperatureService {


	private double result = 90;
	private Random rand = new Random();
	
	
	@Override
	public double getTemperature() {
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
		return result;
	}

}
