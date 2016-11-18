
var token = {};


function logar(){

    if( $('#usuario').val() != '' && $('#senha').val() != '') {

        var objUsuario = {
            login: $('#usuario').val(),
            senha: $('#senha').val()
        };
        $.ajax({
            url: 'autenticar',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(objUsuario),
            success: function (resut) {

                var token = resut.data;
                console.log(resut);



            }
        });

    }
};
