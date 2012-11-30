'use strict';

function RootController($scope) {

    $scope.activeTab = function(tab) {

        $('#primetime').removeClass('active');
        $('#partie2').removeClass('active');
        $('#finsoiree').removeClass('active');
        $('#cesoir').removeClass('active');
        $('#now').removeClass('active');
        $('#chaines').removeClass('active');
        $(tab).addClass('active');
        if (tab == '#primetime' || tab == '#partie2' || tab == '#finsoiree') {
            $('#cesoir').addClass('active');
        }

    }

}

RootController.$inject = [ '$scope'];


/* Controllers */
function NowController($scope, ChannelService, ProgrammeService, $log, $location, $window) {
    $scope.$on('$viewContentLoaded', function(event) {
        $window._gaq.push(['_trackPageview', $location.path()]);
    });

    var sdf = new JsSimpleDateFormat("yyyyMMddHHmmss");
    var now = sdf.format(new Date());
    $scope.channels = ProgrammeService.getByDate(now);
    $scope.activeTab('#now');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
NowController.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location', '$window'];

/* Controllers */
function PrimeTimeController($scope, ChannelService, ProgrammeService, $log, $location, $window) {
    $scope.$on('$viewContentLoaded', function(event) {
        $window._gaq.push(['_trackPageview', $location.path()]);
    });

    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var now = sdf.format(new Date()) + '210000';
    $scope.channels = ProgrammeService.getByDate(now);
    $scope.activeTab('#primetime');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
PrimeTimeController.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location', '$window'];

/* Controllers */
function Partie2Controller($scope, ChannelService, ProgrammeService, $log, $location, $window) {
    $scope.$on('$viewContentLoaded', function(event) {
        $window._gaq.push(['_trackPageview', $location.path()]);
    });

    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var now = sdf.format(new Date()) + '230000';
    $scope.channels = ProgrammeService.getByDate(now);
    $scope.activeTab('#partie2');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
Partie2Controller.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location', '$window'];

/* Controllers */
function FinSoireeController($scope, ChannelService, ProgrammeService, $log, $location, $window) {
    $scope.$on('$viewContentLoaded', function(event) {
        $window._gaq.push(['_trackPageview', $location.path()]);
    });

    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var today = new Date();
    var tomorrow = new Date();
    tomorrow.setDate(today.getDate()+1);
    var now = sdf.format(tomorrow) + '010000';
    if (today.getHours() > 2 ) {
        now = sdf.format(today) + '010000';
    }

    $scope.channels = ProgrammeService.getByDate(now);
    $scope.activeTab('#finsoiree');
}
// Pour que l'injection de dépendances fonctionne en cas de 'minifying'
FinSoireeController.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$log', '$location', '$window'];

function ChaineController($scope, ChannelService, ProgrammeService, $routeParams, $log, $location, $window) {
    $scope.$on('$viewContentLoaded', function(event) {
        $window._gaq.push(['_trackPageview', $location.path()]);
    });


    $scope.chaineCourante = $routeParams.idChaine;


    $scope.chaines = ChannelService.query();

    var sdf = new JsSimpleDateFormat("yyyyMMdd");
    var today = new Date();
    var tomorrow = new Date();
    var yesterday = new Date();
    tomorrow.setDate(today.getDate()+1);
    yesterday.setDate(today.getDate()-1);
    var dateDebut;
    var dateFin;
    if (today.getHours() < 3 ) {
        dateDebut = sdf.format(yesterday) + '030000';
        dateFin = sdf.format(today) + '030000';
    } else {
        dateDebut = sdf.format(today) + '030000';
        dateFin = sdf.format(tomorrow) + '030000';
    }

    $scope.programmes = ProgrammeService.getByChannelAndDate($scope.chaineCourante, dateDebut, dateFin);

    $scope.activeTab('#chaines');
}

ChaineController.$inject = ['$scope', 'ChannelService', 'ProgrammeService', '$routeParams', '$log', '$location', '$window'];