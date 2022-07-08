package kr.co.clozet.files.services;

import io.github.classgraph.Resource;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.files.domains.File;
import kr.co.clozet.files.domains.FileDTO;
import kr.co.clozet.files.properties.FileProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.services
 * fileName        :BoardService.java
 * author          : sungsuhan
 * date            :2022-05-04
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-04           sungsuhan      최초 생성
 **/
public interface FileService {
    List<File> findAll();

    List<File> findAll(Sort sort);

    Page<File> findAll(Pageable pageable);

    long count();

    String delete(File file);

    Messenger save(FileDTO fileDTO);

    Optional<File> findById(String file);

    boolean existsById(String file);

    List<File> saveFileList(List<File> fileList);

//    void FileService(FileProperties fileProperties);
//
//    public void init();
//
//    public Resource loadFile(String fileName) throws FileNotFoundException;

}
