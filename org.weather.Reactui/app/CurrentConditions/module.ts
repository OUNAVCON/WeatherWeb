
import angular = require('angular');
import currentConditionsController = require("CurrentConditions/CurrentConditionsController");

var ngModule:ng.IModule = angular.module("currentConditions", []);

ngModule.controller("CurrentConditionsController", currentConditionsController);


export = ngModule