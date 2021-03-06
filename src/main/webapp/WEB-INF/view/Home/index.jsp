<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Home Page</title>

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
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet"/>
    <link href="css/general.css" rel="stylesheet"/>
</head>
<body>
    <div class="container">

        <jsp:include page="Public/header.jsp" /><!-- /.blog-header -->

        <div class="jumbotron p-4 p-md-5 text-white rounded bg-dark">
            <div class="col-md-6 px-0">
                <h1 class="display-4 font-italic">${jumbotron.articleTitle}</h1>
                <p class="lead my-3">${jumbotron.articleSummary}......</p>
                <p class="lead mb-0"><a href="/OpenBlog/article/${jumbotron.articleId}" class="text-white font-weight-bold">Continue reading...</a></p>
            </div>
        </div><!-- /.jumbotron -->

        <div class="row mb-2">
            <c:forEach items="${featuredPosts}" var="featuredPost">
                <div class="col-md-6">
                    <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary">${featuredPost.articleCategory.categoryName}</strong>
                            <h3 class="mb-0">${featuredPost.articleTitle}</h3>
                            <div class="mb-1 text-muted">${featuredPost.articleCreateTime}</div>
                            <p class="card-text mb-auto">${featuredPost.articleSummary.length() > 100 ? featuredPost.articleSummary.substring(0,100) : featuredPost.articleSummary}......</p>
                            <a href="/OpenBlog/article/${featuredPost.articleId}" class="stretched-link">Continue reading</a>
                        </div>
                        <div class="col-auto d-none d-lg-block">
                            <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                <title>Placeholder</title>
                                <rect width="100%" height="100%" fill="#55595c"/>
                                <text x="50%" y="50%" fill="#eceeef" dy=".3em">

                                </text>
                            </svg>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div><!-- /.featured post -->
    </div>

    <main role="main" class="container">
        <div class="row">
            <div class="col-md-8 blog-main">
                <h3 class="pb-4 mb-4 font-italic border-bottom">
                    What's New Today
                </h3>

                <c:forEach items="${whatsNewToday}" var="newArticle">
                    <div class="blog-post">
                        <h2 class="blog-post-title">${newArticle.articleTitle}</h2>
                        <p class="blog-post-meta">${newArticle.articleCreateTime} by <a href="/OpenBlog/user/${newArticle.user.userName}">${newArticle.user.userName}</a></p>
                        ${newArticle.articleContent} <!-- Insert Rich HTML here -->
                    </div><!-- /.blog-post -->
                </c:forEach>
            </div><!-- /.blog-main -->

            <aside class="col-md-4 blog-sidebar">
                <div class="p-4 mb-3 bg-light rounded">
                    <h4 class="font-italic">About</h4>
                    <p class="mb-0">Here are three newly added articles</p>
                    <p class="mb-0">Total views: ${views}</p>
                </div>

                <div class="p-4">
                    <h4 class="font-italic">Tags</h4>
                    <ol class="list-unstyled mb-0">
                        <c:forEach items="${tagList}" var="tag">
                            <li><a href="/OpenBlog/tag/${tag.tagName}/1">${tag.tagName}</a></li>
                        </c:forEach>
                    </ol>
                </div>

                <div class="p-4">
                    <h4 class="font-italic">Elsewhere</h4>
                    <ol class="list-unstyled">
                        <li><a href="https://github.com/BboySticker/">GitHub</a></li>
                        <li><a href="https://www.linkedin.com/in/xinyu-zhang-31a685170/">LinkedIn</a></li>
                        <li><a href="#">Facebook</a></li>
                    </ol>
                </div>
            </aside><!-- /.blog-sidebar -->

        </div><!-- /.row -->

    </main><!-- /.container -->

    <jsp:include page="Public/footer.jsp" /><!-- /.blog-footer -->

</body>
</html>