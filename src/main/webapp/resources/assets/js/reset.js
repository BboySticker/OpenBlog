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

    $("#submit-btn").click(function() {
        var form = $(".my-login-validation");
        if (form[0].checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.addClass('was-validated');

        var email = $("#email").val();
        var password = $("#password").val();
        var confirmedPassword = $("#password-confirm").val();
        if(email == "") {
            alert("Email cannot be empty!");
        } else if(password == "") {
            alert("Password cannot be empty!");
        } else if (confirmedPassword == "") {
            alert("Confirmed Password cannot be empty!");
        } else if (password != confirmedPassword) {
            alert("Password and Confirmed Password should be same!");
        } else {
            $.ajax({
                async: false,
                type: "POST",
                url: 'resetPasswordVerify',
                data: $("#password-reset-form").serialize(),
                dataType: "json",
                success: function(data) {
                    if(data.code == 0) {
                        alert(data.msg);
                    } else {
                        window.location.href = "login";
                    }
                },
                error: function() {
                    alert("Failed to retrieve data")
                }
            })
        }
    });
});