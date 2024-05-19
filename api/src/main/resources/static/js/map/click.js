var map = L.map("img").setView([31, 3], 6);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);



var marker;
var lat, lng;

map.on("click", function posRef(e) {
    lat = e.latlng.lat;
    lng = e.latlng.lng;

    marker = L.marker([lat, lng]);
    marker.addTo(map)
        .bindPopup('Your Property')
        .openPopup();

    

    return ref;
});
