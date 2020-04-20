<aside class="col-md-4 blog-sidebar">
    <div class="p-4 mb-3 bg-light rounded">
        <h4 class="font-italic">About</h4>
        <p class="mb-0">Here are three newly added articles</p>
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
            <li><a href="#">Twitter</a></li>
            <li><a href="#">Facebook</a></li>
        </ol>
    </div>
</aside>