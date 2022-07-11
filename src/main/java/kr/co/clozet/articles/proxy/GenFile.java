package kr.co.clozet.articles.proxy;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;

/**
 * packageName:kr.co.clozet.articles.proxy
 * fileName        :GenFile.java
 * author          : sungsuhan
 * date            :2022-07-11
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-07-11           sungsuhan      최초 생성
 **/
@Data
@Component("gfile")
public class GenFile<T> {
    private File file;

    public File makeFile(T t1, String t2) {
        HashMap<String, T> o = new HashMap<>();
        o.put("T", t1);

        if (o.get("T") instanceof String) {
            file = new File((String) o.get("T"), t2);
        } else if (o.get("T") instanceof File) {
            System.out.println(">>> " + (File) o.get("T"));
            file = new File((File) o.get("T"), t2);
        }
        return file;
    }
}