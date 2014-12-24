// <reference path="typeScriptDefenitions/libs.d.ts" />
/// <amd-dependency path="angular-bootstrap"/>
/// <amd-dependency path="jquery"/>
define(["require", "exports", 'angular', 'example/ExampleController', 'example/ExampleService', 'Config', "angular-bootstrap", "jquery"], function (require, exports, angular, ExampleController, ExampleService, Config) {
    var ngModule = angular.module('amdatu.bootstrap.app', ['ui.bootstrap']);
    ngModule.controller('ExampleController', ExampleController);
    ngModule.service('ExampleService', ExampleService);
    ngModule.constant('BASE_URL', Config.host);
    return ngModule;
});
