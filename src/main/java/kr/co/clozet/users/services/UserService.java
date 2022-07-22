package kr.co.clozet.users.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public interface UserService {
    Messenger save(UserDTO user);

    UserDTO login(UserDTO user);

    Optional<User> findByToken(UserDTO userDTO);

    UserDTO find_id(UserDTO user);

    //이메일발송
    void sendEmail(UserDTO user, String div) throws Exception;

    //비밀번호찾기
    void findPw(HttpServletResponse resp, UserDTO user) throws Exception;

    void update(UserDTO userDTO) throws Exception;

    void delete(UserDTO userDTO) throws Exception;

}
