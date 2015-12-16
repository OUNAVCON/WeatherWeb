// <reference path="typeScriptDefenitions/libs.d.ts" />
/// <amd-dependency path="angular-bootstrap"/>
/// <amd-dependency path="jquery"/>

import angular = require('angular')
import WeatherController = require('weather/controllers/WeatherController')
import WeatherService = require('weather/services/WeatherService')
//import Config = require('Config')

var ngModule: ng.IModule = angular.module('amdatu.bootstrap.app', ['ui.bootstrap']);

ngModule.controller('WeatherController', WeatherController);
ngModule.service('WeatherService', WeatherService);
ngModule.constant('BASE_URL', 'http://localhost:9050');

export = ngModule;

