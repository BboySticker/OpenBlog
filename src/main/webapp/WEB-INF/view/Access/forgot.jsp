<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Kodinger">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>My Login Page &mdash; Bootstrap 4 Login Page Snippet</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>

    <body class="my-login-page">
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-md-center align-items-center h-100">
                    <div class="card-wrapper">
                        <div class="card fat">
                            <div class="card-body">
                                <h4 class="card-title">Forgot Password</h4>
                                <form id="reset-form" method="POST" class="my-login-validation" novalidate="">
                                    <div class="form-group">
                                        <label for="email">E-Mail Address</label>
                                        <input id="email" type="email" class="form-control" name="email" value="" required autofocus>
                                        <div class="invalid-feedback">
                                            Email is invalid
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="token">Reset Token</label>
                                        <input id="token" type="text" class="form-control" name="token" value="" required data-eye>
                                        <div class="form-text text-muted">
                                            By clicking "Get Token" we will send a password reset token to you
                                        </div>
                                    </div>

                                    <div class="form-group m-0">
                                        <button type="button" class="btn btn-primary btn-block" id="reset-btn">
                                            Reset Password
                                        </button>
                                    </div>

                                    <div class="mt-4 text-center">
                                        Back to <a href="login">Login</a>
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
        <script type="text/javascript">
        'use strict';

        $(function() {
            $("input[type='text'][data-eye]").each(function(i) {
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

                $this.after($("<button/>", {
                    html: 'Get Token',
                    class: 'btn btn-primary btn-sm',
                    id: 'passeye-toggle-'+i,
                    type: 'button',
                }).css({
                    position: 'absolute',
                    right: 10,
                    top: ($this.outerHeight() / 2) - 12,
                    padding: '2px 7px',
                    fontSize: 12,
                    cursor: 'pointer',
                }));

                var InterValObj;  // timer obj
                var count = 60;  // disable the button for 60 seconds
                var curCount;  // remained seconds

                $("#passeye-toggle-"+i).click(function() {
                    // check the input of the form is valid
                    var form = $(".my-login-validation");
                    if (form[0].checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.addClass('was-validated');

                    // make a counter to allow user only
                    // request for sending email every 60 seconds
                    curCount = count;
                    $("#passeye-toggle-"+i).attr("disabled", "true");  // disabled the send button
                    $("#passeye-toggle-"+i).html("Retry after " + curCount + "seconds");
                    InterValObj = window.setInterval(setRemainTime, 1000);  // activate the timer, execute every 1 second

                    // send a sync ajax request to backend
                    var email = $("#email").val();
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: 'getResetToken',
                        data: {"email": email},
                        dataType: "json",
                        success: function (data) {
                            if (data.code == 0) {
                                alert(data.msg);
                            } else {
                                alert(data.msg);
                            }
                        },
                        error: function () {
                            alert("Failed to retrieve data")
                        }
                    });
                });

                // time handler
                function setRemainTime() {
                    if (curCount == 0) {
                        window.clearInterval(InterValObj);  // stop the timer
                        $("#passeye-toggle-"+i).removeAttr("disabled");  // enable the button
                        $("#passeye-toggle-"+i).html("Re-Send");  // change the displayed text to re-send
                    } else {
                        curCount --;  // counting down
                        $("#passeye-toggle-"+i).html("Retry after " + curCount + "seconds");
                    }
                }
            });

            $("#reset-btn").click(function() {
                // when user click the submit button
                // validate the user input
                var form = $(this);
                if (form[0].checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.addClass('was-validated');

                // send a sync ajax request to backend
                var email = $("#email").val();
                var token = $("#token").val();
                $.ajax({
                    async: false,
                    type: "POST",
                    url: 'resetVerify',
                    data: $("#reset-form").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            alert(data.msg);
                        } else {
                            window.location.href = "reset";
                        }
                    },
                    error: function () {
                        alert("Failed to retrieve data")
                    }
                });
            });
        });
    </script>
    </body>
</html>
