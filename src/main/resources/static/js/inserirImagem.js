cont=0;

$("#fileUpload").on('change', function() {

    //Obter a contagem dos arquivos selecionados
    var countFiles =$(this)[0].files.length;

    if(countFiles < 2){

        //endereço div
        var image_holder = $("#image-holder");

        //laço para cada arquivo selecionado
        for (var i = 0; i < countFiles; i++){

            var imgPath = $(this)[0].value;

            var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();


            if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
                if (typeof(FileReader) != "undefined") {

                    //instancia de um objeto para permitir ler conteudo
                    var reader = new FileReader();
                    reader.onload = function(e) {

                        cont++;

                        //id=img +1
                        var nova_div = 'img-'+cont;
                        $(image_holder).append('<div id='+nova_div+' onclick="deletaImg('+cont+')"><span>x</span></div>');

                        $("<img />", {
                            "src": e.target.result,
                            "class": "thumb-image"
                        }).appendTo('#'+nova_div);
                    };
                    image_holder.show();
                    reader.readAsDataURL($(this)[0].files[i]);

                } else {
                    alert("Este navegador não suporta FileReader.");
                }

            } else {
                alert("Selecionar apenas imagens");
            }
        }
    }else{
        alert('Você deve selecionar apenas DUAS imagens!');
    }
});

function deletaImg(idImg){

    if( $('#img-'+idImg).remove() ){
        cont--;
    }

}

// function salvar() {
//
// }
//
// function commitPalavra() {
//
//     var tipo;
//     var acao;
//
//     if ($('#idPalavra').val() == '') {
//         tipo = 'POST';
//         acao = 'inserida';
//     } else {
//         tipo = 'PUT';
//         acao = 'alterada';
//     }
//
//     // JEITO 1
//     objPalavra.id = $('#idPalavra').val();
//     objPalavra.titulo = $('#tituloPalavra').val();
//     objPalavra.descricao = $('#descricaoPalavra').val();
//     objPalavra.data = $('#dataPalavra').val();
//
//     /* JEITO 2
//      objPalavra = {
//      id: $('#idPalavra').val(),
//      titulo: $('#tituloPalavra').val(),
//      descricao: $('#descricaoPalavra').val(),
//      data: $('#dataPalavra').val()
//      };*/
//
//     // AJAX  INSERT AND UPDATE
//     $.ajax({
//         url: 'dicionario',
//         type: tipo,
//         dataType: 'json',
//         contentType: 'application/json;charset=UTF-8',
//         data: JSON.stringify(objPalavra),
//         success: function () {
//             window.alert('Palavra ' + acao + ' com Sucesso!');
//             carregaPalavras();
//         }, error: function () {
//             alert('Erro ao tentar executar!');
//         }
//     });
//
// }
