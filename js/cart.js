
/*=========================================
    CARRITO DE COMPRAS
==========================================*/

// Variables globales para el carrito
let cart = [];
const cartOverlay = document.querySelector('.cart-overlay');
const cartItemsContainer = document.querySelector('.cart-items');
const totalAmountContainer = document.querySelector('.total-amount');

// Función para agregar productos al carrito
function addToCart(product) {
    const existingProduct = cart.find(item => item.id === product.id);
    if (existingProduct) {
        existingProduct.quantity += 1; // Aumentar la cantidad si el producto ya está en el carrito
    } else {
        cart.push({ ...product, quantity: 1 }); // Agregar un nuevo producto con cantidad 1
    }
    updateCartUI();
}







// Actualizar la interfaz de usuario del carrito
function updateCartUI() {
    const cartCountElement = document.querySelector('.hm-icon-cart span');
    const cartItemCount = cart.reduce((sum, item) => sum + item.quantity, 0);
    cartCountElement.innerText = cartItemCount; // Mostrar la cantidad total de artículos en el carrito
    
    // Actualizar los elementos del carrito en el overlay
    cartItemsContainer.innerHTML = ''; // Limpiar el contenido anterior
    let totalAmount = 0;
    cart.forEach(product => {
        const cartItem = document.createElement('li');
        cartItem.classList.add('cart-item');
        cartItem.innerHTML = `
            <img src="${product.image}" alt="${product.name}">
            <div class="item-info">
                <h4>${product.name}</h4>
                <p>Cantidad: 
                    <button class="decrease-quantity" data-id="${product.id}">-</button>
                    ${product.quantity}
                    <button class="increase-quantity" data-id="${product.id}">+</button>
                </p>
                <p>Precio: $${(product.price * product.quantity).toLocaleString()}</p>
                <button class="remove-item" data-id="${product.id}">Eliminar</button>
            </div>
        `;
        cartItemsContainer.appendChild(cartItem);
        totalAmount += product.price * product.quantity;
    });
    totalAmountContainer.innerText = `$${totalAmount.toLocaleString()}`;

    const goShopBtn = document.getElementById('go-shop-btn');
    const checkoutBtn = document.getElementById('button-comprar');

    // Mostrar u ocultar el botón de ir a comprar si el carrito está vacío
    if (cart.length === 0) {
        checkoutBtn.style.display = 'none';
        goShopBtn.style.display = 'block';
    } else {
        checkoutBtn.style.display = 'block';
        goShopBtn.style.display = 'none';
    }
    
    // Event listeners para aumentar y disminuir cantidad como el video 
    document.querySelectorAll('.increase-quantity').forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault();
            const productId = event.target.getAttribute('data-id');
            const product = cart.find(item => item.id === productId);
            product.quantity += 1; // Incrementar la cantidad
            updateCartUI(); // Actualizar la interfaz de usuario
        });
    });

    document.querySelectorAll('.decrease-quantity').forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault();
            const productId = event.target.getAttribute('data-id');
            const product = cart.find(item => item.id === productId);
            if (product.quantity > 1) {
                product.quantity -= 1; // Disminuir la cantidad
            } else {
                cart = cart.filter(item => item.id !== productId); // Eliminar el producto si la cantidad es 1
            }
            updateCartUI(); // Actualizar la interfaz de usuario
        });
    });

    // Event listeners para eliminar productos
    document.querySelectorAll('.remove-item').forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault();
            const productId = event.target.getAttribute('data-id');
            cart = cart.filter(product => product.id !== productId);
            updateCartUI();
        });
    });
}
// Event listener para el botón "Ir a comprar"
document.getElementById('go-shop-btn').addEventListener('click', () => {
    // Simula un clic en la pestaña de "Gorras" o redirige a la sección de gorras
    document.querySelector('.hm-tabs li:nth-child(1)').click(); 
    cartOverlay.style.display = 'none'; // Ocultar el carrito
});

// Event Listener para el botón "Agregar al Carrito"
async function applyCartListeners(){
    document.querySelectorAll('.hm-btn.btn-primary').forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); 
    
            const productElement = button.closest('.product-item');
            const product = {
                id: productElement.querySelector('h3').innerText, 
                name: productElement.querySelector('h3').innerText,
                price: parseFloat(productElement.querySelector('.precio span').innerText.replace('$', '').replace('.', '')),
                image: productElement.querySelector('img').src,
            };
            addToCart(product); // Llama la función para agregar el producto al carrito
        });
    });
}

// Mostrar el carrito al hacer clic en el icono del carrito
document.querySelector('.hm-icon-cart').addEventListener('click', () => {
    if (cart.length === 0) {
        showAlert("Ole care chimba, va a comprar sin agregar al carrito nada ? awebado");
    } else {
        cartOverlay.style.display = 'flex'; // Mostrar el carrito si hay productos
    }
});


// Ocultar el carrito cuando se hace clic en la X
document.querySelector('.close-cart').addEventListener('click', () => {
    cartOverlay.style.display = 'none';
});

// Función para mostrar el mensaje de alerta personalizado
function showAlert(message) {
    console.log("Mostrando alerta: ", message);  // Verificar si se llama a la función
    const alertMessage = document.getElementById('mensaje-de-alerta');
    alertMessage.textContent = message;
    alertMessage.style.display = 'block';
    setTimeout(() => {
        alertMessage.style.display = 'none';  // sin esta linea, la linea de abajo 4000 no funciona porque le tenia otras cosas
    }, 4000); // Ocultar el mensaje después de 4 segundos o pues ya lo hablaremos xd 
}


//REDIGIR CUANDO SE ELIMINAR LOS PRODUCTOS Y SE VA A IR A COMPRAR
document.getElementById('go-shop-btn').addEventListener('click', () => {
    // Desactivar todas las pestañas
    document.querySelectorAll('.hm-tab-link').forEach(tab => tab.classList.remove('active'));

    // Activar la pestaña de Gorras, Porque gorras es la primera que esta
    const gorrasTab = document.querySelector('.hm-tab-link:nth-child(1)');
    gorrasTab.classList.add('active');

    // se oculta todo el contenido de las pestañas
    document.querySelectorAll('.tabs-content').forEach(content => content.classList.remove('tab-active'));

    // muestra el contenido de las gorras
    const gorrasContent = document.getElementById('Productos');
    gorrasContent.classList.add('tab-active');

    // desplaza la página hasta la sección de Productos populares
    const productosPopularesSection = document.getElementById('Productos');
    productosPopularesSection.scrollIntoView({ behavior: 'smooth', block: 'start' });

    // ajustar el desplazamiento un poco hacia arriba, mueva el 25 y 500 segun su necidad porque creo que esa cosa con pantalla grande o chica se nos va a joder :V
    setTimeout(() => {
        window.scrollBy(0, 25); // Con esta vuelta, el 25, se cuadro para que quedara  y se viera el productos populares y se vea las gorras y arriba pa que no se oculte esa chimbada
    }, 500); // y esta cosa trabaja de la mano con la de arriba
});
