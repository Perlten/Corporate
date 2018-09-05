

var textToType = [
    "Jesper er dejlig, og sådan er det",
    "Jørgen så en fugl flyve til de vame lande",
    "Der var en lille hund i en lille verden",
    "At være eller ikke at være datamatiker",
    "Du vil ikke vide hvad jeg lavede igår",
    "Jesper brugte hele dagen foran spejlet i forberedelse til en fest. Dørmanden lukkede ham ikke ind",
    "Adam har flutes, de er on point"];

var currentText;
var running = false;
var time = 0;
var refreshInterval;

//Sets the text to e typed
window.addEventListener("load", getSampleText);
window.addEventListener("keyup", checkText);
document.getElementById("reset").addEventListener("click", startOver);

function getSampleText() {
    var index = Math.floor((Math.random() * textToType.length));

    var text = textToType[index];
    document.getElementById("textToType").innerHTML = text;
    currentText = text;
}
function pad(n, width, z) {
    z = z || '0';
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
}
function countUp() {
    time++

    var s = time * 10;

    var ms = s % 1000;
    s = (s - ms) / 1000;
    var secs = s % 60;
    s = (s - secs) / 60;
    var mins = s % 60;

    document.getElementById("timerId").innerText = pad(mins, 2) + ":" + pad(secs, 2) + ":" + pad(ms / 10, 2);

}
function checkText() {
    var typedText = document.getElementById("test-area").value;
    if (!running) {
        running = true;
        refreshInterval = setInterval(countUp, 10);
    }

    if (typedText === currentText) {
        running = false;
        clearInterval(refreshInterval);
    }
}
function startOver(){
    time = 0;
    getSampleText();
    document.getElementById("test-area").value = "";
}



