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
            if (url.includes('/dashboard')) {
                loadDashboardDependencies();
            }
        })
        .catch(error => {
            // Display an error message if the content cannot be loaded
            mainWindowBox.innerHTML = `<p>Error loading content: ${error.message}</p>`;
        });
}

function loadDashboardDependencies() {
    // Load dashboard.js dynamically
    const script = document.createElement('script');
    script.src = '/js/dashboard.js';
    script.type = 'text/javascript';
    document.body.appendChild(script);

    // Load dashboard.css dynamically
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = '/dashboard.css';
    document.head.appendChild(link);
}

// 🆕 Load content on page load if ?load=... is present in URL
document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    const load = params.get('load');

    if (load) {
        loadContent(`/${load}`);
    }
});
