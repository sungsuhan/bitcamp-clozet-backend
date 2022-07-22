package kr.co.clozet.users.repositories;

import kr.co.clozet.users.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;



interface UserCustomRepository{

    @Query("SELECT u.username FROM User u WHERE u.name= :name AND u.email= :email")
    String find_id(@Param("name") String name, @Param("email") String email);

}

@Repository @Transactional
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByToken(String token);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

}