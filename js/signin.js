const url = 'https://localhost:3000/usr/user';
document.getElementById('login-box').addEventListener('submit', function(event) {
    event.preventDefault();
    postDatos()
    // Hacer la solicitud POST a la API
});


var aviso = document.getElementById('avisoUsuario')

var loginBox = document.getElementById('login-contenedor');

async function postDatos() {

    var salioBien = new Boolean;
    // Obtener los valores de los campos del formulario
    const usrNombres = document.getElementById('usrNombre').value;
    const usrApellidos = document.getElementById('usrApellidos').value;
    const usrUsername = document.getElementById('usrUsername').value;
    const usrEmail = document.getElementById('usrEmail').value;
    const usrDireccion = document.getElementById('usrDireccion').value;
    const usrTelefono = document.getElementById('usrNumeroDeTelefono').value;
    const usrIdentificacion = document.getElementById('usrDocumento').value;
    const usrPassword = document.getElementById('usrPassword').value;
    
    // // Crear el objeto de datos a enviar
    const data = {
        usrApellidos: usrApellidos,
        usrDireccion: usrDireccion,
        usrEmail: usrEmail,
        usrIdentificacion: usrIdentificacion,
        usrNombres: usrNombres,
        usrPassword: usrPassword,
        usrTelefono: usrTelefono,
        usrActive: 'activo',
        usrUsername: usrUsername,
        role: 'USER'
    };
    console.log(data);
    
    try {
        
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            switch(response.status){
                case 201:
                    salioBien = true
                    return response.json();
                case 302:
                    salioBien = false;
                    break;
                default:
                    throw new Error(`Error inesperado: ${response.status}`)
            }
        })
        .catch(error => {
            aviso.innerText =("Error")
        });    
    } catch (error) {
        aviso.innerText =('Error al realizar la solicitud:', error);
    }

    if(salioBien == true){
        salioBien = true;
        loginBox.innerHTML = `
        <span>Se creo correctamente el usuario ${usrUsername}</span><br>
        <span>Bienvenido ${usrNombres}</span><br>
        <a href="../index.html">Pagina principal</a>`
        loginBox.classList.add('usuarioCorrecto')
    } else if(salioBien == false){
        aviso.innerText =("Error")
        aviso.classList.toggle('avisoUsuarioIncorrecto')
    }
}