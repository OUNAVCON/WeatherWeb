/**
 * Created by irose on 2/11/16.
 */
// <reference path="typeScriptDefenitions/libs.d.ts" />

//import WeatherService = require();
class CurrentConditionsController {
    message : string;

    static $inject = ['Weather'];

    constructor(public weather : Weather) {
        this.message = 'hello';//weatherService.getMessage();
        this.weather.temperature = 74;
    }

}

export = CurrentConditionsController;