var search = document.getElementById("search")

//change this to PROD
var URLAPI = "http://localhost:8084/api/"

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
    // console.log(data)
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
        cvr: data.cvr,
        employeecount: data.numEmployees
    }
    return entity
}

function show(id) {

    console.log("showing " + id)
    var info = document.getElementById("info_" + id)
    var h4 = document.getElementById("h4_" + id)
    if (info.style.display == "none") {
        info.style.display = "block"
        h4.style.paddingTop = "0px"
    } else {
        info.style.display = "none"
        h4.style.paddingTop = paddingTop
    }

}


function edit(id) {
    var nonWantedObjs = ["id", "personIds", "cityId"]
    console.log("edit: " + id)
    var content = document.getElementById("div_" + id)

    var allEdit = document.getElementsByClassName("edit")
    var contents = document.getElementsByClassName("contents")
    for (elm of allEdit) {
        elm.style.display = "none"
    }
    for (elm of contents) {
        elm.style.display = "block"
    }

    var edit = document.getElementById("edit_" + id)
    content.style.display = "none"
    edit.style.display = "block"

    edit.innerHTML += "<b>GENERAL INFORMATION</b><br>"

    function pasteEnt(data) {
        for (obj in data) {
            var isNotWanted = !(nonWantedObjs.indexOf(obj) > -1)
            if (typeof data[obj] == "object") {
                if (isNotWanted) {
                    var lastchar = obj.charAt(obj.length -1)
                    var substr = obj.substring(0,obj.length - 1) + lastchar
                    edit.innerHTML += "<br><b>" + substr.toLocaleUpperCase() + "#</b><br>"
                    pasteEnt(data[obj])
                }

            } else {
                if (isNotWanted) {
                    var type = typeof x
                    if(!isNaN(data[obj])){
                        type = "number"
                    }

                    edit.innerHTML += obj.toUpperCase() + ":<input type='" + type + "' class='form-control editfield' value='" + data[obj] + "' id='edit_" + obj + "_" + id + "' >"
                }
            }
        }
    }

    REST2(URLPERS + id, pasteEnt)
    REST2(URLCOMP + id, pasteEnt)
}

function direct(cname) {
    var array1 = cname.split(":")
    var array2 = array1[1].split("_")
    var operation = array2[0]
    var value = array2[1]
    console.log(value)

    switch (operation) {
        case "ENT":
            show(value)
            break;
        case "EDT":
            edit(value)
            break;
    }
}

results.addEventListener("click", function (event) {
    function hasClassName(target) {
        var cname = "" + target.className
        if (cname === "") {
            console.log("The thing was empty")
            hasClassName(target.parentElement)
        } else {
            console.log(cname.indexOf(":") > -1)
            if (cname.indexOf(":") > -1) {
                direct(cname)
            } else {
                hasClassName(target.parentElement)
            }
        }
    }
    hasClassName(event.target)
})

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
        var html = '<div class="media border p-3 :ENT_' + e.id + '" style="background-color: white">'
        html += '<img src="' + e.img + '" alt="type" class="mr-3 mt-3 rounded-circle" style="width:60px;">'
        html += '<div class="media-body contents" id="div_' + e.id + '">'

        html += "<h4 id='h4_" + e.id + "' style='padding-top: " + paddingTop + "'>" + e.name + " <small><i>" + e.email + "</i></small></h4>"
        html += "<div style='display: none' id='info_" + e.id + "'>"
        if (e.cvr) {
            html += "<i>CVR: " + e.cvr + "</i><br>"
        }

        if (e.addresses) {
            for (address of e.addresses) {
                html += '<i><p href="#" data-toggle="tooltip" title="' + address.additionalInfo + '">'
                html += address.street + "<br>"
                html += address.zip + ", " + address.city
                html += '</p></i>'
            }
        }

        if (e.phones) {
            html += "<b>Tlf: </b>"
            for (phone of e.phones) {
                html += "<a data-toggle='tooltip' title='" + phone.description + "' href='tel:+45" + phone.number + "'>" + phone.number + "</a> "
            }
        }


        if (e.hobbies) {
            html += "<br>"
            if (e.hobbies.length > 0) {
                console.log(e.hobbies)
                html += "<b>Hobbies: <b>"
                for (hobby of e.hobbies) {
                    html += '<a href="#" data-toggle="tooltip" title="' + hobby.description + '">'
                    html += '<span class="badge badge-secondary">' + hobby.name + '</span>'
                    html += '</a>'
                }
            }
        }
        html += "<img class=':EDT_" + e.id + "' src='pics/edit.png'style='width: 25px; float: right'>"

        html += "</div>"

        html += '</div>'
        html += '<div class="media-body edit" style="display: none" id="edit_' + e.id + '">'
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
    var value = "" + document.getElementById("search").value

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

            if (value.charAt(0) === ">") {
                console.log("Over!")
                var array = value.split("")
                array.shift()
                var overValue = array.join("")
                console.log(overValue)
                if (overValue.length > 0) {
                    if (!isNaN(overValue)) {
                        REST(URLCOMP + "count/" + overValue, listLoader, companyConverter)
                    }
                }
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

function REST2(URL, callback) {
    fetch(URL)
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
            fullError: res.json()
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