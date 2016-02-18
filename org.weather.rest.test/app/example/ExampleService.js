define(["require", "exports"], function(require, exports) {
    var ExampleService = (function () {
        function ExampleService($http, BASE_URL) {
            this.$http = $http;
            this.BASE_URL = BASE_URL;
        }
        ExampleService.prototype.getMessage = function () {
            return "Hello, world";
        };
        ExampleService.$inject = ['$http', 'BASE_URL'];
        return ExampleService;
    })();

    
    return ExampleService;
});
