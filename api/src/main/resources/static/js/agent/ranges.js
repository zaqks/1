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

    //ze checking
    //
    for (const letter of ["W", "H"]) {
        if (inptName.includes(letter)) {
            if (inptName.includes("start")) {
                //needs to be less than end
                if (val >= vals[`end${letter}`]) {
                    inpt.value = `${vals[`end${letter}`] - 1}`
                    return
                }
            }
            else {
                //needs to be larger than start
                if (val <= vals[`start${letter}`]) {
                    inpt.value = `${vals[`start${letter}`] + 1}`
                    return
                }

            }

            break;
        }

    }

    //
    vals[inptTxtName] = val
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
    }

    for (const i of inpts) {
        inptName = `${i}Inpt`;

        updateRange(document.getElementById(inptName))
    }

}