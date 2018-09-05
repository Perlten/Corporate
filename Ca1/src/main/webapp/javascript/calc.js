var buttons = document.getElementsByClassName("t1")

for (var button of buttons) {
    button.addEventListener("click", function (event) {
        var symbol = this.innerText

        coordinator(symbol)
    })
}

document.getElementById("calculate").addEventListener("click", function (event) {
    event.stopPropagation()
    console.log("calculating")
    if (operator != null) {
        var display = document.getElementById("display")
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
    }

})

var symbols = ["/", "*", "-", "+"]
var operator = null;
var dotBefore = false;
var dotAfter = false

function coordinator(symbol) {
    console.log("operator: " + operator)
    console.log(".before: " + dotBefore)
    console.log(".after: " + dotAfter)
    var display = document.getElementById("display")

    if (symbol === "=") {
        //do nothing
    } else {
        var append = true;
        if (operator == null) {
            if (symbolcheck(symbol)) {
                operator = symbol;
            }

        } else {
            if (symbolcheck(symbol)) {
                append = false;
            }
        }

        if (symbol == ".") {
            var innerText = display.innerText
            if (operator == null) {
                if (dotBefore) {
                    append = false;
                } else {
                    if (innerText.length == 0) {
                        append = false
                    } else {
                        dotBefore = true
                    }
                }
            } else {

                if (dotAfter) {
                    append = false;
                } else {
                    if (innerText.charAt(innerText.length - 1) === operator) {
                        append = false
                    } else {
                        dotAfter = true
                    }
                }
            }
        }



        if (append) {

            display.innerText += symbol

        }

    }

}

function symbolcheck(symbol) {
    return symbols.indexOf(symbol) != -1
}