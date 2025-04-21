function submitProductForm(event){
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(event.target);

    fetch("/products", {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if(response.ok) {
            loadProducts();
        } else {
            alert("Failed to create product.");
        }
    })
}


function loadProducts() {
    fetch("/inventory")
        .then(response => response.text())
        .then(html => {
            document.querySelector('.mainWindowBox').innerHTML = html;
        })
        .catch(error => {
            console.error('Error loading products:', error);
        });
}
/*
document.addEventListener("DOMContentLoaded", () => {
    const productForm = document.getElementById("productForm");

    productFormat.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent the default form submission
        
        const formData = new FormData(productForm);
        const productData = new Object.fromEntries(formData.entries());

        fetch("/products", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(productData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to create a product");
            }
            return response.json();
        })
        
        .then(() => {
        
            loadContent("/products", event);
            
    })
        .catch(error => {
            console.error("Error:", error);
            alert("Failed to create a product. Please try again.");
        });
    });
});
*/