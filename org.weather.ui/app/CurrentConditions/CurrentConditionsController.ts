/**
 * Created by irose on 2/11/16.
 */
// <reference path="typeScriptDefenitions/libs.d.ts" />

class CurrentConditionsController {
    public message: string;
    public weather: Weather;

  //  static $inject = ['Weather'];

    constructor() {
        this.message = 'hello';//weatherService.getMessage();
        this.weather = <Weather>{};
        this.weather.temperature = 75;
        console.log(this.weather);
    }

}

export = CurrentConditionsController;