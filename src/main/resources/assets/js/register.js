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

    $("#register-btn").click(function() {
        var form = $(".my-login-validation");
        if (form[0].checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.addClass('was-validated');

        var name = $("#name").val();
        var user = $("#email").val();
        var password = $("#password").val();
        if (name == "") {
            alert("Username cannot be empty!");
        } else if (name.indexOf(' ') !== -1) {
            alert("Username cannot contain spaces!");
        } else if(user == "") {
            alert("Email cannot be empty!");
        } else if(password == "") {
            alert("Password cannot be empty!");
        } else {
            $.ajax({
                async: false,
                type: "POST",
                url: 'registerVerify',
                data: $("#register-form").serialize(),
                dataType: "json",
                success: function(data) {
                    if(data.code == 0) {
                        alert(data.msg);
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