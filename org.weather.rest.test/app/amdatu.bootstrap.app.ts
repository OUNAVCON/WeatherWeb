// <reference path="typeScriptDefenitions/libs.d.ts" />
/// <amd-dependency path="angular-bootstrap"/>
/// <amd-dependency path="jquery"/>

import angular = require('angular')
import ExampleController = require('example/ExampleController')
import ExampleService = require('example/ExampleService')
import Config = require('Config')

var ngModule: ng.IModule = angular.module('amdatu.bootstrap.app', ['ui.bootstrap']);

ngModule.controller('ExampleController', ExampleController);
ngModule.service('ExampleService', ExampleService);
ngModule.constant('BASE_URL', Config.host);

export = ngModule;

