// style de la map

L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
  maxZoom: 19,
  attribution:
    '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
}).addTo(map);

console.log("map : ", map);

// map.on("click", function posRef(e) {
//   ref.lat = e.latlng.lat;
//   ref.lng = e.latlng.lng;
//   alert("Lat, Lon : " + ref.lat + ", " + ref.lng);
//   return ref;
// });

// goTo(50, 50);
