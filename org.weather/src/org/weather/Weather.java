package org.weather;

import java.time.LocalDateTime;

public class Weather {
	private float temperature;
	private float humidityRhPercent;
	private float ambientLightLevel;
	private LocalDateTime timeStamp;

	public Weather() {
	};

	public Weather(float temperature, float humidityRhPercent,
			float ambientLightLevel) {
		this(temperature, humidityRhPercent, ambientLightLevel, LocalDateTime
				.now());
	}

	public Weather(float temperature, float humidityRhPercent,
			float ambientLightLevel, LocalDateTime timeStamp) {
		super();
		this.temperature = temperature;
		this.humidityRhPercent = humidityRhPercent;
		this.ambientLightLevel = ambientLightLevel;
		this.timeStamp = timeStamp;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidityRhPercent() {
		return humidityRhPercent;
	}

	public void setHumidityRhPercent(float humidityRhPercent) {
		this.humidityRhPercent = humidityRhPercent;
	}

	public float getAmbientLightLevel() {
		return ambientLightLevel;
	}

	public void setAmbientLightLevel(float ambientLightLevel) {
		this.ambientLightLevel = ambientLightLevel;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Weather [temperature=" + temperature + ", humidityRhPercent="
				+ humidityRhPercent + ", ambientLightLevel="
				+ ambientLightLevel + ", timeStamp=" + timeStamp + "]";
	}

}
