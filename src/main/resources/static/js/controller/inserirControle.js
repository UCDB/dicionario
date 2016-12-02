dicionarioApp.controller("inserir-controller",['$scope', '$location','dataShare','$http', function ($scope, $location,dataShare,$http) {

    if(dataShare.getData()){
        $scope.palavra = dataShare.getData();
        dataShare.setData("");
    }


    $scope.cancelar = function () {
        $location.path("/pagina-principal");
    };


    $scope.cadastrarPalavra = function () {

        $http.post("/dicionario", $scope.palavra).then(
            function (response) {
                window.alert("Cadastrado ");
                $location.path("/pagina-principal");
            }, function erro(response) {

                window.alert("Erro ");
            });

    };

}]);