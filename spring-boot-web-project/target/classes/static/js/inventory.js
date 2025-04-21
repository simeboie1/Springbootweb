function filterTable() {
    const searchValue = document.getElementById("searchBar").value.toLowerCase();
    const rows = document.querySelectorAll(".productTable tbody tr");

    rows.forEach(row => {
        const name = row.querySelector("td:nth-child(2)").textContent.toLowerCase();
        row.style.display = name.includes(searchValue) ? "" : "none";
    });
}

function filterByStock() {
    const filterValue = document.getElementById("stockFilter").value;
    const rows = document.querySelectorAll(".productTable tbody tr");

    rows.forEach(row => {
        const stockCell = row.querySelector("td:nth-child(5)");
        if (!stockCell) return;

        const stock = parseInt(stockCell.textContent.trim(), 10);

        row.classList.remove("low-stock", "out-of-stock"); // Reset classes

        if (isNaN(stock)) {
            row.style.display = ""; // Show row if stock is invalid
            return;
        }

        // Add highlighting classes
        if (stock === 0) {
            row.classList.add("out-of-stock");
        } else if (stock <= 10) {
            row.classList.add("low-stock");
        }

        // Apply filter logic
        if (filterValue === "low" && stock > 10) {
            row.style.display = "none";
        } else if (filterValue === "out" && stock > 0) {
            row.style.display = "none";
        } else {
            row.style.display = "";
        }
    });
}