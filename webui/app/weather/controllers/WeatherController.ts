// <reference path="typeScriptDefenitions/libs.d.ts" />

import WeatherService = require('../services/WeatherService')

class WeatherController {
    message : string;

    static $inject = ['WeatherService'];

    constructor(private weatherService : WeatherService) {
		this.message = 'hello';//weatherService.getMessage();
    }

}

export = WeatherController