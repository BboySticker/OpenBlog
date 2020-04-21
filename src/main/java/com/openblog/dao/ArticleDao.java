package com.openblog.dao;

import com.openblog.entity.Article;

import java.util.List;

public interface ArticleDao {
    /**
     * Retrieve the count of articles according to status.
     *
     * @param status
     * @return Integer, num of articles
     */
    Integer countArticle(Integer status);

    /**
     * Retrieve the count of comments for all articles.
     *
     * @return Integer, count
     */
    Integer countArticleComment();

    /**
     * Retrieve the total views.
     *
     * @return Integer, count
     */
    Integer countArticleView();

    /**
     * Retrieve the count of articles according to category.
     *
     * @param categoryId
     * @return Integer, count
     */
    Integer countArticleByCategoryId(Integer categoryId);

    /**
     * Retrieve the count of articles according to tag.
     *
     * @param tagId
     * @return Integer, count
     */
    Integer countArticleByTagId(Integer tagId);

    /**
     * Retrieve all articles in a list.
     *
     * @return List<Article>
     */
    List<Article> listArticle();

    /**
     * Retrieve certain number of most recent articles in a list.
     *
     * @param limit
     * @return List<Article>
     */
    List<Article> listRecentArticle(Integer limit);

    /**
     * Update article detail.
     *
     * @param article
     */
    void updateArticleDetail(Article article);

    /**
     * Update article in general.
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * Delete articles in a batch.
     *
     * @param ids A list of Article ID
     */
    void deleteArticleBatch(List<Integer> ids);

    /**
     * Delete an article according to its ID.
     *
     * @param article Article
     */
    void deleteArticle(Article article);

    /**
     * Retrieve article by status and id.
     *
     * @param status
     * @param id
     * @return Article
     */
    Article getArticleByStatusAndId(Integer status, Integer id);

    /**
     * Retrieve articles based on view count.
     *
     * @param limit
     * @return List<Article>
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * Retrieve previous article based on current article id.
     *
     * @param id Article.articleId
     * @return Article
     */
    Article getAfterArticle(Integer id);

    /**
     * Retrieve next article based on current article id.
     *
     * @param id
     * @return Article
     */
    Article getPreArticle(Integer id);

    /**
     * Retrieve random article.
     *
     * @param limit
     * @return List<Article>
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * Retrieve articles based on comment count.
     *
     * @param limit
     * @return List<Article>
     */
    List<Article> listArticleByCommentCount(Integer limit);

    /**
     * Add new article.
     *
     * @param article
     */
    void insertArticle(Article article);

    /**
     * Update article's comment count based on Comment schema.
     *
     * @param articleId
     */
    void updateCommentCount(Integer articleId);

    /**
     * Get last update article.
     *
     * @return Article
     */
    Article getLastUpdateArticle();

    /**
     * List all articles according to tag id
     *
     * @param tagId
     * @param limit
     * @return
     */
    List<Article> listArticleByTagId(Integer tagId, Integer limit);

    /**
     * Get articles based on category and limit
     *
     * @param cateId
     * @param limit
     * @return List<Article>
     */
    List<Article> listArticleByCategoryId(Integer cateId, Integer limit);

    /**
     * Get articles based on a list of category id
     *
     * @param cateIds
     * @param limit
     * @return List<Article>
     */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);

    /**
     * Get category id based on article id
     *
     * @param articleId
     * @return List<Integer>
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * Get all articles
     *
     * @return List<Article>
     */
    List<Article> listAllNotWithContent();

    /**
     * Retrieve articles from a perticular user
     *
     * @param userId
     * @return
     */
    List<Article> listArticleByAuthor(String userId);

}
