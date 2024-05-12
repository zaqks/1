const imgLbl = document.getElementById("imgLabel")
const imgsList = document.getElementById("imgs")

var imgIndx = 2;


function insertImgs(inpt) {
    var img;
    var reader;
    var file = inpt.files[0];

    img = document.createElement("img")
    img.id = `img#${imgIndx}`


    reader = new FileReader();
    reader.onload = function (e) {
        img.src = e.target.result
    }

    reader.readAsDataURL(file)

    imgsList.appendChild(img)


    imgLbl.htmlFor = `img#${imgIndx}`

    imgIndx++

}