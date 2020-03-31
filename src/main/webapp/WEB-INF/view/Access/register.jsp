<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Kodinger">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>My Login Page &mdash; Bootstrap 4 Login Page Snippet</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/my-login.css">
        <style>
            html,body {
                height: 100%;
            }

            body.my-login-page {
                background-color: #f7f9fb;
                font-size: 14px;
            }

            .my-login-page .brand {
                width: 90px;
                height: 90px;
                overflow: hidden;
                border-radius: 50%;
                margin: 40px auto;
                box-shadow: 0 4px 8px rgba(0,0,0,.05);
                position: relative;
                z-index: 1;
            }

            .my-login-page .brand img {
                width: 100%;
            }

            .my-login-page .card-wrapper {
                width: 400px;
            }

            .my-login-page .card {
                border-color: transparent;
                box-shadow: 0 4px 8px rgba(0,0,0,.05);
            }

            .my-login-page .card.fat {
                padding: 10px;
            }

            .my-login-page .card .card-title {
                margin-bottom: 30px;
            }

            .my-login-page .form-control {
                border-width: 2.3px;
            }

            .my-login-page .form-group label {
                width: 100%;
            }

            .my-login-page .btn.btn-block {
                padding: 12px 10px;
            }

            .my-login-page .footer {
                margin: 40px 0;
                color: #888;
                text-align: center;
            }

            @media screen and (max-width: 425px) {
                .my-login-page .card-wrapper {
                    width: 90%;
                    margin: 0 auto;
                }
            }

            @media screen and (max-width: 320px) {
                .my-login-page .card.fat {
                    padding: 0;
                }

                .my-login-page .card.fat .card-body {
                    padding: 15px;
                }
            }
        </style>
    </head>
    <body class="my-login-page">
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-md-center h-100">
                    <div class="card-wrapper">
                        <div class="card fat">
                            <div class="card-body">
                                <h4 class="card-title">Register</h4>
                                <form id="register-form" method="POST" class="my-login-validation" novalidate="">
                                    <div class="form-group">
                                        <label for="name">Name</label>
                                        <input id="name" type="text" class="form-control" name="name" required autofocus>
                                        <div class="invalid-feedback">
                                            What's your name?
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="email">E-Mail Address</label>
                                        <input id="email" type="email" class="form-control" name="email" required>
                                        <div class="invalid-feedback">
                                            Your email is invalid
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input id="password" type="password" class="form-control" name="password" required data-eye>
                                        <div class="invalid-feedback">
                                            Password is required
                                        </div>
                                    </div>

                                    <div class="form-group m-0">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Register
                                        </button>
                                    </div>
                                    <div class="mt-4 text-center">
                                        Already have an account? <a href="login">Login</a>
                                    </div>
                                </form>
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
        <script src="js/my-login.js"></script>
        <script type="text/javascript">
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
                    $("#passeye-toggle-"+i).on("click", function() {
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

                $(".my-login-validation").submit(function() {
                    var name = $("#name").val();
                    var user = $("#email").val();
                    var password = $("#password").val();
                    if (name=="") {
                        alert("Username cannot be empty!");
                    } else if(user=="") {
                        alert("Email cannot be empty!");
                    } else if(password=="") {
                        alert("Password cannot be empty!");
                    } else {
                        $.ajax({
                            type: "POST",
                            url: 'register',
                            data: $("#register-form").serialize(),
                            success: function(resp) {
                                alert(code);
                                if(resp.code == 0) {
                                    alert(resp.msg);
                                } else {
                                    window.location.href = "index";
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
