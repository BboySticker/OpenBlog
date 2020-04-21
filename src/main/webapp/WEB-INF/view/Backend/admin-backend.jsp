<%@ page import="com.openblog.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin - ${user.userName}</title>

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

        h1, h2, h3, h4, h5, h6, #article-title {
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
            margin-bottom: 2rem;
        }
        .blog-post-title {
            margin-bottom: .25rem;
            font-size: 1.5rem;
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

    </style>
</head>
<body>
<div class="container">
    <jsp:include page="Public/header.jsp" /><!-- /.blog-header -->
</div>

<main role="main" class="container">
    <div class="row">
        <div class="col-md-8 blog-main">
            <h2 class="text-center">Manage Articles</h2>
            <c:forEach items="${articleList}" var="article">
                <div class="blog-post">
                    <a id="article-title" href="/OpenBlog/article/${article.articleId}" class="blog-post-title">${article.articleTitle}</a>
                    <p class="blog-post-meta">${article.articleCreateTime} by <a href="/OpenBlog/user/${article.user.userName}">${article.user.userName}</a></p>
                    <p>${article.articleSummary}......</p>
                    <c:if test="${user.userEmail == currentUser.userEmail}">
                        <div>
                            <a class="btn btn-sm btn-outline-secondary" href="/OpenBlog/article/edit/${article.articleId}">Edit</a>
                            <a class="btn btn-sm btn-outline-secondary" href="/OpenBlog/article/delete/${article.articleId}">Delete</a>
                        </div>
                    </c:if>
                </div><!-- /.blog-post -->
            </c:forEach>

            <nav class="blog-pagination">
                <c:forEach var="i" begin="1" end="${pageCount}">
                    <a class="btn btn-outline-primary ${i == pageIndex ? "disabled" : ""}" href="/OpenBlog/admin/${user.userName}/articles/${i}">${i}</a>
                </c:forEach>
            </nav>
        </div><!-- /.blog-main -->

        <aside class="col-md-4 blog-sidebar">
            <div class="p-4 mb-3 bg-light rounded">
                <c:if test="${user.userEmail == currentUser.userEmail}">
                    <h4 class="font-italic">Manage Users</h4>
                    <a href="/OpenBlog/admin/${user.userName}/users" class="mb-0">Click this link to User Management Portal.</a>
                </c:if>
                <c:if test="${user.userEmail != currentUser.userEmail}">
                    <h4 class="font-italic">User Profile</h4>
                    <p class="mb-0">Here is a list articles written by ${user.userName}.</p>
                </c:if>
            </div>
        </aside><!-- /.blog-sidebar -->

    </div><!-- /.row -->

</main><!-- /.container -->

<jsp:include page="../Home/Public/footer-2.jsp" /><!-- /.blog-footer -->

</body>
</html>