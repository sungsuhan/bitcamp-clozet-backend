package kr.co.clozet.articles.repositories;

import kr.co.clozet.articles.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.board.repositories
 * fileName        :ArticleRepository.java
 * author          : sungsuhan
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           sungsuhan      최초 생성
 **/

interface ArticleCustomRepository{

    @Query(value = "SELECT a FROM Article a where a.user.username = :username")
    List<Article> findByUsernameToArticle(@Param("username") String username);

    @Query(value = "SELECT a FROM Article a where a.user.token = :token")
    String [] findByTokenToArticle(@Param("token") String token);

    @Transactional @Modifying
    @Query("update Article a set a.view = a.view + 1 where a.title = :title")
    int updateView(@Param("title") String title);

}


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
    Optional<Article> findByTitle(String title);
}
