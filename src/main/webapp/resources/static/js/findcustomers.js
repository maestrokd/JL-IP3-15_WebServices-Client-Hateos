function loadCustomers() {
    // CSRF protection headers
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;
    headers["Authorization"]="Basic " + btoa("admin" + ":" + "admin")

    var completeField = document.getElementById("complete-field");
    var completeTable = document.getElementById("completeTable");
    var completeDiv = document.getElementById("completeDiv");

    console.log("INFO search: ", encodeURI(completeField.value));

    clearAutoCompleteDiv();

    var port = (window.location.port) ? (":" + window.location.port) : ("");
    console.log("INFO port: " + port);
    console.log("INFO url: " + "//" + window.location.hostname
        + port
        + "/protected/customers/findajax?search=" + encodeURI(completeField.value)
    );


    $.ajax({

        type: "GET",
        contentType: "application/json",
        url: "//"
            + window.location.hostname
            + port
            + "/protected/customers/findajax?search=" + encodeURI(completeField.value),
        headers: headers,
        dataType: 'json',
        timeout: 100000,

        success: function (data) {
            console.log("INFO data2: ", Object.keys(data).length);
            if (Object.keys(data).length != null) {
                // fillTextArea(data);
                fillTableArea(data);
            }
        },

        error: function (e) {
            console.log("ERROR: ", e);
        },

        done: function (e) {
            console.log("DONE");
        }
    });


    function clearAutoCompleteDiv() {

        if (completeDiv.hasChildNodes()) {
            $("#completeDiv").html("");
        }

        if (completeTable.hasChildNodes()) {
            $("#completeTable").html("");
        }
    }

    function fillTextArea(data) {
        var textAreaElement = document.createElement("textarea");
        textAreaElement.className = "form-control";
        textAreaElement.setAttribute("rows", Object.keys(data).length + 1);
        for (var i = 0; i < Object.keys(data).length; i++) {
            textAreaElement.appendChild(document.createTextNode(data[i]["phoneNumber"] + "\n"))
        }
        $("#completeDiv").append(textAreaElement);
    }


    function fillTableArea(data) {
        for (var i = 0; i < Object.keys(data).length; i++) {
            var trElement = document.createElement("tr");
            var tdElement1 = document.createElement("td");
            var tdElement2 = document.createElement("td");
            var tdElement3 = document.createElement("td");
            var tdElement4 = document.createElement("td");
            var buttonElement1 = document.createElement("button");

            buttonElement1.className = "btn btn-info";
            buttonElement1.setAttribute("onclick", "location.href='/all/customers/" + data[i]["phoneNumber"] + "'");
            buttonElement1.appendChild(document.createTextNode("Info"));

            tdElement1.appendChild(document.createTextNode(data[i]["phoneNumber"]));
            tdElement2.appendChild(document.createTextNode(data[i]["customerStatusName"]));
            tdElement3.appendChild(document.createTextNode(data[i]["balance"]));
            tdElement4.appendChild(buttonElement1);

            trElement.appendChild(tdElement1);
            trElement.appendChild(tdElement2);
            trElement.appendChild(tdElement3);
            trElement.appendChild(tdElement4);
            $("#completeTable").append(trElement);
        }
    }

}

