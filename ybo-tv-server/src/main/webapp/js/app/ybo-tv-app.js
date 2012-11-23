'use strict';


// Declare app level module which depends on filters, and services
var App = angular.module('YboTv', ['YboTv.services']);


// Configuration des routes
App.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/chaines', {templateUrl: '/pages/partials/chaines.html', controller: ChainesController});
    $routeProvider.otherwise({redirectTo: '/chaines'});
  }]);

