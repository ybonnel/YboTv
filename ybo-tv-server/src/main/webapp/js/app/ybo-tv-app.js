'use strict';


// Declare app level module which depends on filters, and services
var App = angular.module('YboTv', ['YboTv.services']);


// Configuration des routes
App.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/now', {templateUrl: '/pages/partials/chaines.html', controller: NowController});
    $routeProvider.when('/primetime', {templateUrl: '/pages/partials/chaines.html', controller: PrimeTimeController});
    $routeProvider.when('/partie2', {templateUrl: '/pages/partials/chaines.html', controller: Partie2Controller});
    $routeProvider.when('/finsoiree', {templateUrl: '/pages/partials/chaines.html', controller: FinSoireeController});
    $routeProvider.when('/chaine/:idChaine', {templateUrl: '/pages/partials/chaine.html', controller: ChaineController});
    $routeProvider.otherwise({redirectTo: '/now'});
  }]);

