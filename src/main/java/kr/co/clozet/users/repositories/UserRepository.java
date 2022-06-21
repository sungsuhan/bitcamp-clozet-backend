package kr.co.clozet.users.repositories;

import kr.co.clozet.users.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
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
    @Query(value = "select * from users where name like '한%'",
            nativeQuery = true)
    List<User> findHan();

    @Modifying(clearAutomatically = true)
    @Query(value = "select users.phone from users where users.name like '한%'",
            nativeQuery = true)
    List<User> findPhoneByHan();
}


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByUsername(String username);


}