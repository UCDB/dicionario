dicionarioApp.controller('loginController', function($scope, $http,$location) {

    $scope.usuario={};

    $scope.token = {};

    $scope.visualizar= "";


    $scope.autenticar  = function () {
        $scope.visualizar= "true";

        $http.post("/autenticar", $scope.usuario)
            .then(function (response){

            localStorage.setItem("userToken",  response.data.token);
                $scope.visualizar= "";
                window.location.replace("/");

        }, function (response) {
                $scope.visualizar= "";
            alert("Falha: " + response.data.message)
        });
    };
});
