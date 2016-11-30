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


    $scope.editar = function (id) {


        for(var i = 0; i < $scope.usuarios.length;i++){
            if(id == $scope.usuarios[i].id){
                $scope.usuario= $scope.usuarios[i];
                $scope.visualizar = "true";
            }
        }
    };

    $scope.cadastrarUsuario = function () {
        $scope.visualizar = "true";
    };
    $scope.cancelar = function () {
        $scope.visualizar = "";
        $scope.usuario = {};

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
