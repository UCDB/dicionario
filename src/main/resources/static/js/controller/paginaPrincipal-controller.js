dicionarioApp.controller("paginaInicial-controller",['$scope', '$location','$http','dataShare','toastr', function ($scope, $location,$http,dataShare,toastr) {

    $scope.palavras = [];

    carregaPalavras();

    $scope.cadPalavra = function () {
        $location.path("/cad-palavra");
    };


    function carregaPalavras(){
        $scope.palavras = [];
        $scope.informacoes ="";
        $http.get("/adm/dicionario").then(
            function (response) {
                $scope.palavras = response.data;

            },function (response) {
                toastr.error(response.data.message,"Erro");
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

    $scope.info = function (palavara) {
      $scope.informacoes =   palavara;
    };


    $scope.excluir = function (id) {

        $http.delete("/adm/dicionario/"+id).then(
            function (response) {
                carregaPalavras();
                toastr.success("Excluido","OK");

            }, function erro(response) {

                toastr.error(response.data.message,"Erro");
            });
    };




}]);