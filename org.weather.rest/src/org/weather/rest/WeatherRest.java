package org.weather.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.weather.WeatherService;
import org.weather.Weather;



@Path("weather")
public class WeatherRest {

	private volatile WeatherService temperatureService;
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Weather getWeather() {
		System.out.println("made it this far");
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
		  return temperatureService.getAllWeatherSamples()  ;
	  }
	  
	  @GET
	  @Path("getWeatherSamples")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Weather> getWeatherSamples(@QueryParam("start") int start,@QueryParam("count") int count){
		  return temperatureService.getWeatherSamples(start, count);
	  }
}
