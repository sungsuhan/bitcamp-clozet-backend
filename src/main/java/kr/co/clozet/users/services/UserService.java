package kr.co.clozet.users.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.services
 * fileName        :UserService.java
 * author          : sungsuhan
 * date            :2022-05-03
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-03           sungsuhan      최초 생성
 **/
public interface UserService {
    UserDTO login(UserDTO user);

    List<User> findAll();

    List<User> findAll(Sort sort);

    Page<User> findAll(Pageable pageable);

    Messenger count();

    //UserDTO update(UserDTO user);

    Messenger delete(UserDTO user);

    Messenger deleteAll();

    Messenger save(UserDTO user);

    Optional<User> findById(UserDTO userDTO);

    Optional<User> findByToken(UserDTO userDTO);

    Messenger existsById(String userid);

    User findByToken(UserDTO userDTO);

    // custom
    Optional<User> findByUsername(String username);

    Messenger logout();

    UserDTO findUsername(UserDTO user);

    //이메일발송
    public void sendEmail(UserDTO user, String div) throws Exception;

    //비밀번호찾기
    public void findPw(HttpServletResponse resp, UserDTO user) throws Exception;

    int partialUpdate(UserDTO userDTO);
}
