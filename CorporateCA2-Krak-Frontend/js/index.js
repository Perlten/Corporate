var search = document.getElementById("search")

//change this to PROD
var URLAPI = "https://corporategroup.dk/Krak/api/"

var URLPERS = URLAPI + "person/"
var URLCOMP = URLAPI + "company/"

var results = document.getElementById("results")

function personConverter(data) {
    var entity = {
        name: data.firstname + " " + data.lastname,
        email: data.email,
        phones: data.phones,
        img: "pics/img_avatar3.png",
        addresses: data.addresses
    }
    return entity
}

function companyConverter(data){
    var entity ={
        name: data.name,
        email: data.email,
        phones: data.phoneList,
        img: "pics/img_avatar3.png",
        addresses: data.addressList
    }
    return entity
}

function entityLoader(data, converter) {
    var e = converter(data)
    var html = '<div class="media border p-3" style="background-color: white">'
    html += '<img src="' + e.img + '" alt="type" class="mr-3 mt-3 rounded-circle" style="width:60px;">'
    html += '<div class="media-body">'
    html += "<h4>" + e.name + " <small><i>" + e.email + "</i></small></h4>"

    for (address of e.addresses) {
        html += '<p>'
        html += address.street + "<br>"
        html += address.zip + ", " + address.city
        html += '</p>'
    }

    
    for(phone of e.phones){
        html += "<b>Tel:</b>" + "<a href='tel:+45" + phone.number +  "'>" + phone.number +  " - </a>" + phone.description + "<br>"
    }


    html += '</div>'
    html += "</div>"

    results.innerHTML += html

}

function isArray(what) {
    return Object.prototype.toString.call(what) === '[object Array]';
}

function listLoader(data, converter){
    if(isArray(data)){
        for(element of data){
            entityLoader(element, converter)
        }
    } else {
        entityLoader(data, converter)
    }
}

search.addEventListener("keyup", function (event) {
    event.preventDefault()
    results.innerHTML = ""
    var value = this.value
    console.log(value)

    //if is a number
    if (!isNaN(value) && value.length == 8) {
        //make rest call on person phone numbers
        REST(URLPERS + "phone/" + value, listLoader, personConverter)

        //company
        REST(URLCOMP + "phone/" + value, listLoader,companyConverter)
    }


})






function REST(URL, callback, converter, options) {
    fetch(URL, options)
        .then(errorCheck)
        .then(data => callback(data, converter))
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