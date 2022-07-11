package kr.co.clozet.articles.proxy;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * packageName:kr.co.clozet.articles.proxy
 * fileName        :FileProxy.java
 * author          : sungsuhan
 * date            :2022-07-11
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-07-11           sungsuhan      최초 생성
 **/
@Component("filemgr")
public class FileProxy extends Proxy {
    public void fileupload(MultipartFile[] uploadFile) {
        String uploadFolder = Path.UPLOAD_PATH.toString();
        File uploadPath = makeDir(uploadFolder, getFolder());

        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        final String s = getFolder();
        for(MultipartFile m : uploadFile) {
            String fname = m.getOriginalFilename();
            String extension = fname.substring(fname.lastIndexOf(".")+1);
            fname = fname.substring(fname.lastIndexOf("\\")+1, fname.lastIndexOf("."));
            File savedFile = makeFile(uploadPath, fname+"-"+ UUID.randomUUID().toString()+"."+extension);
            try {
                m.transferTo(savedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getFolder() {
        return currentDate().replace("-", File.separator);
    }
}

