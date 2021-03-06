/* eslint-disable no-new */
'use strict';

require('./index.less');

require('jquery');
require('angular');
require('availity-angular');

var app = require('./module');
var weather = require('./weather');
var weatherController = require('./weather/weather-controller');

app.addModules([
  'availity',
  'availity.ui',
  'availity.ui.templates',
  'ui.router',
  'blueimp.fileupload',
  'ng.shims.placeholder'
]);
/*
app.controller('PageController', function($scope, AvModal, AV_GLOBALS) {

/!*  var reg = {
    name: null,
    selectedState: null,
    date: null,
    states: AV_GLOBALS.REGIONS,
    onShow: function() {
      new AvModal({
        scope: $scope,
        templateUrl: 'registration/templates/notification.html',
        show: true
      });

    }
  };*!/

  reg.selectedState = reg.states[0];

  $scope.reg = reg;

});*/

app.config(function($stateProvider, $urlRouterProvider, avValProvider) {

/*  var defaultRules = {
    'name': {
      'required': {
        'message': 'Your name is required.'
      },
      'size': {
        'min': 2,
        'max': 10,
        'message': 'Your name must be between 2 and 10 characters.'
      }
    },
    'date': {
      'required': {
        'message': 'Date of service is required.'
      },
      'dateFormat': {
        'format': 'MM/DD/YYYY',
        'message': 'Format needs to be MM/DD/YYYY'
      }
    }*/
/*
  };

  avValProvider.addRules({
    'default': defaultRules
  });
*/

  $stateProvider
    .state('weather', {
      url: '/weather',
      template: weather.TEMPLATES.PAGE,
      controller: "weatherController as ctrl"
    });

  $urlRouterProvider.otherwise('/weather');

});

module.exports = app;

