package kr.co.clozet.articles.repositories;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.users.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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


}


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);
}
