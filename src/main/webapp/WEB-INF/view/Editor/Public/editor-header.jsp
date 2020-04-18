<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--Header Start--%>
<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-4 text-left">
            <a class="blog-header-logo text-dark" href="/OpenBlog">Open Blog</a>
        </div>
        <div class="col-8">
            <form action="${article == null ? "/OpenBlog/editor/drafts/save" : "/OpenBlog/editor/drafts/update"}" id="article-form" method="post">
                <div class="row">
                    <div class="col-6 text-center input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">Title</span>
                        </div>
                        <input id="title" type="text" class="form-control" name="title" value="${article == null ? "" : article.articleTitle}">
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
</header>
<%--Header End--%>