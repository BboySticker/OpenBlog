<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.openblog.entity.User" %>
<%--
    Header part
    Include: heading, search bar, sign up
--%>
<%--Header Start--%>
<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-6 text-left">
            <a class="blog-header-logo text-dark" href="/OpenBlog">Open Blog</a>
        </div>
        <div class="col-6">
            <div class="row flex-nowrap justify-content-end align-items-center">
                <div class="col-6 text-right">
                    <form action="/OpenBlog/article/list/1" method="get">
                        <div class="input-group input-group-sm form-inline">
                            <input type="hidden" name="action" value="search">
                            <input id="search-input" name="keyword" type="search" class="form-control form-control-sm" placeholder="Search..." />
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-outline-secondary" type="button" id="button-addon2">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
                <%
                    User currentUser = (User) session.getAttribute("user");
                    if (currentUser == null) {
                        currentUser = new User();
                        currentUser.setUserName("SignUp");
                    }
                %>
                <div class="col-6 text-right">
                    <div class="row flex-nowrap justify-content-end align-items-center">
                        <div class="col-4 text-right" id="log-out">
                            <a class="btn btn-sm btn-outline-secondary" href="/OpenBlog/logout"
                               style="<%=currentUser.getUserUrl() == null ? "visibility:hidden;" : ""%>"
                                    <%=currentUser.getUserUrl() == null ? "disabled" : ""%>>
                                <%=currentUser.getUserUrl() == null ? "" : "Logout"%>
                            </a>
                        </div>
                        <div class="col-4 text-right">
                            <a class="btn btn-sm btn-outline-secondary" href="/OpenBlog/editor/drafts/new"
                               style="<%=currentUser.getUserUrl() == null ? "visibility:hidden;" : ""%>"
                                    <%=currentUser.getUserUrl() == null ? "disabled" : ""%>>
                                <%=currentUser.getUserUrl() == null ? "" : "Write"%>
                            </a>
                        </div>
                        <div class="col-4 text-right">
                            <a class="btn btn-sm btn-outline-secondary" href="/OpenBlog/<%=currentUser.getUserUrl() == null ? "register" : currentUser.getUserUrl()%>">
                                <%=currentUser.getUserName()%>
                            </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</header>
<div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-between">
        <a class="p-2 text-muted" href="#">Popular</a>
        <a class="p-2 text-muted" href="#">Favourite</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/technology/1">Technology</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/design/1">Design</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/culture/1">Culture</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/business/1">Business</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/politics/1">Politics</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/opinion/1">Opinion</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/science/1">Science</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/health/1">Health</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/style/1">Style</a>
        <a class="p-2 text-muted" href="/OpenBlog/category/travel/1">Travel</a>
    </nav>
</div>
<%--Header End--%>