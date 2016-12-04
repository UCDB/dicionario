dicionarioApp.controller('loginController', function($scope, $http,$location) {

    $scope.usuario={};

    $scope.token = {};


    $scope.autenticar  = function () {

        $http.post("/autenticar", $scope.usuario)
            .then(function (response){

            localStorage.setItem("userToken",  response.data.token);
                window.location.replace("/");

        }, function (response) {
            console.log("Falha " + response.message)
        });
    };
});
