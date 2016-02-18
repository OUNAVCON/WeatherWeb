// <reference path="typeScriptDefenitions/libs.d.ts" />

import ExampleService = require('ExampleService')

class ExampleController {
    message : string;

    static $inject = ['ExampleService'];

    constructor(private exampleService : ExampleService) {
		this.message = exampleService.getMessage();
    }
}

export = ExampleController
