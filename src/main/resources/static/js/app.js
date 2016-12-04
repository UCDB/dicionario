var dicionarioApp = angular.module("dicionarioApp",['ngRoute','toastr']);

dicionarioApp.config(function ($routeProvider,$locationProvider,toastrConfig ) {
    angular.extend(toastrConfig, {
        autoDismiss: false,
        containerId: 'toast-container',
        maxOpened: 0,
        newestOnTop: true,
        positionClass: 'toast-top-right',
        preventDuplicates: false,
        preventOpenDuplicates: true,
        target: 'body',
        timeOut: 2000
    });


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

    $locationProvider.html5Mode(true);

});


dicionarioApp.config(function ($httpProvider) {
    $httpProvider.interceptors.push("tokenInterceptor");

});