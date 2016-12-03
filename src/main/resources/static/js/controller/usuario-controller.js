dicionarioApp.controller("usuarioController", function($scope,$http,$location,dataShare){
    //$scope.usuarios =[];



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


        $scope.cadastrar = function () {

            if($scope.usuario.id == null){

            $http.post("/usuario", $scope.usuario).then(
                function (response) {
                    window.alert("Cadastrado ");
                    $scope.usuario = {};
                    $scope.carregarLista();
                }, function erro(response) {

                    window.alert("Erro ");
                });
        }else{
                $http.put("/usuario", $scope.usuario).then(
                    function (response) {
                        window.alert("Alterado com sucesso ");
                        $scope.usuario = {};
                        $scope.carregarLista();
                    }, function erro(response) {
                        window.alert("Erro ");
                    });
            }

    };

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
        $http.delete("/usuario/"+id).then(
            function (response) {
                window.alert("Excluido com sucesso ");
                $scope.carregarLista();
            }, function erro(response) {
                window.alert("Erro ");
            });
    };

});

