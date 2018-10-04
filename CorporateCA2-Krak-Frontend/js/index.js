var search = document.getElementById("search")

//change this to PROD
var URLPATH = "http://localhost:8084/"
var URLAPI = URLPATH + "api/"

var URLPERS = URLAPI + "person/"

function handleInfoEntity(data){
    console.log(data)
}

search.addEventListener("keyup", function(){
    var value = this.value
    console.log(value)

    //if is a number
    if(!isNaN(value) && value.length == 8){
        //make rest call on phone numbers
        REST(URLPERS + "phone/" + value,handleInfoEntity)
    }
    

})






function REST(URL, callback, options) {
    fetch(URL, options)
        .then(errorCheck)
        .then(data => callback(data))
        .catch(errorHandler)
}

function errorCheck(res) {
    if (res.ok) {
        return res.json()
    } else {
        Promise.reject({
            httpError: res.status,
            fullError: res.join()
        })
    }
}

function errorHandler(err) {
    function errordisplay(err) {
        var error = document.getElementById("error")
        error.innerText = err
    }
    if (err.httpError) {
        err.fullError.then(json => {
            errordisplay(json.httpError)
        })
        console.log("error")
    } else {
        console.log("Network Error " + err)
        errordisplay(err)
    }
}