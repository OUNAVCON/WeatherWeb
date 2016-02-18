// <reference path="typeScriptDefenitions/libs.d.ts" />
/// <amd-dependency path="angular-bootstrap"/>
/// <amd-dependency path="jquery"/>

import angular = require('angular')
//import Config = require('Config')
import weather = require('weather/module');
import currentConditions = require('CurrentConditions/module');

var ngModule: ng.IModule = angular.module('amdatu.bootstrap.app', ['ui.bootstrap', weather.name, currentConditions.name]);

ngModule.constant('BASE_URL', 'http://localhost:9050');

export = ngModule;

