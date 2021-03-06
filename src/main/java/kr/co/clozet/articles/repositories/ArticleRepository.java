package kr.co.clozet.articles.repositories;

import kr.co.clozet.articles.domains.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


interface ArticleCustomRepository{

    @Query(value = "SELECT a FROM Article a where a.user.username = :username")
    List<Article> findByUsernameToArticle(@Param("username") String username);

    @Query(value = "SELECT a FROM Article a where a.token = :token and a.open is null")
    List<Article> findByTokenToArticle(@Param("token") String token);

    @Query(value = "SELECT a FROM Article a where a.token = :token and a.open is not null")
    List<Article> findMyQna(@Param("token") String token);

    @Query(value = "SELECT a FROM Article a WHERE a.open is null")
    List<Article> findAllArticle();


    @Transactional @Modifying
    @Query("update Article a set a.view = a.view + 1 where a.articleId = :articleId")
    int updateView(@Param("articleId") Long articleId);

    @Transactional @Modifying
    @Query("delete from Article a where a.token in :token and a.articleId = :articleId")
    void deleteArticle(@Param("token") String token, @Param("articleId") long articleId);

    @Transactional @Modifying
    @Query("delete from Article a where a.token in :token and a.articleId = :articleId")
    void deleteComment(@Param("token") String token, @Param("articleId") long articleId);

    @Query("select a.articleId FROM Article a join User u on u.userId = a.user.userId where a.user.username = :username")
    List<Article> findByUsername(@Param("username") String username);

    @Query("SELECT a from Article a WHERE a.open LIKE 'true' order by a.writtenDate ASC")
    List<Article> findByQnaDateASC(@Param("open") String open);


}


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
    List<Article> findByTitle(String title);
    List<Article> findByOpen(String open);
    List<Article> findByArticleId(long articleId);
    List<Article> findAll(Sort sort);
    List<Article> findByToken(String token);
    void deleteArticleByArticleId(Long articleId);

}
