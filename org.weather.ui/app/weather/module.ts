
import angular = require('angular')
import WeatherController = require('weather/WeatherController')
import WeatherService = require('weather/WeatherService')

var ngModule:ng.IModule = angular.module('weather', []);

ngModule.controller('WeatherController', WeatherController);
ngModule.service('WeatherService', WeatherService);


export = ngModule