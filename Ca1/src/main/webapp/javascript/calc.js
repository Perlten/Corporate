var buttons = document.getElementsByClassName("t1")
var reset = false;

for (var button of buttons) {
    button.addEventListener("click", function (event) {
        var symbol = this.innerText

        coordinator(symbol)
    })
}

document.getElementById("calculate").addEventListener("click", function (event) {
    var display = document.getElementById("display")
    var innerText = display.innerText
    event.stopPropagation()
    if (operator != null) {
        if (innerText.charAt(innerText.length - 1) !== ".") {
            var result = eval(display.innerText)
            display.innerText = result
            operator = null
            dotAfter = false
            console.log(result % 1 == 0)
            if (result % 1 == 0) {
                console.log("reached")
                dotBefore = false
            } else {
                dotBefore = true
            }
            reset = true
        }
    }

})


var symbols = ["/", "*", "-", "+"]
var operator = null;
var dotBefore = false;
var dotAfter = false

function coordinator(symbol) {
    var display = document.getElementById("display")
    var innerText = display.innerText

    if (reset) {
        display.innerText = ""
        reset = false;
    }

    console.log("operator: " + operator)
    console.log(".before: " + dotBefore)
    console.log(".after: " + dotAfter)

    if (symbol === "=") {
        //do nothing
    } else {
        var append = true;
        if (symbolcheck(symbol)) {
            if (display.innerText.length == 0) {
                append = false
            } else if (innerText.charAt(innerText.length - 1) === ".") {
                append = false


            } else {
                if (operator == null) {
                    if (symbolcheck(symbol)) {
                        operator = symbol;
                    }

                } else {
                    if (symbolcheck(symbol)) {
                        append = false;
                    }
                }
            }
        }

        if (symbol == ".") {
            if (operator == null) {
                if (dotBefore) {
                    append = false;
                } else {
                    if (innerText.length == 0) {
                        display.innerText += "0"
                        dotBefore = true
                    } else {
                        dotBefore = true
                    }
                }
            } else {

                if (dotAfter) {
                    append = false;
                } else {
                    if (innerText.charAt(innerText.length - 1) === operator) {
                        display.innerText += "0"
                        dotAfter = true
                    } else {
                        dotAfter = true
                    }
                }
            }
        }

        if (symbol === "0") {
            var beforeIsZero = false
            try {
                beforeIsZero = innerText[innerText.length - 1] === "0"
            } catch (error) {
                console.log(error)
            }

            if (beforeIsZero) {
                if (operator == null) {
                    if (!dotBefore) {
                        append = false;
                    }
                } else {
                    if (!dotAfter) {
                        append = false;
                    }
                }
            }
        }

        console.log(append)
        if (symbol === "-") {
            var otherSyms = ["/","*","+"]

            var last = innerText.charAt(innerText.length - 1)
            if (innerText.length == 0) {
                append = true
            } else if(last !== "-") {
                append = true
            } else {
                append = false
            }
            console.log("op after: " + operator)

        }


        if (append) {

            display.innerText += symbol

        }

    }

}

function symbolcheck(symbol) {
    return symbols.includes(symbol)
}

