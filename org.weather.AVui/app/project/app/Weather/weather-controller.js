/**
 * Created by irose on 2/15/16.
 */
'use strict';

require('jquery');
require('angular');

var app = require('../module');

class weatherController {
    public text = "my text";
}

app.controller('weatherController',weatherController);

module.exports = app;
