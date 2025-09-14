const url = 'http://localhost:8080/prd/product';

async function getProducts() {
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        

        if (!response.ok) {
            console.log('Error en la solicitud: ' + response.statusText);
            var productoContenedor = document.getElementById('grid-product');
            productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
        }

        const products = await response.json();
        if(products == '' || products == null){
            console.log("1")
            var productoContenedor = document.getElementById('grid-product');
            productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
        }
        displayProducts(products); // Llamar función para mostrar productos
        applyCartListeners();
        
    } catch (error) {
        console.error('Hubo un problema con la solicitud:', error);
        var productoContenedor = document.getElementById('grid-product');
        productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
    }
}
async function getProductsByCategory(category){
    try {
        const response = await fetch(url+"/category/"+category, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        
        if (!response.ok) {
            console.log('Error en la solicitud: ' + response.statusText);
            var productoContenedor = document.getElementById('grid-product');
            productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
        }

        const products = await response.json();
        if(products == '' || products == null || products == ""){
            console.log("afpoajf");
            console.log(products);
            mostrarError("Error")
        }
        else{
            displayProducts(products); // Llamar función para mostrar productos
            applyCartListeners();
        }
        
    } catch (error) {
        var productoContenedor = document.getElementById('grid-product');
        productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos</div>`
        console.error('Hubo un problema con la solicitud:', error);
    }
}


function mostrarError(mensajeError){
    var productoContenedor = document.getElementById('grid-product');
    productoContenedor.innerHTML = `<div> ${mensajeError}</div>`

}

// Función para mostrar los productos en el HTML
function displayProducts(products) {
    var productoContenedor = document.getElementById('grid-product');
    productoContenedor.innerText = ''; // Limpiar el contenido previo
    
    products.forEach(product => {
        var productItem = document.createElement('li');
        productItem.id = "product-item";
        productItem.className= "product-item"
        productItem.innerHTML = `<div class="p-portada">
                                    <a href="javascript:void(0);" onclick="openModal('.${product.prodImg}')">
                                        <img src=".${product.prodImg}" alt="">
                                    </a>
                                    <span class="stin stin-new">${product.prodActive}</span>
                                </div>
                                <div class="p-info">
                                    <a href="#"> <h3>${product.prodName}</h3></a>
                                <div class="precio">
                                    <span>$${product.prodPrecio}</span>
                                </div>
                                    <a href="#" class="hm-btn btn-primary uppercase">AGREGAR AL CARRITO</a>
                                </div>`;

        productoContenedor.appendChild(productItem);
    });
}

document.addEventListener('DOMContentLoaded', () =>{
    // Llamar la función cuando cargue la página
    getProducts();
})
const url = 'http://localhost:8080/prd/product';

async function getProducts() {
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        

        if (!response.ok) {
            console.log('Error en la solicitud: ' + response.statusText);
            var productoContenedor = document.getElementById('grid-product');
            productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
        }

        const products = await response.json();
        if(products == '' || products == null){
            console.log("1")
            var productoContenedor = document.getElementById('grid-product');
            productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
        }
        displayProducts(products); // Llamar función para mostrar productos
        applyCartListeners();
        
    } catch (error) {
        console.error('Hubo un problema con la solicitud:', error);
        var productoContenedor = document.getElementById('grid-product');
        productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
    }
}
async function getProductsByCategory(category){
    try {
        const response = await fetch(url+"/category/"+category, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        
        if (!response.ok) {
            console.log('Error en la solicitud: ' + response.statusText);
            var productoContenedor = document.getElementById('grid-product');
            productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos </div>`
        }

        const products = await response.json();
        if(products == '' || products == null || products == ""){
            console.log("afpoajf");
            console.log(products);
            mostrarError("Error")
        }
        else{
            displayProducts(products); // Llamar función para mostrar productos
            applyCartListeners();
        }
        
    } catch (error) {
        var productoContenedor = document.getElementById('grid-product');
        productoContenedor.innerHTML = `<div> Hubo un error al buscar los productos</div>`
        console.error('Hubo un problema con la solicitud:', error);
    }
}


function mostrarError(mensajeError){
    var productoContenedor = document.getElementById('grid-product');
    productoContenedor.innerHTML = `<div> ${mensajeError}</div>`

}

// Función para mostrar los productos en el HTML
function displayProducts(products) {
    var productoContenedor = document.getElementById('grid-product');
    productoContenedor.innerText = ''; // Limpiar el contenido previo
    
    products.forEach(product => {
        var productItem = document.createElement('li');
        productItem.id = "product-item";
        productItem.className= "product-item"
        productItem.innerHTML = `<div class="p-portada">
                                    <a href="javascript:void(0);" onclick="openModal('.${product.prodImg}')">
                                        <img src=".${product.prodImg}" alt="">
                                    </a>
                                    <span class="stin stin-new">${product.prodActive}</span>
                                </div>
                                <div class="p-info">
                                    <a href="#"> <h3>${product.prodName}</h3></a>
                                <div class="precio">
                                    <span>$${product.prodPrecio}</span>
                                </div>
                                    <a href="#" class="hm-btn btn-primary uppercase">AGREGAR AL CARRITO</a>
                                </div>`;

        productoContenedor.appendChild(productItem);
    });
}

document.addEventListener('DOMContentLoaded', () =>{
    // Llamar la función cuando cargue la página
    getProducts();
})