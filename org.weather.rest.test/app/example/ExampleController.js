define(["require", "exports"], function(require, exports) {
    var ExampleController = (function () {
        function ExampleController(exampleService) {
            this.exampleService = exampleService;
            this.message = exampleService.getMessage();
        }
        ExampleController.$inject = ['ExampleService'];
        return ExampleController;
    })();

    
    return ExampleController;
});
