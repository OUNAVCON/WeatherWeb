/// <reference path="./requirejs/require.d.ts" />
/// <reference path="./angularjs/angular.d.ts" />
/// <reference path="./angularjs/angular-route.d.ts" />
/// <reference path="./angular-bootstrap/angular-ui-bootstrap.d.ts" />
/// <reference path="./rx/rx.d.ts" />
/// <reference path="./sockjs/sockjs.d.ts" />
/// <reference path="./stomp/stomp.d.ts" />
/// <reference path="./atmosphere/atmosphere.d.ts"/>
/// <reference path="./config.d.ts"/>

declare module "require" {
    export = require;
}

declare module "angular" {
    export = angular;
}

declare module "SockJS" {
    export = SockJS
}

declare module "Stomp" {
    export = Stomp
}

declare module "Rx" {
    export = Rx
}

declare module "Atmosphere" {
    export = atmosphere
}

declare module "Config" {
    export = Config
}