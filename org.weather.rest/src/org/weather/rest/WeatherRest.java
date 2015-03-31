package org.weather.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;





//import org.apache.felix.dm.annotation.api.Component;
import org.weather.WeatherService;
import org.weather.Weather;


//@Component(provides=Object.class)
@Path("weather")
public class WeatherRest {

	private volatile WeatherService temperatureService;
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Weather getWeather() {
	    return temperatureService.getWeather();
	  }

	  @GET
	  @Path("Temperature")
	  @Produces(MediaType.APPLICATION_JSON)
	  public double getTemperature()
	  {
		 return temperatureService.getWeather().getTemperature();
	  }
	  
	  @GET
	  @Path("getAllWeatherSamples")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Weather> getAllWeatherSamples(){
		  return temperatureService.getAllWeatherSamples();
	  }
}
