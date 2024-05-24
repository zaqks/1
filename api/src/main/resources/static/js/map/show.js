const map = L.map("map").setView([31, 3], 6);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);


var marker

function showCoords() {
    const mapW = document.getElementById("map")

    var x = mapW.getAttribute("x")
    var y = mapW.getAttribute("y")

    x = parseFloat(x)
    y = parseFloat(y)

    marker = L.marker([x, y]);
    marker.addTo(map)
        .bindPopup('Property Location')
        .openPopup();
}