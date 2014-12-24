package org.weather.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.felix.dm.annotation.api.Component;
import org.weather.TemperatureService;


@Component(provides=Object.class)
@Path("weather")
public class TemperatureRest {

	private volatile TemperatureService temperatureService;
	

	
	  @GET
	  @Produces("text/plain")
	  public String hello() {
	    return "hello world";
	  }

	  @GET
	  @Path("Temperature")
	  @Produces(MediaType.APPLICATION_JSON)
	  public double getTemperature()
	  {
		 return temperatureService.getTemperature();
	  }
}
