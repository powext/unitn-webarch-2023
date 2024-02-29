<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <script type="text/javascript">
        function validate() {
            var p1 = document.getElementsByName("password")[0].value;
            var p2 = document.getElementsByName("repeat_password")[0].value;
            var valid = false;
            if(p1 === p2) {
                valid = true;
                document.form.submit()
            } else {
                alert("Passwords don't match.")
            }
            return valid;
        }
    </script>
</head>
<body>
    <h1><%= "Registration" %></h1>
    <form name="form" action ="registration" method="post">
        Username
        <input type="text" name="username" required/><br>

        Password
        <input type="password" name="password" required/><br>

        Repeat Password
        <input type="password" name="repeat_password" required/><br>

        <input type="button" value="submit" onclick="validate()">
    </form>
</body>
</html>