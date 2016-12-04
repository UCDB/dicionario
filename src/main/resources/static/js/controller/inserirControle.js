dicionarioApp.controller("inserir-controller",['$scope', '$location','dataShare','$http','toastr', function ($scope, $location,dataShare,$http,toastr) {

    if(dataShare.getData()){
        $scope.palavra = dataShare.getData();
        dataShare.setData("");
    }


    $scope.cancelar = function () {
        $location.path("/pagina-principal");
    };


    $scope.cadastrarPalavra = function () {

        if ($scope.palavra.id == null){

            $http.post("/adm/dicionario", $scope.palavra).then(
                function (response) {

                    toastr.success("Palavra Cadastrada !");
                    $location.path("/pagina-principal");
                }, function erro(response) {
                    toastr.error(response.data.message,"Erro");

                });

        }else{


            $http.put("/adm/dicionario", $scope.palavra).then(
                function (response) {

                    toastr.success("Palavra Editada !");
                    $location.path("/pagina-principal");
                }, function erro(response) {

                    toastr.error(response.data.message,"Erro");
                });

        }



    };

}]);