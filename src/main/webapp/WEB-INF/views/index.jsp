<html>
<head>
    <title>Index</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="include/headnav.jsp" %>
    <div class="container-fluid">
        <div class="row content">
            <div class="col-sm-3">
                <div class="col-sm-12">
                    <button onclick="location.href='/getallpayments?page=0&size=2'" class="btn btn-primary">Get all payments</button>
                </div>
                <div class="col-sm-12">
                    <h5>Old links:</h5>
                    <a href="user">Go to the all users page</a><br>
                    <a href="userregistration">Registration</a><br>
                    <a href="newlogin">Login</a><br>
                    <a href="protected">Go to the protected page</a><br>
                    <a href="confidential">Go to the confidential page</a><br>

                    <a href="/protected/users/userlist">User List</a><br>
                </div>
            </div>

            <div class="col-sm-9">
                <h2>Welcome page</h2>
            </div>
        </div>
    </div>
</body>
</html>
