var dicionarioApp = angular.module("dicionarioApp",['ngRoute']);

dicionarioApp.config(function ($routeProvider,$locationProvider ) {


    $routeProvider
        .when("/login",{
            templateUrl : 'view/login.html',
            controller : 'loginController'
        }).otherwise({redirectTo:'/'});


    // $locationProvider.html5Mode(true);

});
