# Open Blog

## Summary

Open Blog is a UGC(User Generate Content) platform which allow users share their thoughts by posting articles.

For now, Open Blog mainly focus on technical topics, like data structures, algorithms, programming languages, database techniques, distrubuted systems, etc.

## UX Design

People can sign on to become a user and start posting articles.

Admin and editor will continusly checking those articles and make sure they are liegal, healthy and useful.

Un-login users can only view articles.

Log-in users can comment on articles and like them.

## UI Design

### Home page

- Header: 
  - Logo
  - Search bar
  - Sign-on button
  - List of article categories
- Body:
  - Jumbotron: Top viewed or top liked article; Admin can designate certain article to become the Jumbotron.
  - Featured articles: Top viewed or top liked articles (num = 2).
  - What's New Today: A list of newly posted articles (num = 3~10).
- Sidebar: 
  - About
  - Tags: a list of popular tags

### Article list page (used for display search, filter by category and filter by tag results)

- Header: same as home page
- Body: a list of articles displayed, include title, author, create time, view amount, like amount, etc.
- Sidebar: same as home page

### Article detail page

- Header: same as home page
- Body: Article instance
  - Title
  - Create time
  - Author
  - Article content
- Sidebar: same as home page

### Editor page

- Header:
  - Logo
  - Title input box
  - Publish button
- Body:
  - Textarea for writing article
  - A link to StackEdit, which is a in-bowser markdown editor, for user to edit and preview his/her article

### User manage articles page



## Front-end platform

<b>Account service:</b> user login, logout, register(sign up), retrieve password

<b>Article service:</b> list articles, delete article, update article info (tag, category...)

## Back-end management system

<b>Admin can log in backend system through special url.</b>

<b>Account service:</b> admin login, logout

<b>User service:</b> add user, delete user, update user info; make user become admin;

<b>Article service: </b> list articles, delete article, update article info (tag, category...)
   
  
## Tech Stack  
  
 - Language: Java 8  
 - Framework: Spring, Spring MVC  
 - Front End: JSP/HTML, CSS, JavaScript  
 - JPA: Hibernate  
 - Database: MySQL  
 - Web Server: Tomcat 9  
 - Develop Environment: IntelliJ IDEA, MacOS  
  
## Design  
  
There are two kinds of users for this application. One is Admin, the other is Normal User.  
<li>  
    For Admin, he can do following things:  
    <ul>Login, Logout</ul>  
    <ul>Add User Account</ul>  
    <ul>Delete User Account</ul>  
    <ul>Search for User Account</ul>  
    <ul>Delete Article</ul>  
    <ul>Search for Article</ul>  
</li>  
<li>  
    For Normal User:  
    <ul>Login, Logout</ul>  
    <ul>Manage his/her own Articles [Post, Delete, Edit]</ul>  
    <ul>Comment?</ul>  
    <ul>Like?</ul>  
</li>  
  
## Build  
  
<ul>  
    Make your own EmailService in order to send a password reset email.  
</ul>