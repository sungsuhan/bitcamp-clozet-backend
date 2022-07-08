package kr.co.clozet.files.repositories;

import kr.co.clozet.files.domains.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName:kr.co.clozet.repositories
 * fileName        :BoardRepository.java
 * author          : sungsuhan
 * date            :2022-05-04
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-04           sungsuhan      최초 생성
 **/

interface FileCustomRepository{


}

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
