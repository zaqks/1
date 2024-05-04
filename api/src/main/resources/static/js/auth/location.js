const FETCHES = 5;
const xInpt = document.getElementById("x_coord")
const yInpt = document.getElementById("y_coord")
const btn = document.getElementById("submitBtn")

var x = 0;
var y = 0;
var ready = false;


function getLocation() {
    console.log('pass')

    if ("geolocation" in navigator) {
        //ask for permission
        navigator.geolocation.getCurrentPosition(
            function (position) {
                x = position.coords.latitude;
                y = position.coords.longitude;


                xInpt.value = `${x}`
                yInpt.value = `${y}`

                ready = true;
                return

            },
            function (err) {
                ready = true;
                return
            }
        );
    }
}


getLocation()