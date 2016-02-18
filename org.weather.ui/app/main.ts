/**
 * Created by paul on 05/09/14.
 */
require
    .config({
        paths: {
            'angular': 'bower_components/angular/angular',
            'angular-bootstrap': 'bower_components/angular-bootstrap/ui-bootstrap-tpls',
            'angular-route': 'bower_components/angular-route',
            'domReady': 'bower_components/domReady/domReady',
            'jquery' : 'bower_components/jquery/dist/jquery'
        },

        shim: {
            'angular': {
                exports: 'angular'
            },
            'angular-route': {
                deps: ['angular']
            },
            'angular-bootstrap' : {
                deps: ['angular']
            }
        }
    }
);

define(['require', 'angular', 'amdatu.bootstrap.app'], function(require, ng) {
    'use strict';

    require(['domReady!'], function(document) {
        /* everything is loaded...go! */

        return ng.bootstrap(document, ['amdatu.bootstrap.app']);
    });
});