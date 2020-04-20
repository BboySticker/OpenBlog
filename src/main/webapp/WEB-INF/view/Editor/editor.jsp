<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editor - Write Article</title>
    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com//docs/4.4/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <style>
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <link href="css/general.css" rel="stylesheet" />
    <style type="text/css">
        .blog-header {
            line-height: 1;
            border-bottom: 1px solid #e5e5e5;
        }

        .blog-header-logo {
            font-family: "Playfair Display", Georgia, "Times New Roman", serif;
            font-size: 2.25rem;
        }

        .blog-header-logo:hover {
            text-decoration: none;
        }

        h1, h2, h3, h4, h5, h6 {
            font-family: "Playfair Display", Georgia, "Times New Roman", serif;
        }

        .display-4 {
            font-size: 2.5rem;
        }
        @media (min-width: 768px) {
            .display-4 {
                font-size: 3rem;
            }
        }

        .nav-scroller {
            position: relative;
            z-index: 2;
            height: 2.75rem;
            overflow-y: hidden;
        }

        .nav-scroller .nav {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-wrap: nowrap;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }

        .nav-scroller .nav-link {
            padding-top: .75rem;
            padding-bottom: .75rem;
            font-size: .875rem;
        }

        .card-img-right {
            height: 100%;
            border-radius: 0 3px 3px 0;
        }

        .flex-auto {
            -ms-flex: 0 0 auto;
            flex: 0 0 auto;
        }

        .h-250 { height: 250px; }
        @media (min-width: 768px) {
            .h-md-250 { height: 250px; }
        }

        /* Pagination */
        .blog-pagination {
            margin-bottom: 4rem;
        }
        .blog-pagination > .btn {
            border-radius: 2rem;
        }

        /*
         * Blog posts
         */
        .blog-post {
            margin-bottom: 4rem;
        }
        .blog-post-title {
            margin-bottom: .25rem;
            font-size: 2.5rem;
        }
        .blog-post-meta {
            margin-bottom: 1.25rem;
            color: #999;
        }

        /*
         * Footer
         */
        .blog-footer {
            padding: 2.5rem 0;
            color: #999;
            text-align: center;
            background-color: #f9f9f9;
            border-top: .05rem solid #e5e5e5;
        }
        .blog-footer p:last-child {
            margin-bottom: 0;
        }

        textarea {
            resize: none;
        }
    </style>
</head>
<body>
    <%
        String title = "";
        String content = "";
    %>
    <div class="container">
        <jsp:include page="Public/editor-header.jsp" /><!-- /.blog-header -->
        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">
                    Instructions:</br>
                    </br>
                    Input need to</br>
                    be in markdown</br>
                    format</br>
                    </br>
                    Click on</br>
                    "Edit with StackEdit"</br>
                    to preview</br>
                </span>
            </div>
            <textarea id="article" name="article" class="form-control" aria-label="With textarea" rows="23" cols="50" form="article-form">${article == null ? "" : article.articleContentInMd}</textarea>
        </div>
        <div class="stackedit-button-wrapper">
            <a href="javascript:open()">
                "Edit with StackEdit"
            </a>
        </div>
    </div>

    <jsp:include page="Public/editor-footer.jsp" /><!-- /.blog-footer -->

    <%-- StackEdit In-Bowser Markdown Editor --%>
    <%-- https://github.com/benweet/stackedit.js/blob/master/docs/lib/stackedit.js --%>
    <script src="https://unpkg.com/stackedit-js@1.0.7/docs/lib/stackedit.min.js"></script>
    <script type="application/javascript">
        const el = document.querySelector('textarea');
        const stackedit = new Stackedit();

        function open() {
            // Open the iframe
            stackedit.openFile({
                name: 'Filename', // with an optional filename
                content: {
                    text: el.value // and the Markdown content.
                }
            });
        }

        // Listen to StackEdit events and apply the changes to the textarea.
        stackedit.on('fileChange', (file) => {
            el.value = file.content.text;
        });
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script>
        'use strict';
        $("#publish-btn").click(function() {
            // var form = $(".my-login-validation");
            var title = $("#title").val();
            var content = $("#article").val();
            if(title == "") {
                alert("Title cannot be empty!");
            } else if(content == "") {
                alert("Article content cannot be empty!");
            }
        });
    </script>
</body>
</html>
