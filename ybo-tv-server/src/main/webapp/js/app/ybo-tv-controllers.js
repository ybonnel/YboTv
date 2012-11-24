'use strict';

/* Controllers */
function NowController($scope, ChannelService, ProgrammeService, $log, $location) {
    var sdf = new JsSimpleDateFormat("yyyyMMddHHmmss");
    var now = sdf.format(new Date());
    $scope.channels = ProgrammeService.getByDate(now);
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
NowController().$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location'];

/* Controllers */
function CeSoirController($scope, ChannelService, ProgrammeService, $log, $location) {
    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var now = sdf.format(new Date()) + '210000';
    $scope.channels = ProgrammeService.getByDate(now);
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
CeSoirController().$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location'];