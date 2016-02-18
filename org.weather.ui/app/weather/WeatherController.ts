// <reference path="typeScriptDefenitions/libs.d.ts" />

import WeatherService = require('WeatherService')

class WeatherController {
    message : string;
    weather: Weather;

    static $inject = ['WeatherService'];

    constructor(private weatherService : WeatherService) {
		this.message = 'hello';//weatherService.getMessage();
    }

    public getUpdatedWeatherClick():void {
        this.weatherService.useGetWeather({}).then(
            (result)=> {
                this.weather = result;
            }
        );
    }

}

export = WeatherController