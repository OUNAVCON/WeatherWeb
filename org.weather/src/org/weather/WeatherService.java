package org.weather;

import java.util.List;

public interface WeatherService {

	public Weather getWeather();	

	public Weather addWeather(Weather weather);

	public List<Weather> getAllWeatherSamples();
}
