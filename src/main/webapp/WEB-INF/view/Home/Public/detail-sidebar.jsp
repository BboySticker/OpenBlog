<aside class="col-md-4 blog-sidebar">
    <div class="p-4 mb-3 bg-light rounded">
        <h4 class="font-italic">Category: ${article.articleCategory.categoryName}</h4>
        <p class="mb-0">${article.articleCategory == null ? "No Category" : article.articleCategory.categoryDescription}</p>
    </div>

    <div class="p-4">
        <h4 class="font-italic">Tag</h4>
        <ol class="list-unstyled mb-0">
            <li>
                <a href="${article.articleTag == null ? "#" : "/OpenBlog/tag/"}${article.articleTag == null ? "" : article.articleTag.tagName}${article.articleTag == null ? "" : "/1"}">
                    ${article.articleTag == null ? "No Tag" : article.articleTag.tagName}
                </a>
            </li>
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
</aside>