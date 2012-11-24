'use strict';


// Declare app level module which depends on filters, and services
var App = angular.module('YboTv', ['YboTv.services']);


// Configuration des routes
App.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/now', {templateUrl: '/pages/partials/chaines.html', controller: NowController});
    $routeProvider.when('/cesoir', {templateUrl: '/pages/partials/chaines.html', controller: CeSoirController});
    $routeProvider.otherwise({redirectTo: '/now'});
  }]);

