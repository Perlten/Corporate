var getUser = document.getElementById("getUser")
var getAll = document.getElementById("getAll")

getUser.addEventListener("click", function (event) {
    event.preventDefault()
    var number = Number(document.getElementById("number").value)
    console.log(number)
    var url = "https://jsonplaceholder.typicode.com/users/" + number
    console.log(url)
    fetch(url)
        .then(function (response) {
            if (response.status !== 200) {
                console.error("Problem occured... Status Code: " + response.status)
                return
            }

            response.json().then(function (data) {
                console.log(data.name)
                console.log(data)
                var dataField = document.getElementById("data")
                dataField.innerHTML = "Name: " + data.name +
                    "<br>Phone: " + data.phone +
                    "<br><br><b>Address</b><br>Street: " +
                    data.address.street +
                    "<br>City: " + data.address.city +
                    "<br>Zip: " + data.address.zip + 
                    "<br>Geo (lat,lng): " + data.address.geo.lat + "," + data.address.geo.lng



            })


        })
        .catch(function (error) {
            console.error("Error: " + error)
        })
})


getAll.addEventListener("click", function(event){
    event.preventDefault()
    fetch("https://jsonplaceholder.typicode.com/users")
    .then(function(response){
        if(response.status !== 200){
            console.error("Problem occured... Status Code: " + response.status)
                return
        }

        response.json().then(function(data){
            var html = ""
            for(var person of data){
                html += "<tr>"
                html += "<td>" + person.name + "</td>"
                html += "<td>" + person.phone + "</td>"
                html += "</tr>"
            }
            var tbody = document.getElementById("tbody")
            tbody.innerHTML = html
        })
    })
})
