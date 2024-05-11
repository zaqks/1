































const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

const inpts = [
    "startW",
    "endW",
    "startH",
    "endH"
]

const vals = {}





function updateRange(inpt) {
    const inptName = inpt.id;
    const inptTxtName = inptName.split("Inpt")[0];
    const inptTxt = document.getElementById(inptTxtName);

    var val = inpt.value;
    val = parseInt(val)

    //

    //


    vals[inptTxtName] = parseInt(val)
    if (inptTxtName.includes("H"))
        val = `${val}H`;
    else
        val = days[val]
    inptTxt.innerText = val;
}

function initRanges() {
    var inptName;

    for (const i of inpts) {
        inptName = `${i}Inpt`;

        vals[i] = document.getElementById(inptName).value;
        vals[i] = parseInt(vals[i])

        updateRange(document.getElementById(inptName))
    }

}