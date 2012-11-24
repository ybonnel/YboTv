'use strict';


// Declare app level module which depends on filters, and services
var App = angular.module('YboTv', ['YboTv.services', 'loading']);


// Configuration des routes
App.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/now', {templateUrl: '/pages/partials/chaines.html', controller: NowController});
    $routeProvider.when('/primetime', {templateUrl: '/pages/partials/chaines.html', controller: PrimeTimeController});
    $routeProvider.when('/partie2', {templateUrl: '/pages/partials/chaines.html', controller: Partie2Controller});
    $routeProvider.when('/finsoiree', {templateUrl: '/pages/partials/chaines.html', controller: FinSoireeController});
    $routeProvider.when('/chaine/:idChaine', {templateUrl: '/pages/partials/chaine.html', controller: ChaineController});
    $routeProvider.otherwise({redirectTo: '/now'});
  }]);



var mod = angular.module('loading', []);

mod.factory('loadingService', function() {
    var service = {
        requestCount: 0,
        isLoading: function() {
            return service.requestCount > 0;
        }
    };
    return service;
});

mod.factory('onStartInterceptor', function(loadingService) {
    return function (data, headersGetter) {
        loadingService.requestCount++;
        return data;
    };
});

mod.factory('onCompleteInterceptor', function(loadingService) {
    return function(promise) {
        var decrementRequestCount = function(response) {
            loadingService.requestCount--;
            return response;
        };
        return promise.then(decrementRequestCount, decrementRequestCount);
    };
});

mod.config(function($httpProvider) {
    $httpProvider.responseInterceptors.push('onCompleteInterceptor');
});

mod.run(function($http, onStartInterceptor) {
    $http.defaults.transformRequest.push(onStartInterceptor);
});

mod.controller('LoadingCtrl', function($scope, loadingService) {
    $scope.$watch(function() { return loadingService.isLoading(); }, function(value) { $scope.loading = value; });
});