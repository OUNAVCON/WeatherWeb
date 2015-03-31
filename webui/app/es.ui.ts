/// <amd-dependency path="angular-ui-router"/>
/// <amd-dependency path="angular-ui-select"/>
/// <amd-dependency path="angular-foundation"/>
/// <amd-dependency path="angular-bootstrap-datepicker"/>
/// <amd-dependency path="angular-nvd3"/>
/// <amd-dependency path="angular-fcsa-number"/>
/// <amd-dependency path="moment"/>

import angular = require('angular');
import dashboardModule = require('dashboard/module');
import configurationModule = require('configuration/module');
import managementModule = require('management/module');
import reportsModule = require('reports/module');
import administrationModule = require('administration/module');
import authModule = require('auth/module');

import commonModule = require('common/module');

import moment = require('moment');

var angular_dependencies = [
    'fcsa-number',
    'mm.foundation',
    'nvd3ChartDirectives',
    'ui.bootstrap',
    'ui.router',
    'ui.select',
    commonModule.name,
    dashboardModule.name,
    configurationModule.name,
    managementModule.name,
    reportsModule.name,
    administrationModule.name,
    authModule.name
];

var ngMainModule:ng.IModule = angular.module('es.ui', angular_dependencies);

ngMainModule.config(
    ['$stateProvider', '$urlRouterProvider',
        ($stateProvider, $urlRouterProvider) => {

            $urlRouterProvider
                .otherwise('/');

            $stateProvider.state("root", {
                url: "/",
                template: '<p>root</p>'
            });
        }]
);


ngMainModule.value('apiServerUrl', '//localhost:9000')
    .value('buildName', 'Local build');

ngMainModule.factory('moment', () => {
    return moment;
});

ngMainModule.run(['$rootScope', 'authService', 'moment', ($rootScope, authService) => {
    $rootScope.title = "SynTech Enterprise Server";
    $rootScope.$on('$stateChangeStart', (event, next, current)=> {
        authService.authorizeRouteChange();
    })


}]);

angular.bootstrap(document, ['es.ui']);

