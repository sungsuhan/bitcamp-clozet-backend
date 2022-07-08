package kr.co.clozet.files.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * packageName:kr.co.clozet.files.properties
 * fileName        :FileProperties.java
 * author          : sungsuhan
 * date            :2022-07-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-07-07           sungsuhan      최초 생성
 **/
@Getter
@Setter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.servlet.multipart")
public class FileProperties {
    private final Boolean enabled;
    private final String location;
    private final String maxFileSize;
    private final String maxRequestSize;
    private final String fileSizeThreshold;
    //private String location;
}
