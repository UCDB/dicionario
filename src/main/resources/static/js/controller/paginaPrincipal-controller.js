dicionarioApp.controller("paginaInicial-controller",['$scope', '$location','$http', function ($scope, $location,$http) {

    $scope.palavras = [];

        carregaPalavras();

// Função Delete Jquery
    $(function() {
        $('.deleteRowButton').click(function(){
            $(this).parents('tr').first().remove();
        });
    });

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




}]);