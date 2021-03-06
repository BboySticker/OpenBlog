<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Article List</title>

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
            margin-bottom: 2rem;
        }
        .blog-post-title {
            margin-bottom: .25rem;
            font-size: 1.5rem;
            font-family: "Playfair Display", Georgia, "Times New Roman", serif;
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
                <%
                    String displayedTitle = "";
                    String action = response.getHeader("action");
                    if (action == null) {
                        displayedTitle = "";
                    } else if (action.equalsIgnoreCase("search")) {
                        displayedTitle = "Search Results";
                    } else if (action.equalsIgnoreCase("category")) {
                        displayedTitle = "Category: ";
                    } else if (action.equalsIgnoreCase("tag")) {
                        displayedTitle = "Tag: ";
                    } else if (action.equalsIgnoreCase("popular")) {
                        displayedTitle = "Popular Articles";
                    }
                %>
                <h2 class="text-center">
                    <%=displayedTitle%>
                    ${category == null ? "" : category.categoryName}
                    ${tag == null ? "" : tag.tagName}
                </h2>

                <c:forEach items="${articleList}" var="article">
                    <div class="blog-post">
                        <a class="blog-post-title" href="/OpenBlog/article/${article.articleId}">${article.articleTitle}</a>
                        <p class="blog-post-meta">
                                ${article.articleCreateTime} by
                                <a href="/OpenBlog/user/${article.user.userName}">${article.user.userName}</a>
                                Views: ${article.articleViewCount}
                        </p>
                        ${article.articleSummary} <!-- Insert article summary here -->
                    </div><!-- /.blog-post -->
                </c:forEach>

                <nav class="blog-pagination">
                    <c:forEach var="i" begin="1" end="${pageCount}">
                        <a class="btn btn-outline-primary ${i == pageIndex ? "disabled" : ""}" href="${href}/${i}${action == "search" ? "?keyword=" : ""}${keyword}">${i}</a>
                    </c:forEach>
                </nav>
            </div><!-- /.blog-main -->

            <aside class="col-md-4 blog-sidebar">
                <div class="p-4 mb-3 bg-light rounded">
                    <h4 class="font-italic">
                        <c:if test="${action == 'search'}">About</c:if>
                        <c:if test="${action == 'category'}">${category.categoryName}</c:if>
                        <c:if test="${action == 'tag'}">${tag.tagName}</c:if>
                        <c:if test="${action == 'popular'}">About</c:if>
                    </h4>
                    <p class="mb-0">
                        <c:if test="${action == 'search'}">Results displayed based on searching keyword</c:if>
                        <c:if test="${action == 'category'}">${category.categoryDescription}</c:if>
                        <c:if test="${action == 'tag'}">${tag.tagDescription}</c:if>
                        <c:if test="${action == 'popular'}">Results displayed based on view count</c:if>
                    </p>
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

    <jsp:include page="Public/footer-2.jsp" /><!-- /.blog-footer -->

</body>
</html>