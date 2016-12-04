dicionarioApp.controller("usuarioController", function($scope,$http,$location,dataShare,toastr){
    //$scope.usuarios =[];



    $scope.usuario ={};

    $scope.visualizar= "";


    //busca os usuarios cadastrados no banco e mostra na tabela
    $scope.carregarLista = function() {
        $scope.usuarios=[];
        $http.get("/adm/usuario").then(
            function (response) {
                $scope.usuarios = response.data;

            }, function erro(response) {

                toastr.error(response.data.message,"Erro");
            });
    };

    $scope.carregarLista();

    // metodos para cadastrar e alterar usuario


        $scope.cadastrar = function () {

            if($scope.usuario.id == null){

            $http.post("/adm/usuario", $scope.usuario).then(
                function (response) {
                    toastr.success("Usuário Cadastrado !","OK");
                    $scope.usuario = {};
                    $scope.carregarLista();
                    $scope.visualizar = "";
                }, function erro(response) {

                    toastr.error(response.data.message,"Erro");
                });
        }else{
                $http.put("/adm/usuario", $scope.usuario).then(
                    function (response) {
                        toastr.success("Usuário Editado !","OK");
                        $scope.usuario = {};
                        $scope.carregarLista();
                    }, function (response) {
                        toastr.error(response.data.message,"Erro");
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
        $http.delete("/adm/usuario/"+id).then(
            function (response) {
                toastr.success("Usuário Excluido !","OK");
                $scope.carregarLista();
            }, function erro(response) {
                toastr.error(response.data.message,"Erro");
            });
    };

});

