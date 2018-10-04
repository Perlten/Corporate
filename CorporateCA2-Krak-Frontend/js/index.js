var search = document.getElementById("search")

//change this to PROD
var URLAPI = "http://localhost:8084/api/"

var URLPERS = URLAPI + "person/"
var URLCOMP = URLAPI + "company/"

var results = document.getElementById("results")

var infoEntities = []


function personConverter(data) {
    var rnd = Math.floor((Math.random() * 2) + 1)
    var entity = {
        id: data.id,
        name: data.firstname + " " + data.lastname,
        email: data.email,
        phones: data.phones,
        img: "pics/man" + rnd + ".png",
        addresses: data.addresses
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

function entityLoader(data, converter) {
    var e = converter(data)
    if(!(infoEntities.indexOf(e.id) > -1)){
        var count = document.getElementById("count")
        count.innerHTML = parseInt(count.innerHTML) + 1
        var html = '<div class="media border p-3" style="background-color: white">'
        html += '<img src="' + e.img + '" alt="type" class="mr-3 mt-3 rounded-circle" style="width:60px;">'
        html += '<div class="media-body">'
        html += "<h4>" + e.name + " <small><i>" + e.email + "</i></small></h4>"
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
            html += "<b>Tel: </b>" + "<a href='tel:+45" + phone.number + "'>" + phone.number + "</a> - " + phone.description + "<br>"
        }

        html += '</div>'
        html += "</div><br>"
        
        results.innerHTML += html
        
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



search.addEventListener("keyup", function (event) {
    event.preventDefault()
    results.innerHTML = ""
    var count = document.getElementById("count")
    count.innerHTML = 0
    var value = this.value
    console.log(value)
    var found = document.getElementById("found")
    found.style.display = "none"
    infoEntities = []

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
            var names = value.split(" ")
            for (name of names) {
                REST(URLPERS + "firstname/" + name, listLoader, personConverter)
                REST(URLPERS + "lastname/" + name, listLoader, personConverter)
            }

            //company
            REST(URLCOMP + "name/" + value, listLoader, companyConverter)
        }
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