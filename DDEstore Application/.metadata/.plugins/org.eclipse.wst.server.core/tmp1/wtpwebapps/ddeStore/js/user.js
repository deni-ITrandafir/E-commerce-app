document.getElementById('filterForm').addEventListener('change', function () {
    const formData = new FormData(this);
    const queryParams = new URLSearchParams(formData).toString();

    fetch('/ddeStore/FilterProductsServlet?' + queryParams)
        .then(response => response.json())
        .then(data => {
            const productContainer = document.getElementById('productList');
            productContainer.innerHTML = '';

            data.forEach(product => {
                const productHTML = `
                    <div class="product-item">
                        <h5>${product.Brand} ${product.Model}</h5>
                        <p>Memorie: ${product.Memorie}, Culoare: ${product.Culoare}</p>
                        <p>Pret: ${product.Pret} RON</p>
                    </div>`;
                productContainer.innerHTML += productHTML;
            });
        });
});


