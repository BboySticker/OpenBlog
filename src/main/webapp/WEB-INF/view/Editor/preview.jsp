<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Preview - ${article.articleTitle}</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com//docs/4.4/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="https://getbootstrap.com//docs/4.4/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <meta name="theme-color" content="#563d7c">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
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

        div label input {
            margin-right:100px;
        }
        body {
            font-family:sans-serif;
        }

        .btn-group {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <header class="blog-header py-3">
            <div class="row flex-nowrap justify-content-between align-items-center">
                <div class="col-4 text-left">
                    <a class="blog-header-logo text-dark" href="/OpenBlog">Open Blog</a>
                </div>
                <div class="col-8">
                    <form action="${article == null ? "/OpenBlog/editor/drafts/save" : "/OpenBlog/editor/drafts/preview"}" id="article-form" method="post">
                        <div class="row">
                            <div class="col-6 text-center input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">Title</span>
                                </div>
                                <input id="title" type="text" class="form-control" name="title" value="${article == null ? "" : article.articleTitle}" disabled>
                            </div>
                            <div class="col-6 text-right">
                                <button id="publish-btn" type="submit" class="btn btn-outline-secondary">
                                    ${article == null ? "Publish" : "Update"}
                                </button>
                            </div>
                        </div>
                        <input type="hidden" name="articleId" value="${article.articleId}">
                    </form>
                </div>
            </div>
        </header><!-- /.blog-header -->
    </div>

    <main role="main" class="container">
        <div class="row">
            <div class="col-md-8 blog-main">
                <div class="blog-post">
                    <h2 class="blog-post-title">${article.articleTitle}</h2>
                    <p class="blog-post-meta">${article.articleCreateTime} by
                        <a href="/OpenBlog/user/${article.user.userName}">${article.user.userName}</a>
                        View: ${article.articleViewCount}
                    </p>
                    ${article.articleContent} <!-- Insert Rich HTML here -->
                </div><!-- /.blog-post -->

                <nav class="blog-pagination">
                    <a class="btn btn-outline-primary" href="#">Older</a>
                    <a class="btn btn-outline-secondary disabled" href="#" tabindex="-1" aria-disabled="true">Newer</a>
                </nav>
            </div><!-- /.blog-main -->

            <aside class="col-md-4 blog-sidebar">
                <div class="p-4 mb-3 bg-light rounded">
                    <h4 class="font-italic">Instruction</h4>
                    <p class="mb-0">Please select Categories and Tags</p>
                </div>

                <div class="p-4">
                    <h4 class="font-italic">Categories</h4>
                    <select class="form-control" name="category" form="article-form">
                        <option value="1">Technology</option>
                        <option value="2">Design</option>
                        <option value="3">Culture</option>
                        <option value="4">Business</option>
                        <option value="5">Politics</option>
                        <option value="6">Opinion</option>
                        <option value="7">Science</option>
                        <option value="8">Health</option>
                        <option value="9">Style</option>
                        <option value="10">Travel</option>
                    </select>
                </div>

                <div class="p-4">
                    <h4 class="font-italic">Tag</h4>
                    <div class="input-group mb-3">
                        <input type="text" id="tag" name="tag" class="form-control"
                               placeholder="Write the tag you want to add..." aria-label="Username"
                               aria-describedby="basic-addon1" form="article-form"/>
                    </div>
                </div>
            </aside><!-- /.blog-sidebar -->
        </div><!-- /.row -->
    </main><!-- /.container -->

    <jsp:include page="../Home/Public/footer-2.jsp" /><!-- /.blog-footer -->

</body>
</html>