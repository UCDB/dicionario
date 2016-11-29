dicionarioApp.controller("usuarioController", function($scope,$http,$location){
    //$scope.usuarios =[];

    $(document).ready(function() {
        $http.get("/usuario").then(
            function (response) {
                $scope.usuarios = response.data;

            }, function erro(response) {

                window.alert("Erro ao carregar dados");
            });
    });

    $scope.visualizar= "";

    $scope.cadastrar = function () {

        $http.post("/usuario", $scope.usuario).then(
            function (response) {
                window.alert("Cadastrado ");
            }, function erro(response) {

                window.alert("Erro ");
            });

    };

    $scope.cadastrarUsuario = function () {
        $scope.visualizar = "true";
    };
    $scope.cancelar = function () {
        $scope.visualizar = "";
        $scope.usuario = {};
        $scope.usuario.login = "";
    }



});

// function cadastrarUsuario(){
//     $('.tela-cadastro').slideDown();
//     $('#btnCadastrar').fadeOut();
//
// }
//
// function esconderCadastro(){
//     $('.tela-cadastro').slideUp();
//     $('#btnCadastrar').fadeIn();
//
//     $('.tela-cadastro input').val('');
// }
