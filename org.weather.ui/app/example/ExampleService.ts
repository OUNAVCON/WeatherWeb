/// <reference path="../typescriptDefinitions/libs.d.ts" />

import Rx = require('Rx')

class ExampleService {
    static $inject = ['$http', 'BASE_URL'];


    constructor(private $http:ng.IHttpService, private BASE_URL) {
    }

    public getMessage() : string {
        return "Hello, world";
    }
}

export = ExampleService;
