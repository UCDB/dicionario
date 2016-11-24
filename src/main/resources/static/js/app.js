var dicionarioApp = angular.module("dicionarioApp",['ngRoute']);

dicionarioApp.config(function ($routeProvider,$locationProvider ) {

    $routeProvider
        .when("/login",{
            templateUrl : 'view/login.html',
            controller : 'loginController'
        })
        .when("/cad-usuario",{
        templateUrl : 'view/usuario.html',
        controller : 'usuarioController'
        })
        .when("/pagina-principal",{
        templateUrl : 'view/paginaPrincipal.html',
        controller : 'paginaInicial-controller'
        })
        .otherwise({redirectTo:'/'});

   // $locationProvider.html5Mode(true);

});
