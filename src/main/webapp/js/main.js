var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {

        $("#mi-perfil-btn").attr("href", "profile.html?username=" + username);

        //$("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getMascota(false, "ASC");

        $("#ordenar-tipo").click(ordenarMascota);
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getMascota(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarMascota(parsedResult);
            } else {
                console.log("Error recuperando los datos de las mascotas");
            }
        }
    });
}

function mostrarMascota(mascota) {

    let contenido = "";

    $.each(mascota, function (index, mascota) {

        mascota = JSON.parse(mascota);

        contenido += '<tr><th scope="row">' + mascota.id_mascota + '</th>' +
                '<td>' + mascota.tipo + '</td>' +
                '<td>' + mascota.nombre_mascota + '</td>' +
                '<td>' + mascota.raza + '</td>' +
                '<td>' + mascota.edad + '</td>' +
                '<td>' + mascota.tamano + '</td>' +
                '<td>' + mascota.peso + '</td>' +
                '<td>' + mascota.color + '</td>' +
                '<td>' + mascota.ciudad + '</td>' +
                '<td>' + mascota.ruta_foto + '</td>' +
                '<td>' + mascota.fecha_perdido + '</td>' +
                '<td>' + mascota.otros + '</td>';
         

    });
    $("#mascotas-tbody").html(contenido);
}
