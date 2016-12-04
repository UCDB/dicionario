dicionarioApp.controller("mainController",['$scope', '$location', function ($scope, $location) {

    $location.path("/pagina-principal");

    $scope.sair = function () {
        localStorage.setItem("userToken","");
        window.location.replace("/login");
    }


}]);