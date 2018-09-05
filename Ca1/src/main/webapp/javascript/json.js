var number = Number(document.getElementById("number").innerText)
var submitButton = document.getElementById("submit")
submitButton.addEventListener("click", function(event){
    event.preventDefault()
    console.log("Hello")
})