var search = document.getElementById("search")

//change this to PROD

var URLAPI = "http://localhost:8084/api/"


var URLPERS = URLAPI + "person/"
var URLCOMP = URLAPI + "company/"
var URLHOBBY = URLAPI + "hobby/"

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

    document.getElementById("submitPost").style.display = "none"
    document.getElementById("selectPost").style.display = "none"
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

    edit.innerHTML += "<img class=':CLS_" + id + "' src='pics/close.png'style='width: 25px; float: right'>"
    edit.innerHTML += "<img class=':DEL_" + id + "' src='pics/delete.png'style='width: 25px; float: right'>"
    edit.innerHTML += "<img class=':SAVE_" + id + "' src='pics/save.png'style='width: 25px; float: right'>"

    edit.innerHTML += "<b>GENERAL INFORMATION</b><br>"

    function pasteEnt(data) {
        for (obj in data) {
            var isNotWanted = !(nonWantedObjs.indexOf(obj) > -1)
            if (typeof data[obj] == "object") {
                if (isNotWanted) {
                    var lastchar = obj.charAt(obj.length - 1)
                    var substr = obj.substring(0, obj.length - 1) + lastchar
                    edit.innerHTML += "<br><b>" + substr.toLocaleUpperCase() + "#</b><br>"
                    pasteEnt(data[obj])
                }

            } else {
                if (isNotWanted) {
                    var type = typeof x
                    if (!isNaN(data[obj])) {
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



function deleteEntity(id) {
    var opts = {
        method: "DELETE"
    }

    var element = document.getElementById("ENT" + id)
    element.innerHTML = "<img src='pics/loader.gif' style='margin: auto; display:block; max-width: 100%'>"

    function whenDeleted() {
        element.parentNode.removeChild(element)
    }
    
    REST2(URLPERS + id, whenDeleted, opts)
    REST2(URLCOMP + id, whenDeleted, opts)
}

function closeEdit(id){
    var content = document.getElementById("div_" + id)
    var edit = document.getElementById("edit_" + id)
    content.style.display = "block"
    edit.style.display = "none"
}

function direct(cname) {
    var array1 = cname.split(":")
    var array2 = array1[1].split("_")
    var operation = array2[0]
    var value = array2[1]
    console.log(operation + " of " + value)

    switch (operation) {
        case "ENT":
            show(value)
            break;
        case "EDT":
            edit(value)
            break;
        case "DEL":
            deleteEntity(value)
            break;
        case "CLS":
            closeEdit(value)
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
        var html = '<div id="ENT' + e.id + '" class="media border p-3 :ENT_' + e.id + '" style="background-color: white">'
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
    if (infoEntities.length < 1) {
        results.innerHTML = ""
    }
    if (isArray(data)) {
        for (element of data) {
            entityLoader(element, converter)
        }
    } else {
        entityLoader(data, converter)
    }
}

function searchRequest() {


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
    infoEntities = []

    //setting loader
    results.innerHTML = "<img src='pics/loader1.gif' style='margin: auto; display:block; max-width: 30%'>"

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
        results.innerHTML = ""
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

function REST2(URL, callback, options) {
    fetch(URL, options)
        .then(errorCheck)
        .then(data => callback(data))
        .catch(errorHandler)
}

function errorCheck(res) {
    if (res.ok) {
        return res.json()
    } else {
       return Promise.reject({
            httpError: res.status,
            fullError: res.json()
        })
    }
}

function errorHandler(err) {
    if (err.httpError) {
        err.fullError.then(errjson => {
            document.getElementById("error").innerText = "Error: " + errjson.code + " - " + errjson.message
            console.log(errjson)
        })
    } else {
        console.log("Network Error " + err)
    }
}


document.addEventListener('modal', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems, options);
  });


//Used to display correctly
var showPostInfo = false
document.getElementById("showPostInfo").addEventListener("click", function () {
    if (!showPostInfo) {
        postOptionVal = postOption.options[postOption.selectedIndex].value;
        if (postOptionVal == "person") {
            document.getElementById("postForm").innerHTML = namePost()
        }
        if (postOptionVal == "company") {
            document.getElementById("postForm").innerHTML = companyPost()
        } if (postOptionVal == "hobby") {
            document.getElementById("postForm").innerHTML = hobbyPost()
        }
        document.getElementById("submitPost").style.display = "block"
        document.getElementById("selectPost").style.display = "block"
        showPostInfo = true
    } else {
        document.getElementById("submitPost").style.display = "none"
        document.getElementById("selectPost").style.display = "none"
        document.getElementById("postForm").innerHTML = ""
        showPostInfo = false
    }
})

//Select options updating
var postOption = document.getElementById("selectPost");
postOption.addEventListener("change", function () {
    postOptionVal = postOption.options[postOption.selectedIndex].value;
    if (postOptionVal == "person") {
        document.getElementById("postForm").innerHTML = namePost()
    }
    if (postOptionVal == "company") {
        document.getElementById("postForm").innerHTML = companyPost()
    } if (postOptionVal == "hobby") {
        document.getElementById("postForm").innerHTML = hobbyPost()
    }

})


//Submit the post
document.getElementById("submitPost").addEventListener("click", function () {
    if (postOptionVal == "person") {
        var fname = document.getElementById("fname").value
        var lname = document.getElementById("lname").value
        var emailToUse = document.getElementById("email").value
        if (fname != "" && lname != "" && email != "") {

            var person = {
                firstname: fname,
                lastname: lname,
                email: emailToUse
            }

            for (let index = 0; index < 1; index++) {
                REST(URLPERS, function (data) { console.log(data) }, function () { }, makeOptions("POST", person))
            }
        } else {
            //GIVE ERROR!!!
            console.log("error, give inputs")
        }

    }
    if (postOptionVal == "company") {
        var cname = document.getElementById("cname").value
        var desc = document.getElementById("desc").value
        var cvr = document.getElementById("cvr").value
        var numOfEmp = document.getElementById("empNumber").value
        var marketValue = document.getElementById("cvr").value
        var emailToUse = document.getElementById("email").value
        if (cname != "" && desc != "" && cvr != "" && numOfEmp != "" && marketValue != "" && email != "") {

            var company = {
                cname: name,
                description: desc,
                cvr: cvr,
                numEmployees: numOfEmp,
                marketValue: marketValue,
                email: emailToUse
            }

            REST(URLCOMP, function (data) { console.log(data) }, function () { }, makeOptions("POST", company))

        } else {
            //GIVE ERROR!!!
            console.log("error, give inputs")
        }

    } if (postOptionVal == "hobby") {
        var hname = document.getElementById("hname").value
        var desc = document.getElementById("desc").value
        if (hname != "" && desc != "") {

            var hobby = {
                name: hname,
                description: desc,
                personIds: [ 1, 41]
            }




            REST(URLHOBBY, function (data) { console.log(data) }, function () { }, makeOptions("POST", hobby))

        } else {
            //GIVE ERROR!!!
            console.log("error, give inputs")
        }
    }


})


//post name inner html
function namePost() {
    var res = ""
    res += "<br>First name:<br>"
    res += "<input id=\"fname\" class='form-control' type=\"text\"><br>"
    res += "Last name:<br>"
    res += "<input id=\"lname\" class='form-control' type=\"text\"><br>"
    res += "Email:<br>"
    res += "<input id=\"email\" class='form-control' type=\"text\">"
    return res;
}

//post hobbyinnerhtml
function hobbyPost() {
    var res = ""
    res += "<br>Hobby name:<br>"
    res += "<input id=\"hname\" class='form-control' type=\"text\"><br>"
    res += "Description:<br>"
    res += "<input id=\"desc\" class='form-control' type=\"text\"><br>"
    return res;
}

//post company inner html
function companyPost() {
    var res = ""
    res += "<br>Company name:<br>"
    res += "<input id=\"cname\" class='form-control' type=\"text\"><br>"
    res += "Description name:<br>"
    res += "<input id=\"desc\" class='form-control' type=\"text\"><br>"
    res += "CVR:<br>"
    res += "<input id=\"cvr\" class='form-control' type=\"number\"><br>"
    res += "Number of Emp.:<br>"
    res += "<input id=\"empNumber\" class='form-control' type=\"number\"><br>"
    res += "Market Value:<br>"
    res += "<input id=\"marketValue\" class='form-control' type=\"number\"><br>"
    res += "Email:<br>"
    res += "<input id=\"email\" class='form-control' type=\"text\"><br>"
    return res;
}


//smart make options func
function makeOptions(method, body) {
    var opts = {
        method: method,
        headers: {
            "Content-type": "application/json"
        }
    }
    if (body) {
        var Jsonbody = JSON.stringify(body)
        console.log(Jsonbody)
        opts.body = Jsonbody
    }
    return opts;
}


