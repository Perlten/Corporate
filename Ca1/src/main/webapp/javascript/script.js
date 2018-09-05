const testWrapper = document.querySelector(".test-wrapper");
const testArea = document.querySelector("#test-area");
const originText = document.querySelector("#origin-text p");
const resetButton = document.querySelector("#reset");
const theTimer = document.querySelector(".timer");

var texts = ["Adam is cool", 
"The world is big", 
"We got cats in here!", 
"Pardon me?",
"Are you coming with me?",
"Believe me.",
"I am crazy about her.",
"What terrible weather!",
"You’re lying.",
"Absolutely not.", ]

window.onload = function () {
    setRandomText()
}

function setRandomText(){
    var rnd =  Math.floor((Math.random() * texts.length) )
    originText.innerHTML = texts[rnd]    
}

var time = 0
var interval
var update = function () {
    time++
    setime()
}

function pad(n, width, z) {
    z = z || '0';
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
}

function setime() {
    var mils = Math.floor(time * 10)
    var secs = Math.floor(mils / 1000)
    var mins = Math.floor(secs / 60)

    mils = mils / 10 % 100
    secs = secs % 60

    theTimer.innerHTML = pad(mins, 2) + ":" + pad(secs, 2) + ":" + pad(mils, 2)
}


testArea.addEventListener("keydown", function () {
    if (time === 0) {
        console.log("start!")
        interval = window.setInterval(update, 10)
    }
})

testArea.addEventListener("keyup", function () {
    if (originText.innerHTML === testArea.value) {
        clearInterval(interval)
    }

})

resetButton.addEventListener("click", function (event) {
    event.preventDefault()
    time = 0
    testArea.value = ""
    clearInterval(interval)
    setime()
    setRandomText()

})