package com.openblog.service;

import com.openblog.entity.Article;

import java.util.List;

public interface ArticleService {
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
     * @param id Article ID
     */
    void deleteArticle(Integer id);

    /**
     * 分页显示
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
//    PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);

    /**
     * 文章详情页面显示
     *
     * @param status 状态
     * @param id     文章ID
     * @return 文章
     */
    Article getArticleByStatusAndId(Integer status, Integer id);

    /**
     * 获取访问量较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(Integer id);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * 获得评论数较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByCommentCount(Integer limit);

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void insertArticle(Article article);


    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
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
