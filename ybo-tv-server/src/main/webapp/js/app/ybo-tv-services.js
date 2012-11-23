'use strict';

/* Services */

var Services = angular.module('YboTv.services', ['ngResource']);

Services.factory('ChannelService', function($resource) {
    return $resource('/data/channel/:id', {});
});