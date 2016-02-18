/**
 * Created by paul on 05/09/14.
 */
require
    .config({
        paths: {
            'angular': 'bower_components/angular/angular',
            'angular-bootstrap': 'bower_components/angular-bootstrap/ui-bootstrap-tpls',
            'domReady': 'bower_components/requirejs-domready/domReady',
            'SockJS': 'bower_components/sockjs/sockjs',
            'Rx' : 'bower_components/rxjs/dist/rx.lite',
            'Stomp' : 'bower_components/stomp-websocket/lib/stomp',
            'Atmosphere' : 'bower_components/atmosphere/atmosphere',
            'Config' : 'http://localhost:8080/configuration',
            'jquery' : 'bower_components/jquery/dist/jquery.min'
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
            },
            'SockJS' : {
                exports: 'SockJS'
            },
            'Stomp' : {
                exports: 'Stomp'
            },

            'Atmosphere' : {
                exports: 'Atmosphere'
            }
        }
    }
);

require(['bootstrap', 'domReady'], function() {
    // nothing to do here...see app.bootstrap.js
});
