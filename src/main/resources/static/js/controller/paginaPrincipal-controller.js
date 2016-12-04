dicionarioApp.controller("paginaInicial-controller",['$scope', '$location','$http','dataShare', function ($scope, $location,$http,dataShare) {

    $scope.palavras = [];

    carregaPalavras();

    $scope.cadPalavra = function () {
        $location.path("/cad-palavra");
    };


    function carregaPalavras(){
        $scope.palavras = [];

        $http.get("/adm/dicionario").then(
            function (response) {
                $scope.palavras = response.data;

            },function (response) {
                alert('Erro ao buscar palavras !');
            });
    };


    $scope.editar = function (id) {

        for(var i = 0; i < $scope.palavras.length;i++){
            if(id == $scope.palavras[i].id){

                dataShare.setData($scope.palavras[i]);
                $location.path("/cad-palavra");

            }
        }
    };

    $scope.info = function (palavara) {
      $scope.informacoes =   palavara;
    };


    $scope.excluir = function (id) {

        $http.delete("/adm/dicionario/"+id).then(
            function (response) {
                window.alert("Excluido ");
                carregaPalavras();
            }, function erro(response) {

                window.alert("Erro ");
            });
    };




}]);