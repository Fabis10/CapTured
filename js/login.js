const url = 'https://dq5jx513-8080.use2.devtunnels.ms/usr/user'


document.getElementById('login-box').addEventListener('submit', function(event) {
    event.preventDefault();
    getUser();
});

var aviso = document.getElementById('avisoUsuario')

var loginBox = document.getElementById('login-contenedor');

async function getToken(){
    
}
async function getUser(){
    const usrUsername = document.getElementById('username').value;
    const usrPassword = document.getElementById('password').value;

    const user = await fetch(url+'/nm/'+usrUsername, {
        method: 'GET',
        headers: {
            'Authorization': 'Basic ' + btoa('moder:1234')
        }
    }).then(response => {
        switch(response.status){
            case 202:
                aviso.innerText = `Inicio correctamente sesion`
                return response.json();
            case 404:
                aviso.innerText = "Usuario no encontrado"
                aviso.classList.toggle(`avisoUsuarioIncorrecto`)              
                break
            default:
                aviso.innerText = "Error inesperado"
                aviso.classList.toggle('avisoUsuarioIncorrecto')
                throw new Error(`Error inesperado: ${response.status}`)
        }
    })
    .catch(error => {
        console.error(error)
        aviso.innerText = "Error de servidor"
    })

    // if(usrPassword == user.usrPassword){
    //     localStorage('')
    //     loginBox.innerHTML = `
    //     <span>Inicio de sesion correcto</span><br>
    //     <span>Bienvenido ${usrUsername}</span><br>
    //     <a href="../index.html">Pagina principal</a>`
    //     loginBox.classList.add('usuarioCorrecto')

    //     console.log(localStorage.getItem(usuario))

    // }else {
    //     aviso.innerText =("Contraseña incorrecta")
    //     aviso.classList.toggle('avisoUsuarioIncorrecto')
    // }

    
}