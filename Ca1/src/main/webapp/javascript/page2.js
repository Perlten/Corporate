
const TIMER = document.getElementById("timerId");
const TEXTTOTYPE = document.getElementById("textToType");
const RESET = document.getElementById("reset");
const TEXTAREA = document.getElementById("test-area");

var textToType = [
    "Jesper er dejlig, og sådan er det",
    "Jeg kan godt li kagemand",
    "Der var en lille hund i en lille verden",
    "At være eller ikke at være datamatiker",
    "Du vil ikke vide hvad jeg lavede igår",
    "Jesper brugte hele dagen foran spejlet i forberedelse til en fest. Dørmanden lukkede ham ikke ind",
    "Adam har flutes, de er on point"];

var currentText;
var timeRunning = false;
var time = 0;
var refreshInterval;

//Sets the text to e typed
window.addEventListener("load", getSampleText);
TEXTAREA.addEventListener("keyup", checkText);
RESET.addEventListener("click", startOver);

function getSampleText() {
    var index = Math.floor((Math.random() * textToType.length));

    var text = textToType[index];
    TEXTTOTYPE.innerHTML = text;
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

    TIMER.innerText = pad(mins, 2) + ":" + pad(secs, 2) + ":" + pad(ms / 10, 2);

}
function checkText() {
    var typedText = TEXTAREA.value;
    if (!timeRunning) {
        timeRunning = true;
        refreshInterval = setInterval(countUp, 10);
    }

    if (typedText === currentText) {
        console.log("win");
        timeRunning = false;
        clearInterval(refreshInterval);
    }
}
function startOver(){
    time = 0;
    timeRunning = false;
    clearInterval(refreshInterval);
    getSampleText();
    TEXTAREA.value = "";
}



