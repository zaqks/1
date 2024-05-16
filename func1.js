// getting the map
var map = L.map("map").setView([51.505, -0.09], 13);

var ref = { lat: "", lng: "" };

map.on("click", function posRef(e) {
  ref.lat = e.latlng.lat;
  ref.lng = e.latlng.lng;
  alert("Lat, Lon : " + ref.lat + ", " + ref.lng);

  return ref;
});
