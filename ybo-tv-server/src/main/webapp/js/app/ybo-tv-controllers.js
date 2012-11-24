'use strict';


/* Controllers */
function NowController($scope, ChannelService, ProgrammeService, $log, $location) {
    var sdf = new JsSimpleDateFormat("yyyyMMddHHmmss");
    var now = sdf.format(new Date());
    $scope.channels = ProgrammeService.getByDate(now);
    $('#primetime').removeClass('active');
    $('#partie2').removeClass('active');
    $('#finsoiree').removeClass('active');
    $('#cesoir').removeClass('active');
    $('#now').addClass('active');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
NowController.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location'];

/* Controllers */
function PrimeTimeController($scope, ChannelService, ProgrammeService, $log, $location) {
    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var now = sdf.format(new Date()) + '210000';
    $scope.channels = ProgrammeService.getByDate(now);
    $('#now').removeClass('active');
    $('#partie2').removeClass('active');
    $('#finsoiree').removeClass('active');
    $('#primetime').addClass('active');
    $('#cesoir').addClass('active');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
PrimeTimeController.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location'];

/* Controllers */
function Partie2Controller($scope, ChannelService, ProgrammeService, $log, $location) {
    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var now = sdf.format(new Date()) + '230000';
    $scope.channels = ProgrammeService.getByDate(now);
    $('#now').removeClass('active');
    $('#primetime').removeClass('active');
    $('#finsoiree').removeClass('active');
    $('#partie2').addClass('active');
    $('#cesoir').addClass('active');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
Partie2Controller.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location'];

/* Controllers */
function FinSoireeController($scope, ChannelService, ProgrammeService, $log, $location) {
    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var today = new Date();
    var tomorrow = new Date();
    tomorrow.setDate(today.getDate()+1);
    var now = sdf.format(tomorrow) + '010000';
    if (today.getHours() > 2 ) {
        now = sdf.format(today) + '010000';
    }

    $scope.channels = ProgrammeService.getByDate(now);
    $('#now').removeClass('active');
    $('#primetime').removeClass('active');
    $('#partie2').removeClass('active');
    $('#finsoiree').addClass('active');
    $('#cesoir').addClass('active');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
FinSoireeController.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location'];