const headerMenu = document.querySelector('.hm-header');

console.log(headerMenu.offsetTop);

window.addEventListener('scroll', () => {
    if (window.pageYOffset > 80) {
        headerMenu.classList.add('header-fixed');
    } else {
        headerMenu.classList.remove('header-fixed');
    }
});
/*=========================================
    MENU
==========================================*/

const menu = document.querySelector('.icon-menu');
const menuClose = document.querySelector('.cerrar-menu');

menu.addEventListener('click', () => {
    document.querySelector('.header-menu-movil').classList.add('active');
});

menuClose.addEventListener('click', () => {
    document.querySelector('.header-menu-movil').classList.remove('active');
});


// // Escucha los clics en las categorías
document.getElementById('clásicas').addEventListener('click', function(e) {
    getProductsByCategory('Clasicas');
    
});

document.getElementById('exclusivas').addEventListener('click', function(e) {
    // if(openA == false)
    getProductsByCategory('Exclusivas')
});

document.getElementById('limitada').addEventListener('click', function(e) {
    getProductsByCategory('EdicionLimitada')
});

document.getElementById('accesorios').addEventListener('click', function(e) {
    getProductsByCategory('Accesorios')
});



document.getElementById('tab-todo').addEventListener('click', function(e) {
    getProducts();
});
document.getElementById('tab-clasicas').addEventListener('click', function(e) {
    getProductsByCategory('Clasicas');
});

document.getElementById('tab-exclusivas').addEventListener('click', function(e) {
    getProductsByCategory('Exclusivas')
});

document.getElementById('tab-limitada').addEventListener('click', function(e) {
    getProductsByCategory('EdicionLimitada')
});

document.getElementById('tab-accesorios').addEventListener('click', function(e) {
    getProductsByCategory('Accesorios')
});




// Función para abrir el modal con la imagen
function openModal(imageSrc) {
    var modal = document.getElementById('imageModal');
    var modalImage = document.getElementById('modalImage');

    var zoomContainer = document.querySelector('.zoom-container');
    
    modal.style.display = 'block';
    modalImage.src = imageSrc;

    // Añadir eventos de zoom y desplazamiento
    modalImage.addEventListener('mousemove', scrollImage); // Desplazar según el mouse
    modalImage.addEventListener('mouseleave', resetZoom);  // Restablecer zoom al salir
}

// Función para cerrar el modal
function closeModal() {
    var modal = document.getElementById('imageModal');
    var modalImage = document.getElementById('modalImage');
    
    modal.style.display = 'none';

    // Remover eventos de zoom y desplazamiento al cerrar
    modalImage.removeEventListener('mousemove', scrollImage);
    modalImage.removeEventListener('mouseleave', resetZoom);
}

// Función para hacer zoom
function zoomImage(event) {
    var modalImage = event.target;
    var zoomLevel = 2; // Nivel de zoom (2x)
    var x = (event.offsetX / modalImage.offsetWidth) * 100;
    var y = (event.offsetY / modalImage.offsetHeight) * 100;
    
    modalImage.style.transformOrigin = x + "% " + y + "%";
    modalImage.style.transform = "scale(" + zoomLevel + ")";
    modalImage.style.cursor = "zoom-out";
}

// Función para restablecer el zoom
function resetZoom(event) {
    var modalImage = event.target;
    modalImage.style.transform = "scale(1)";
    modalImage.style.cursor = "zoom-in";
}

// Función para desplazar la imagen automáticamente
function scrollImage(event) {
    var modalImage = event.target;
    var zoomContainer = document.querySelector('.zoom-container');
    
    var containerRect = zoomContainer.getBoundingClientRect(); // Tamaño del contenedor
    var imageRect = modalImage.getBoundingClientRect();        // Tamaño de la imagen
    
    var offsetX = event.clientX - containerRect.left; // Posición del mouse en X
    var offsetY = event.clientY - containerRect.top;  // Posición del mouse en Y

    var percentageX = offsetX / containerRect.width;  // Posición en porcentaje X
    var percentageY = offsetY / containerRect.height; // Posición en porcentaje Y

    // Calcular el desplazamiento
    var scrollLeft = (imageRect.width - containerRect.width) * percentageX;
    var scrollTop = (imageRect.height - containerRect.height) * percentageY;

    // Desplazar el contenedor
    zoomContainer.scrollLeft = scrollLeft;
    zoomContainer.scrollTop = scrollTop;
}











document.getElementById('submenuButton').addEventListener('click', function() {
    const submenu = document.querySelector('.submenu-vertical');
    submenu.classList.toggle('hidden');
    console.log("Botón presionado, mostrando/ocultando submenú");
});

// Añadir un evento a cada botón que abre un modal
const modalButtons = document.querySelectorAll('[data-toggle="modal"]');

modalButtons.forEach(button => {
    button.addEventListener('click', function() {
        // Cerrar todos los modales abiertos
        const modals = document.querySelectorAll('.modal.show');
        modals.forEach(modal => {
            $(modal).modal('hide');  // Ocultar el modal si está abierto
        });

        // Abrir el modal correspondiente al botón clicado
        const targetModal = button.getAttribute('data-target');
        $(targetModal).modal('show');
    });
});






const checkoutBtn = document.getElementById('button-comprar');

// Evento para mostrar el modal de compra y ocultar el carrito
checkoutBtn.addEventListener('click', () => {
    // Ocultar el carrito (sección de productos seleccionados)
    cartOverlay.style.display = 'none'; //Oculta el carrito (sección de productos seleccionados

    // Mostrar el modal de confirmación de compra (dirección y método de pago)
    $('#confirmarCompraModal').modal('show');
});
// Evento para volver a mostrar el carrito si el modal se cierra sin confirmar la compra
$('#confirmarCompraModal').on('hidden.bs.modal', () => {
    // Mostrar el carrito nuevamente si el modal se cierra sin completar la compra
    cartOverlay.style.display = 'flex';
});





// Escuchar cambios en el método de pago para actualizar el formulario
document.getElementById('metodoPago').addEventListener('change', function () {
    const metodoSeleccionado = this.value;
    const metodoPagoInfo = document.getElementById('metodoPagoInfo');
    
    // Limpiar el contenedor
    metodoPagoInfo.innerHTML = '';

    // Mostrar campos según el método de pago seleccionado
    if (metodoSeleccionado === 'tarjeta') {
        metodoPagoInfo.innerHTML = `
            <div class="form-group">
                <label for="numeroTarjeta">Número de Tarjeta</label>
                <input type="text" class="form-control" id="numeroTarjeta" placeholder="Introduce tu número de tarjeta">
            </div>
            <div class="form-group">
                <label for="fechaExpiracion">Fecha de Expiración</label>
                <input type="text" class="form-control" id="fechaExpiracion" placeholder="MM/AA">
            </div>
            <div class="form-group">
                <label for="cvv">CVV</label>
                <input type="text" class="form-control" id="cvv" placeholder="Código CVV">
            </div>
        `;
    } else if (metodoSeleccionado === 'pse') {
        metodoPagoInfo.innerHTML = `
            <div class="form-group">
                <label for="banco">Banco</label>
                <select class="form-control" id="banco">
                    <option value="">Seleccione un banco</option>
                    <option value="banco1">Banco pichincha:v</option>
                    <option value="banco2">Bancolombia</option>
                    <!-- Añadir más bancos si es necesario -->
                </select>
            </div>
        `;
    } else if (metodoSeleccionado === 'efectivo') {
        metodoPagoInfo.innerHTML = `
            <p class="texto-blanco">Te proporcionaremos un código para que puedas pagar en puntos de recaudo como Efecty o Baloto.</p>
            <button id="generarCodigoBtn" class="btn btn-warning">Generar Código</button>
            <p id="codigoEfectivo" class="texto-blanco"></p>
        `;




        
        // Añadir el evento para generar código
        document.getElementById('generarCodigoBtn').addEventListener('click', function () {
            const codigo = 'CHINACOTALAMARIKA' + Math.floor(Math.random() * 1000000); // Generar código aleatorio
            document.getElementById('codigoEfectivo').innerText = 'Tu código de pago es: ' + codigo;
        });
    }
});

// Lógica para confirmar la compra
document.getElementById('confirmarCompraBtn').addEventListener('click', function () {
    const direccion = document.getElementById('direccion').value;
    const metodoPago = document.getElementById('metodoPago').value;
    
    if (!direccion) {
        alert('Por favor ingresa tu dirección de envío.');
        return;
    }

    if (metodoPago === 'tarjeta') {
        const numeroTarjeta = document.getElementById('numeroTarjeta').value;
        const fechaExpiracion = document.getElementById('fechaExpiracion').value;
        const cvv = document.getElementById('cvv').value;

        if (!numeroTarjeta || !fechaExpiracion || !cvv) {
            alert('Por favor completa la información de tu tarjeta.');
            return;
        }

        console.log('Pago con tarjeta:', { numeroTarjeta, fechaExpiracion, cvv });
        // Aquí puedes procesar el pago con tarjeta
    } else if (metodoPago === 'pse') {
        const banco = document.getElementById('banco').value;
        if (!banco) {
            alert('Por favor selecciona tu banco.');
            return;
        }

        console.log('Pago con PSE:', banco);
        // Aquí puedes procesar el pago con PSE
    } else if (metodoPago === 'efectivo') {
        const codigoEfectivo = document.getElementById('codigoEfectivo').innerText;
        if (!codigoEfectivo) {
            alert('Por favor genera un código para pagar en efectivo.');
            return;
        }

        console.log('Pago en efectivo:', codigoEfectivo);
        // Aquí puedes procesar el pago en efectivo
    }

    // Cerrar el modal
    $('#confirmarCompraModal').modal('hide');

    // Resetear formulario después de la compra
    document.getElementById('compraForm').reset();
    document.getElementById('metodoPagoInfo').innerHTML = '';
    
    // Opcional: Limpiar el carrito de compras
    cart = [];
    updateCartUI();
});

