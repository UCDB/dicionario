dicionarioApp.controller("paginaInicial-controller",['$scope', '$location', function ($scope, $location) {



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


// Função Edit Jquery
//var _trEdit = null;
//$(document).on('click', '.editRowButton', function(){
//_trEdit = $(this).closest('tr');
//$(_trEdit).find('td:eq(0)').text();

//$('input[name="id"]').val(_id);

//});


}]);