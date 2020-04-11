<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.openblog.entity.User" %>
<%@ page import="java.io.PrintWriter" %>
<%--
    Header part
    Include: heading, search bar, sign up
--%>
<%--Header Start--%>
<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-4 pt-1">
            <a class="text-muted" href="#">Subscribe</a>
        </div>
        <div class="col-4 text-center">
            <a class="blog-header-logo text-dark" href="/OpenBlog">Open Blog</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
            <a class="text-muted" href="search" aria-label="Search">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="mx-3" role="img" viewBox="0 0 24 24" focusable="false">
                    <title>Search</title>
                    <circle cx="10.5" cy="10.5" r="7.5"/>
                    <path d="M21 21l-5.2-5.2"/>
                </svg>
            </a>
            <%
                PrintWriter writer = response.getWriter();
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null) {
                    currentUser = new User();
                    currentUser.setUserName("Sign Up");
                }
            %>
            <a class="btn btn-sm btn-outline-secondary" href="<%=currentUser.getUserUrl() == null ? "register" : currentUser.getUserUrl()%>">
                <%=currentUser.getUserName()%>
            </a>
            <a class="btn btn-sm btn-outline-secondary" href="logout"
               style="<%=currentUser.getUserUrl() == null ? "visibility:hidden;" : ""%>"
                    <%=currentUser.getUserUrl() == null ? "disabled" : ""%>>
                <%=currentUser.getUserUrl() == null ? "" : "Logout"%>
            </a>
            <script>
                $("#logout-btn").disabled=true;
            </script>
        </div>
    </div>
</header>
<div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-between">
        <a class="p-2 text-muted" href="#">Popular</a>
        <a class="p-2 text-muted" href="#">Favourite</a>
        <a class="p-2 text-muted" href="#">Technology</a>
        <a class="p-2 text-muted" href="#">Design</a>
        <a class="p-2 text-muted" href="#">Culture</a>
        <a class="p-2 text-muted" href="#">Business</a>
        <a class="p-2 text-muted" href="#">Politics</a>
        <a class="p-2 text-muted" href="#">Opinion</a>
        <a class="p-2 text-muted" href="#">Science</a>
        <a class="p-2 text-muted" href="#">Health</a>
        <a class="p-2 text-muted" href="#">Style</a>
        <a class="p-2 text-muted" href="#">Travel</a>
    </nav>
</div>
<%--Header End--%>