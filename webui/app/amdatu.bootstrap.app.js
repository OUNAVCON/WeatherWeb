define(["require", "exports", 'angular', 'example/ExampleController', 'example/ExampleService', 'Config', "angular-bootstrap", "jquery"], function(require, exports, angular, ExampleController, ExampleService, Config) {
    var ngModule = angular.module('amdatu.bootstrap.app', ['ui.bootstrap']);

    ngModule.controller('ExampleController', ExampleController);
    ngModule.service('ExampleService', ExampleService);
    ngModule.constant('BASE_URL', Config.host);

    
    return ngModule;
});
