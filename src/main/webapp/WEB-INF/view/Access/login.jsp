<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Kodinger">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>

    <body class="my-login-page">
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-md-center h-100">
                    <div class="card-wrapper">
                        <div class="card fat">
                            <div class="card-body">
                                <h4 class="card-title">Login</h4>
                                <%
                                    String email = "";
                                    String password = "";
                                    // Get all Cookies for current website
                                    Cookie[] cookies = request.getCookies();
                                    if (cookies != null) {
                                        // Iterate through the current cookies array
                                        for (int i = 0; i < cookies.length; i++) {
                                            if ("email".equals(cookies[i].getName())) {
                                                email = cookies[i].getValue();
                                            } else if ("password".equals(cookies[i].getName())) {
                                                password = cookies[i].getValue();
                                            }
                                        }
                                    }
                                %>
                                <form id="login-form" name="login-form" method="POST" class="my-login-validation"  novalidate="">
                                    <div class="form-group">
                                        <label for="email">E-Mail Address</label>
                                        <input id="email" type="email" class="form-control" name="email" value="<%= email %>" required autofocus>
                                        <div class="invalid-feedback">
                                            Email is invalid
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password
                                            <a href="forgot" class="float-right">
                                                Forgot Password?
                                            </a>
                                        </label>
                                        <input id="password" type="password" class="form-control" name="password" value="<%= password %>" required data-eye>
                                        <div class="invalid-feedback">
                                            Password is required
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="custom-checkbox custom-control">
                                            <input type="checkbox" name="remember" id="remember" class="custom-control-input">
                                            <label for="remember" class="custom-control-label">Remember Me</label>
                                        </div>
                                    </div>
                                </form>

                                <div class="form-group m-0">
                                    <%--<input type="submit" class="btn btn-primary btn-block" id="login-btn" value="Login"/>--%>
                                    <button type="button" class="btn btn-primary btn-block" id="login-btn">
                                        Login
                                    </button>
                                </div>

                                <div class="mt-4 text-center">
                                    Don't have an account? <a href="register">Create One</a>
                                </div>
                            </div>
                        </div>
                        <div class="footer">
                            <%@ include file="../Home/Public/footer-2.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="js/login.js"></script>
        <script type="application/javascript">
            'use strict';

            $(function() {
                $("input[type='password'][data-eye]").each(function(i) {
                    var $this = $(this),
                        id = 'eye-password-' + i,
                        el = $('#' + id);

                    $this.wrap($("<div/>", {
                        style: 'position:relative',
                        id: id
                    }));

                    $this.css({
                        paddingRight: 60
                    });
                    $this.after($("<div/>", {
                        html: 'Show',
                        class: 'btn btn-primary btn-sm',
                        id: 'passeye-toggle-'+i,
                    }).css({
                        position: 'absolute',
                        right: 10,
                        top: ($this.outerHeight() / 2) - 12,
                        padding: '2px 7px',
                        fontSize: 12,
                        cursor: 'pointer',
                    }));

                    $this.after($("<input/>", {
                        type: 'hidden',
                        id: 'passeye-' + i
                    }));

                    var invalid_feedback = $this.parent().parent().find('.invalid-feedback');

                    if(invalid_feedback.length) {
                        $this.after(invalid_feedback.clone());
                    }

                    $this.on("keyup paste", function() {
                        $("#passeye-"+i).val($(this).val());
                    });
                    $("#passeye-toggle-"+i).on( "click", function() {
                        if($this.hasClass("show")) {
                            $this.attr('type', 'password');
                            $this.removeClass("show");
                            $(this).removeClass("btn-outline-primary");
                        }else{
                            $this.attr('type', 'text');
                            $this.val($("#passeye-"+i).val());
                            $this.addClass("show");
                            $(this).addClass("btn-outline-primary");
                        }
                    });
                });

                $("#login-btn").click(function() {
                    // var form = $(".my-login-validation");
                    var form = $("#login-form");
                    if (form[0].checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.addClass('was-validated');

                    var email = $("#email").val();
                    var password = $("#password").val();
                    if(email == "") {
                        alert("Email cannot be empty!");
                    } else if(password == "") {
                        alert("Password cannot be empty!");
                    } else {
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: 'loginVerify',
                            data: $("#login-form").serialize(),
                            dataType: "json",
                            success: function(resp) {
                                if(resp.code == 0) {
                                    alert(resp.msg);
                                } else {
                                    window.location.href = "admin";
                                }
                            },
                            error: function() {
                                alert("Failed to retrieve data")
                            }
                        })
                    }
                });
            });
        </script>

    </body>
</html>
