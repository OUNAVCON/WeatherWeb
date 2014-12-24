define(['require', 'angular', 'amdatu.bootstrap.app'], function(require, ng) {
    'use strict';

    require(['domReady!'], function(document) {
        /* everything is loaded...go! */

        return ng.bootstrap(document, ['amdatu.bootstrap.app']);
    });
});