
function geSselectedPhoneNumber() {
    return document.getElementById("selected-phone-number").textContent;
}


$(document).ready(function () {
    var selectedPhoneNumber = geSselectedPhoneNumber();

    $("#sms").click(function () {
        doCreateEvent(selectedPhoneNumber, "sms");
    });

    $("#mms").click(function () {
        doCreateEvent(selectedPhoneNumber, "mms");
    });

    $("#showEventsButton").click(function () {
        doShowEvents(selectedPhoneNumber, 0, 10);
    });

    $("#hideEventsButton").click(function () {
        $("#showEventsPanelBody").html("");
    });

    doUpdateAllEventsCount(selectedPhoneNumber);
});


function doCreateEvent(customerPhoneNumber, serviceName) {
    $.ajax({
        type: "GET",
        // contentType: "application/json",
        url: "/all/events/" + customerPhoneNumber + "/" + serviceName + "/createAjax",
        // headers: headers,
        // dataType: 'json',
        timeout: 100000,

        success: function () {
            doUpdateEventCount(customerPhoneNumber, serviceName);
        },

        error: function (e) {
            console.log("ERROR: ", e);
        },

        done: function (e) {
            console.log("DONE");
        }
    });
}


function doUpdateEventCount(customerPhoneNumber, serviceName) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/all/events/" + customerPhoneNumber + "/" + serviceName,
        // headers: headers,
        dataType: 'json',
        timeout: 100000,

        success: function (data, httpStatus) {
            if (Object.keys(data).length != null) {
                $("#" + serviceName + "EventCounter").html("");
                $("#" + serviceName + "EventCounter").append(Object.keys(data).length);
            }
        },

        error: function (e) {
            console.log("ERROR: ", e);
        },

        done: function (e) {
            console.log("DONE");
        }
    });
}


function doShowEvents(customerPhoneNumber, page, size) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/all/events/" + customerPhoneNumber + "?page=" + page + "&size=" + size,
        // headers: headers,
        dataType: 'json',
        timeout: 100000,

        success: function (data, httpStatus) {
            if (Object.keys(data).length != null) {
                $("#showEventsPanelBody").html("");
                // $("#showEventsPanelBody").append(Object.keys(data).length + "<br>");
                doFillShowEventsPanelBody(data);
            }
        },

        error: function (e) {
            console.log("ERROR: ", e);
        },

        done: function (e) {
            console.log("DONE");
        }
    });
}


function doFillShowEventsPanelBody(data) {
    var tableElement = document.createElement("table");
    var theadElement = document.createElement("thead");
    var trElement = document.createElement("tr");
    var thElement1 = document.createElement("th");
    var thElement2 = document.createElement("th");
    var thElement3 = document.createElement("th");
    var thElement4 = document.createElement("th");
    var tbodyElement = document.createElement("tbody");

    tableElement.className = "table table-condensed table-striped";

    thElement1.appendChild(document.createTextNode("serviceName"));
    thElement2.appendChild(document.createTextNode("startDate"));
    thElement3.appendChild(document.createTextNode("duration"));
    thElement4.appendChild(document.createTextNode("cost"));

    trElement.appendChild(thElement1);
    trElement.appendChild(thElement2);
    trElement.appendChild(thElement3);
    trElement.appendChild(thElement4);

    theadElement.appendChild(trElement);
    tableElement.appendChild(theadElement);

    $.each(data["content"], function (index) {
        var trElement = document.createElement("tr");
        var tdElement1 = document.createElement("td");
        var tdElement2 = document.createElement("td");
        var tdElement3 = document.createElement("td");
        var tdElement4 = document.createElement("td");

        tdElement1.appendChild(document.createTextNode(data["content"][index]["serviceName"]));
        tdElement2.appendChild(document.createTextNode(new Date(data["content"][index]["startDate"]).toUTCString()));
        tdElement3.appendChild(document.createTextNode(data["content"][index]["duration"]));
        tdElement4.appendChild(document.createTextNode(data["content"][index]["cost"]));

        trElement.appendChild(tdElement1);
        trElement.appendChild(tdElement2);
        trElement.appendChild(tdElement3);
        trElement.appendChild(tdElement4);

        tbodyElement.appendChild(trElement);
    });

    tableElement.appendChild(tbodyElement);
    $("#showEventsPanelBody").append(tableElement);


    $("#showEventsPanelBody").append("<br>");
    var pageNumber = data['number'];
    var totalPages = data['totalPages'];


    var first = "<button type='button' class='btn btn-success' onclick='replace(0," + data['size'] + ")'" + ">First</button>";
    $("#showEventsPanelBody").append(first);

    if (pageNumber != 0) {
        var prev = "<button type='button' class='btn btn-primary' onclick='replace(" + (pageNumber - 1) + ", " + data['size'] + ")'" + ">Prev</button>";
        $("#showEventsPanelBody").append(prev);
    }
    if (pageNumber < totalPages -1) {
        var next = "<button type='button' class='btn btn-primary' onclick='replace("
            + (pageNumber + 1) + ", " + data['size'] + ")'" + ">Next</button>";

        $("#showEventsPanelBody").append(next);
    }
    var last = "<button type='button' class='btn btn-success' onclick='replace(" + (totalPages - 1) + ", " + data['size'] + ")'" + ">Last</button>";
    $("#showEventsPanelBody").append(last);
}


function replace(page, size) {
    var selectedPhoneNumber = geSselectedPhoneNumber();
    // console.log("page" + page);
    // console.log("size" + size);
    doShowEvents(selectedPhoneNumber, page, size);
    return false;
}


function doUpdateAllEventsCount(customerPhoneNumber) {
    doUpdateEventCount(customerPhoneNumber, "sms");
    doUpdateEventCount(customerPhoneNumber, "mms");
}

