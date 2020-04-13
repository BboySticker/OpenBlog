<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--Header Start--%>
<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-10 text-left">
            <a class="blog-header-logo text-dark" href="/OpenBlog">Open Blog</a>
        </div>
        <div class="col-2 text-right">
            <form action="save" id="article-form" method="post">
                <input type="submit" class="btn btn-outline-secondary" value="Submit">
            </form>
        </div>
    </div>
</header>
<%--Header End--%>