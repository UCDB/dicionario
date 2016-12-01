dicionarioApp.controller("paginaInicial-controller",['$scope', '$location','$http', function ($scope, $location,$http) {

    $(document).ready(function(){
        carregaPalavras();
    });

// Função Busca Palavra Jquery
    $(function(){
        $("#textBusca").keyup(function(){

            //pega o class da tabela
            var tabela = 'table';

            if( $(this).val() != ""){
                $("."+tabela+" tbody>tr").hide();
                $("."+tabela+" td:contains-ci('" + $(this).val() + "')").parent("tr").show();
            } else{
                $("."+tabela+" tbody>tr").show();
            }
        });
    });
    $.extend(	$.expr[":"], {
        "contains-ci": function(elem, i, match, array) {
            return (elem.textContent || elem.innerText || $(elem).text() || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
        }
    });

// Função Delete Jquery
    $(function() {
        $('.deleteRowButton').click(function(){
            $(this).parents('tr').first().remove();
        });
    });

    var objPalavra = [];

    function carregaPalavras(){

        $http.get("/dicionario").then(
            function (response) {
                objPalavra = response.data;



                for(i=0;i<objPalavra.length;i++){
                    //adiciona como um filho
                    $('#lista-palavra').append('<tr class="linha-palavra" data-id="'+objPalavra[i].id+'">'+
                        '<td>'+objPalavra[i].id+'</td>'+
                        '<td>'+objPalavra[i].palavra+'</td>'+
                        '<td>'+objPalavra[i].significado+'</td>'+
                        '<td class="editRowButton" align="center"> <a href="novaPalavra.html"> <i class="fa fa-pencil" aria-hidden="true"></i></a> </td>'+
                        '<td  class="deleteRowButton" align="center"><i class="fa fa-trash" aria-hidden="true"></i> </td>'+
                        '</tr>');
                }


                //alert('Carregado !');
            },function (response) {
                alert('Erro ao buscar palavras !');
            });



        /*$.get("palavras", function(data,status){

         var objPalavras = JSON.parse(data);

         for(i=0; i< objPalavra.length; i++) {
         //valores = '<td>'+ objPalavras[i].id +'</td>';
         }
         }


         $('.corpoTabela').append(valores);

         });*/
    }

    function abrirPalavra(idPalavra){

        // AJAX busca dados da palavra
        // chamar funcao que preenche objeto JSON com todos os dados da palavra

        objPalavra = {
            id: 1,
            titulo: 'titulo',
            descricao: 'descricao',
            data: '22/11/2016'
        };

        $('#idPalavra').val( objPalavra.id );
        $('#tituloPalavra').val( objPalavra.titulo );
        $('#descricaoPalavra').val( objPalavra.descricao );
        $('#dataPalavra').val( objPalavra.data );

        $('#visualizaPalavra').html('aberta a palavra: '+idPalavra);
    }

    function commitPalavra(){

        var tipo;
        var acao;
        if($('#idPalavra').val() == ''){
            tipo = 'POST';
            acao = 'inserida';
        }else{
            tipo = 'PUT';
            acao = 'alterada';
        }

        // JEITO 1
        objPalavra.id = $('#idPalavra').val();
        objPalavra.titulo = $('#tituloPalavra').val();
        objPalavra.descricao = $('#descricaoPalavra').val();
        objPalavra.data = $('#dataPalavra').val();

        /* JEITO 2
         objPalavra = {
         id: $('#idPalavra').val(),
         titulo: $('#tituloPalavra').val(),
         descricao: $('#descricaoPalavra').val(),
         data: $('#dataPalavra').val()
         };*/

        // AJAX  INSERT AND UPDATE
        $.ajax({
            url:'dicionario',
            type:tipo,
            dataType:'json',
            contentType:'application/json;charset=UTF-8',
            data:JSON.stringify(objPalavra),
            success:function(){
                window.alert('Palavra '+ acao +' com Sucesso!');
                carregaPalavras();
            }, error:function(){
                alert('Erro ao tentar executar!');
            }
        });

    }


}]);