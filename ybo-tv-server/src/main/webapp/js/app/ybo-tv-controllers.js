'use strict';

/* Controllers */
function ChainesController($scope, ChannelService, $log, $location) {
    $scope.channels = ChannelService.query();
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
ChainesController.$inject = ['$scope', 'ChannelService', '$log', '$location'];