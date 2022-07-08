package kr.co.clozet.files.services;

import io.github.classgraph.Resource;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.files.domains.File;
import kr.co.clozet.files.domains.FileDTO;
import kr.co.clozet.files.properties.FileProperties;
import kr.co.clozet.files.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.services
 * fileName        :BoardServiceImpl.java
 * author          : sungsuhan
 * date            :2022-05-04
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-04           sungsuhan      최초 생성
 **/
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository repository;
    private Path dirLocation;

    @Override
    public List<File> findAll() {
        return repository.findAll();
    }

    @Override
    public List<File> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<File> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public String delete(File file) {
        repository.delete(file);
        return "";
    }

    @Override
    public Messenger save(FileDTO fileDTO) {
        return null;
    }

    @Override
    public Optional<File> findById(String file) {
        return repository.findById(0L);
    }

    @Override
    public boolean existsById(String file) {
        return repository.existsById(0L);
    }

    @Override
    public List<File> saveFileList(List<File> fileList) {
        return repository.saveAll(fileList);
    }

//    @Override
//    public void FileService(FileProperties fileProperties) {
//        this.dirLocation = Paths.get(fileProperties.getLocation())
//                .toAbsolutePath().normalize();
//    }
//
//    @Override @PostConstruct
//    public void init() {
//        try {
//            Files.createDirectories(this.dirLocation);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Resource loadFile(String fileName) throws FileNotFoundException {
//
//        try {
//            Path file = this.dirLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(file.toUri());
//
//            if(resource.exists() || resource.isReadable()) {
//                return resource;
//            }else {
//                throw new FileNotFoundException("Could not find file");
//            }
//        } catch (MalformedURLException e) {
//            throw new FileNotFoundException("Could not download file");
//        }
//
//    }


}


