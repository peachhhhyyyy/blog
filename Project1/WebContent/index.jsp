<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to BitBlog</title>
        <link rel="stylesheet" href="css/index.css">
        <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
		<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <div class="wrap">
            <div class="form-wrap">
                <div class="button-wrap">
                    <div id="btn"></div>
                    <button type="button" class="togglebtn" onclick="login()">LOG IN</button>
                    <button type="button" class="togglebtn" onclick="register()">REGISTER</button>
                </div>
                <form id="login" action="board/board.do?m=login" method="post" class="input-group">
                    <input type="text" class="input-field" placeholder="Email" name="email" required autocomplete="email">
                    <input type="password" class="input-field" placeholder="Enter Password" name="pwd" required autocomplete="current-password">
                    <button class="submit">Login</button>
                </form>
                <form id="register" action="board/board.do?m=register" method="post" class="input-group">
                    <input type="text" class="input-field" placeholder="Your Name" name="name" required autocomplete="name">
                    <input type="email" class="input-field" placeholder="Your Email" name="email" required autocomplete="name">
                    <input type="password" class="input-field" placeholder="Enter Password" name="pwd" required autocomplete="new-password">
                    <button class="submit">REGISTER</button>
                </form>
            </div>
        </div>
        <script>
            var x = document.getElementById("login");
            var y = document.getElementById("register");
            var z = document.getElementById("btn");
            
            
            function login(){
                x.style.left = "50px";
                y.style.left = "450px";
                z.style.left = "0";
            }

            function register(){
                x.style.left = "-400px";
                y.style.left = "50px";
                z.style.left = "110px";
            }
        </script>
    </body>
</html> 