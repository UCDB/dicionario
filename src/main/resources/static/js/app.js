var dicionarioApp = angular.module("dicionarioApp",['ngRoute']);

dicionarioApp.config(function ($routeProvider,$locationProvider ) {

    $routeProvider

        .when("/cad-usuario",{
            templateUrl : 'view/usuario.html',
            controller : 'usuarioController'
        })
        .when("/pagina-principal",{
            templateUrl : 'view/paginaPrincipal.html',
            controller : 'paginaInicial-controller'
        })
        .when("/inserir-palavra",{
            templateUrl: 'view/inserirPalavra.html',
            controller: 'inserirPalavra-controller'
        })
        .when("/cad-palavra",{
        templateUrl : 'view/inserirPalavra.html',
            controller: 'inserir-controller'

    })
        .otherwise({redirectTo:'/'});

   // $locationProvider.html5Mode(true);

});
