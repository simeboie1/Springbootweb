function loadContent(url, event) {
    // Prevent the default navigation behavior
    if (event) {
        event.preventDefault();
    }

    // Get the mainWindowBox element
    const mainWindowBox = document.querySelector('.mainWindowBox');

    // Fetch the content from the server
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(html => {
            // Load the fetched HTML into the mainWindowBox
            mainWindowBox.innerHTML = html;

            // Dynamically load dashboard-specific JavaScript and CSS if the dashboard is loaded
        
        })
        .catch(error => {
            // Display an error message if the content cannot be loaded
            mainWindowBox.innerHTML = `<p>Error loading content: ${error.message}</p>`;
        });
}
