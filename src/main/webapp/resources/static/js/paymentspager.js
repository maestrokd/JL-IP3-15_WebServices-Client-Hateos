function loadPayments(page, size) {
    // CSRF protection headers
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;
    headers["Authorization"]="Basic " + btoa("user" + ":" + "user")

    //DB Server -> Model -> Application Server -> View <- Controller <- Front
//MVVM user-> REST/SOAP Application Programming Interface client->server->DB
    $.ajax({
        type: "GET",
        contentType: "application/json",
        // url: "/getallpaymentjs?page=" + page + "&size=" + size,
        url: "http://localhost:8090/api/paymentz?page="+page+"&size="+size,
        headers: headers,
        dataType: 'json',
        timeout: 100000,

        success: function (data) {
            var tbl = $("<table/>").attr("id", "mytable").attr("class", "table table-hover");
            $("#div1").append(tbl);
            for (var i = 0; i < data["content"].length; i++) {
                var tr = "<tr>";
                var td1 = "<td>" + data["content"][i]["customerId"] + "</td>";
                var td2 = "<td>" + data["content"][i]["amount"] + "</td>";
                var td3 = "<td>" + data["content"][i]["date"]+"</td> ";
                var td4 = "<td>" + data["content"][i]["channel"] + "</td> ";
                var td5 = "<td><a href='" + data["content"][i]["_links"]["self"]["href"] + "'>Payment</a></td></tr>";
                $("#mytable").append(tr + td1 + td2 + td3 + td4 + td5);
            }

            $("#div1").append("<br>");
            var pageNumber = data['number'];
            var totalPages = data['totalPages'];


            var first = "<button type='button' class='btn btn-success' onclick='replace(0," + data['size'] + ")'" + ">First</button>";
            $("#div1").append(first);

            if (pageNumber != 0) {
                var prev = "<button type='button' class='btn btn-primary' onclick='replace(" + (pageNumber - 1) + ", " + data['size'] + ")'" + ">Prev</button>";
                $("#div1").append(prev);
            }
            if (pageNumber < totalPages -1) {
                var next = "<button type='button' class='btn btn-primary' onclick='replace("
                    + (pageNumber + 1) + ", " + data['size'] + ")'" + ">Next</button>";

                $("#div1").append(next);
            }
            var last = "<button type='button' class='btn btn-success' onclick='replace(" + (totalPages - 1) + ", " + data['size'] + ")'" + ">Last</button>";
            $("#div1").append(last);
        },

        error: function (e) {
            console.log("ERROR: ", e);
        },

        done: function (e) {
            console.log("DONE");
        }
    });


}

function replace(page, size) {
    console.log("page" + page);
    console.log("size" + size);

    // location.replace("http://localhost:8090/api/paymentz?page=" + page + "&size=" + size);
    location.replace("/getallpayments?page=" + page + "&size=" + size);
    return false;
}
