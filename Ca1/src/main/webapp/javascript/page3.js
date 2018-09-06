var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];
var all = [""];
window.onload = function () {

    all = boys.concat(girls);
    updateBoysList(boys);
    updateGirlList(girls);
    updateAllList(all);


}

function updateAllList(arr) {
    var allListDone = arr.map(function (x) { return "<li>" + x }).join("</li>");
    var all = document.getElementById("all");
    all.innerHTML = "<ul style=\"list-style-type:none\"> " + allListDone + "</ul> "
}
/*
function updateAllList(arrboys, arrgirls) {
    var boysDone = arrboys.map(function (x) {  return "<li>" + x }).join("</li>");
    var girlsDone = arrgirls.map(function (x) { return "<li>" + x }).join("</li>");
    var allList = boysDone.concat(girlsDone);
    var all = document.getElementById("all");
    all.innerHTML = "<ul style=\"list-style-type:none\"> " + allList + "</ul> "
}
*/

function updateBoysList(arr) {
    var boysDone = arr.map(function (x) { return "<li>" + x }).join("</li>");
    var boysList = document.getElementById("boys");
    boysList.innerHTML = "<ul style=\"list-style-type:none\"> " + boysDone + "</ul> "
}

function updateGirlList(arr) {
    var girlsDone = arr.map(function (x) { return "<li>" + x }).join("</li>");
    var girlsList = document.getElementById("girls");
    girlsList.innerHTML = "<ul style=\"list-style-type:none\"> " + girlsDone + "</ul> "
}

//Adding girl to list and updating list
var submitgirl = document.getElementById("addgirl");
submitgirl.addEventListener("click", function (event) {
    event.preventDefault();
    var nameOfGirl = document.getElementById("newgirl");
    girls.push(nameOfGirl.value);
    all.push(nameOfGirl.value)
    updateGirlList(girls);
    updateAllList(all);
})

//Adding boy to list and updating list

var submitboy = document.getElementById("addboy");
submitboy.addEventListener("click", function (event) {
    event.preventDefault();
    var nameOfBoy = document.getElementById("newboy");
    boys.push(nameOfBoy.value);
    all.push(nameOfBoy.value);
    updateBoysList(boys);
    updateAllList(all);
})

// remove boy from list
var removeboy = document.getElementById("removeboy")
removeboy.addEventListener("click", function (event) {
    event.preventDefault();
    if (document.getElementById("first").checked) {
        all.shift();
        boys.shift();
    } else if (document.getElementById("last").checked) {
        all.pop();
        boys.pop();
    }
    updateBoysList(boys);
    updateAllList(all);
})

//REMOVE GIRL
var removegirl = document.getElementById("removegirl")
removegirl.addEventListener("click", function (event) {
    event.preventDefault();
    if (document.getElementById("first").checked) {
        girls.shift();
        all.shift();
    } else if (document.getElementById("last").checked) {
        girls.pop();
        all.pop();
    }
    updateGirlList(girls);
    updateAllList(all);
})

//REVERSE ALL LIST
var reverseAll = document.getElementById("reverse");
reverseAll.addEventListener("click", function (event) {
    event.preventDefault();
    all.reverse();

    updateAllList(all);
})

var ascending = true;
var sortAll = document.getElementById("sort");
sortAll.addEventListener("click", function(event){
   if(ascending){
    all.sort(function(a, b) {
        var nameA = a.toUpperCase();
        var nameB = b.toUpperCase(); 
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
        // names must be equal
        return 0;
      });
      ascending = false
    }else{
        all.sort(function(a, b) {
            var nameA = b.toUpperCase(); 
            var nameB = a.toUpperCase(); 
            if (nameA < nameB) {
              return -1;
            }
            if (nameA > nameB) {
              return 1;
            }
            // names must be equal
            return 0;
          });
        ascending = true
    }
updateAllList(all);

})



