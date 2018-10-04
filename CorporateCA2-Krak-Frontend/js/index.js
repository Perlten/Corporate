var search = document.getElementById("search")

//change this to PROD
var URLAPI = "http://localhost:8084/CorporateCA2-Krak/api/"

var URLPERS = URLAPI + "person/"
var URLCOMP = URLAPI + "company/"

var results = document.getElementById("results")

var searchlogo = document.getElementById("searchlogo")

var infoEntities = []

var startTime = 0

var placeholder = "Søg firma, person, telefon, hobby, postnummer"
var chars = placeholder.split("")

var timer

var paddingTop = "4.3%"

function loadChar() {
    search.placeholder += chars.shift()
    if (chars.length < 1) {
        clearInterval(timer)
    }
}

this.onload = function () {
    timer = setInterval(loadChar, 40)
}

function personConverter(data) {
    //get a picture id
    var picid = (data.id % 2) + 1

    var entity = {
        id: data.id,
        name: data.firstname + " " + data.lastname,
        email: data.email,
        phones: data.phones,
        img: "pics/man" + picid + ".png",
        addresses: data.addresses,
        hobbies: data.hobbies
    }
    return entity
}

function companyConverter(data) {
    var entity = {
        id: data.id,
        name: data.name,
        email: data.email,
        phones: data.phoneList,
        img: "pics/reunion.png",
        addresses: data.addressList,
        cvr: data.cvr
    }
    return entity
}

function show(id){
    console.log("showing " + id)
    var info = document.getElementById("info_" + id)
    var h4 = document.getElementById("h4_" + id)
    if(info.style.display == "none"){
        info.style.display = "block"
        h4.style.paddingTop = "0px"
    } else{
        info.style.display = "none" 
        h4.style.paddingTop = paddingTop
    }
}

function entityLoader(data, converter) {
    var e = converter(data)
    //if the id of the data is not already displayed
    if (!(infoEntities.indexOf(e.id) > -1)) {
        //counting up
        var count = document.getElementById("count")
        count.innerHTML = parseInt(count.innerHTML) + 1

        //setting time
        var currentTime = new Date().getTime()
        var deltaTime = (currentTime - startTime) / 1000
        document.getElementById("seconds").innerHTML = deltaTime

        //setting html
        var html = '<div onclick="show(' + e.id + ')" class="media border p-3" style="background-color: white">'
        html += '<img src="' + e.img + '" alt="type" class="mr-3 mt-3 rounded-circle" style="width:60px;">'
        html += '<div class="media-body">'
        
        html += "<h4 id='h4_" + e.id + "' style='padding-top: " + paddingTop + "'>" + e.name + " <small><i>" + e.email + "</i></small></h4>"
        html += "<div style='display: none' id='info_" + e.id + "'>"
        if (e.cvr) {
            html += "<i>CVR: " + e.cvr + "</i><br>"
        }
        
        for (address of e.addresses) {
            html += '<p href="#" data-toggle="tooltip" title="' + address.additionalInfo + '">'
            html += address.street + "<br>"
            html += address.zip + ", " + address.city
            html += '</p>'
        }
        
        
        for (phone of e.phones) {
            html += "<b>Tlf: </b>" + "<a href='tel:+45" + phone.number + "'>" + phone.number + "</a> - " + phone.description + "<br>"
        }
        
        if (e.hobbies) {
            if(e.hobbies.length > 0){
                html += "<b>Hobbies: <b>"
                for (hobby of e.hobbies) {
                    html += '<span class="badge badge-secondary">' + hobby.name + '</span> '
                }
            }
        }
        html += "</div>"



        html += '</div>'
        html += "</div><br>"

        results.innerHTML += html

        //adding the id to the displayed list
        infoEntities.push(e.id)
    }
}

function isArray(what) {
    return Object.prototype.toString.call(what) === '[object Array]';
}

function listLoader(data, converter) {
    if (isArray(data)) {
        for (element of data) {
            entityLoader(element, converter)
        }
    } else {
        entityLoader(data, converter)
    }
}

function searchRequest(event) {
    event.preventDefault()

    //reset time
    startTime = new Date().getTime()
    document.getElementById("seconds").innerHTML = 0

    //reset count
    var count = document.getElementById("count")
    count.innerHTML = 0

    //showing found
    var found = document.getElementById("found")
    found.style.display = "block"

    //clearing results
    results.innerHTML = ""
    infoEntities = []

    //getting value
    var value = document.getElementById("search").value

    //if is a number
    if (value.length > 0) {
        if (!isNaN(value)) {
            if (value.length == 8) {
                //make rest call on person phone numbers
                REST(URLPERS + "phone/" + value, listLoader, personConverter)

                //company
                REST(URLCOMP + "phone/" + value, listLoader, companyConverter)
                REST(URLCOMP + "cvr/" + value, listLoader, companyConverter)
            } else if (value.length == 4) {
                REST(URLPERS + "zip/" + value, listLoader, personConverter)

            }
            REST(URLPERS + value, listLoader, personConverter)
            REST(URLCOMP + value, listLoader, companyConverter)
        } else {
            //person
            REST(URLPERS + "hobby/" + value, listLoader, personConverter)
            REST(URLPERS + "email/" + value, listLoader, personConverter)
            var names = value.split(" ")
            for (name of names) {
                REST(URLPERS + "firstname/" + name, listLoader, personConverter)
                REST(URLPERS + "lastname/" + name, listLoader, personConverter)
            }

            //company
            REST(URLCOMP + "name/" + value, listLoader, companyConverter)
            REST(URLCOMP + "email/" + value, listLoader, companyConverter)

        }
    } else {
        found.style.display = "none"
    }
}

search.addEventListener("keyup", searchRequest)


searchlogo.addEventListener("click", searchRequest)

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