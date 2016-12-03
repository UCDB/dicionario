dicionarioApp.controller("usuarioController", function($scope,$http,$location){


    $scope.usuario ={};

    $scope.visualizar= "";


    //busca os usuarios cadastrados no banco e mostra na tabela
    $scope.carregarLista = function() {
        $http.get("/usuario").then(
            function (response) {
                $scope.usuarios = response.data;

            }, function erro(response) {

                window.alert("Erro ao carregar dados");
            });
    };

    $scope.carregarLista();

    // metodos para cadastrar e alterar usuario
    if($scope.usuario.id != " "){
        $scope.cadastrar = function () {

            $http.post("/usuario", $scope.usuario).then(
                function (response) {
                    window.alert("Cadastrado ");
                    $scope.carregarLista();
                }, function erro(response) {

                    window.alert("Erro ");
                });


        };
    }else{
        $scope.alterar = function () {

            $http.put("/usuario", $scope.usuario).then(
                function (response) {
                    window.alert("Alterado com sucesso ");
                }, function erro(response) {
                    window.alert("Erro ");
                });
            $scope.carregarLista();
        };
    }

    //pegar o usuario da tabela e jogar no formulario de cadastro
    $scope.editar = function (id) {

        for(var i = 0; i < $scope.usuarios.length;i++){
            if(id == $scope.usuarios[i].id){
                $scope.usuario= $scope.usuarios[i];
                $scope.visualizar = "true";
            }
        }
    };


    // visualizar o formulario de cadastro de usuario ao clicar no botão "Cadastrar Usuário"
    $scope.cadastrarUsuario = function () {
        $scope.visualizar = "true";
        $scope.usuario = {};

    };

    //ocultar e limpar o formulario de cadastro de usuario
    $scope.cancelar = function () {
        $scope.visualizar = "";
        $scope.usuario = {};
    };

    $scope.excluir = function (id) {
        $http.delete("/usuario", $scope.id).then(
            function (response) {
                window.alert("Excluido com sucesso ");
            }, function erro(response) {
                window.alert("Erro ");
            });
    };

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
