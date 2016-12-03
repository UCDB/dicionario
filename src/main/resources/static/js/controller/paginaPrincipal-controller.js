dicionarioApp.controller("paginaInicial-controller",['$scope', '$location','$http','dataShare', function ($scope, $location,$http,dataShare) {

    $scope.palavras = [];

    carregaPalavras();

    $scope.cadPalavra = function () {
        $location.path("/cad-palavra");
    };


    function carregaPalavras(){

        $http.get("/dicionario").then(
            function (response) {
                $scope.palavras = response.data;

            },function (response) {
                alert('Erro ao buscar palavras !');
            });
    }

    $scope.editar = function (id) {

        for(var i = 0; i < $scope.palavras.length;i++){
            if(id == $scope.palavras[i].id){

                dataShare.setData($scope.palavras[i]);
                $location.path("/cad-palavra");

            }
        }
    };


    $scope.excluir = function (id) {

        $http.delete("/dicionario/"+id).then(
            function (response) {
                window.alert("Excluido ");
                carregaPalavras();
            }, function erro(response) {

                window.alert("Erro ");
            });
    }




}]);