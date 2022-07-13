package kr.co.clozet.users.repositories;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.repositories
 * fileName        :UserRepository.java
 * author          : sungsuhan
 * date            :2022-05-03
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-03           sungsuhan      최초 생성
 **/

interface UserCustomRepository{
    @Modifying(clearAutomatically = true)
    @Query(value = "select * from users where users.name like '한%'",
            nativeQuery = true)
    List<User> findHan();

    @Modifying(clearAutomatically = true)
    @Query(value = "select users.name from users where users.name like '한%'",
            nativeQuery = true)
    String [] findPhoneByHan();

    @Modifying(clearAutomatically = true)
    @Query(value = "select a.title from articles a where a.user_id like (select u.user_id from users u where u.user_id like 1)",
            nativeQuery = true)
    String [] findTitleByUserId();

    @Query(value = "select u.username from User u where u.name = :name and u.email = :email")
    String findUsername(@Param("name") String name, @Param("email") String email);

}

@Repository @Transactional
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByUsername(Map<String, String> userDTO);
    Optional<User> findByToken(String token);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

}