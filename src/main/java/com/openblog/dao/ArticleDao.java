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
     * Pagination
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
//    PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);

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
     * 获得最后更新记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();

    /**
     * 获得相关文章
     *
     * @param cateId 分类ID
     * @param limit  查询数量
     * @return 列表
     */
    List<Article> listArticleByCategoryId(Integer cateId, Integer limit);

    /**
     * 获得相关文章
     *
     * @param cateIds 分类ID集合
     * @param limit   数量
     * @return 列表
     */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);


    /**
     * 根据文章ID获得分类ID列表
     *
     * @param articleId 文章Id
     * @return 列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * 获得所有的文章
     *
     * @return 列表
     */
    List<Article> listAllNotWithContent();

}
