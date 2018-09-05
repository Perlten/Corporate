var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];

function showList(list, div) {
    var div = document.getElementById(div);
    var html = ""
    for (var person of list) {
        html += person + "<br>"
    }
    div.innerHTML = html
}

function showAllLists() {
    showList(boys, "boys")
    showList(girls, "girls")
    showList(boys.concat(girls), "all")
}
showAllLists()


//buttons
var addboy = document.getElementById("addboy")
var addgirl = document.getElementById("addgirl")
var removeboy = document.getElementById("removeboy")
var removegirl = document.getElementById("removegirl")
var reverse = document.getElementById("reverse")
var sort = document.getElementById("sort")
//textfields
var newboy = document.getElementById("newboy")
var newgirl = document.getElementById("newgirl")
//radiobuttons
var directions = document.getElementsByName("direction")

addboy.addEventListener("click", function (event) {
    event.preventDefault()
    var value = newboy.value
    boys.push(value)
    showAllLists()
})

addgirl.addEventListener("click", function (event) {
    event.preventDefault()
    var value = newgirl.value
    girls.push(value)
    showAllLists()
})


function getDirection() {
    var direction
    for (var button of directions) {
        if (button.checked) {
            direction = button.id
        }
    }
    return direction
}

function remove(list) {
    if (getDirection() === "first") {
        list.shift()
    } else {
        list.pop()
    }
    showAllLists()
}


removeboy.addEventListener("click", function (event) {
    event.preventDefault()
    remove(boys)
})

removegirl.addEventListener("click", function(event){
    event.preventDefault()
    remove(girls)
})

function concatLists(){
    return boys.concat(girls)
}

reverse.addEventListener("click", function(event){
    event.preventDefault()
    var all = concatLists()
    all.reverse()
    showList(all,"all")
})

var sorted = false

sort.addEventListener("click", function(event){
    event.preventDefault()
    var all = concatLists()
    all.sort(comparator)
    if(sorted){
        all.reverse()
        sorted = false
    } else {
        sorted = true
    }
    showList(all,"all")
})

var comparator = function(a,b){
    return a.toLowerCase().localeCompare(b.toLowerCase())
}