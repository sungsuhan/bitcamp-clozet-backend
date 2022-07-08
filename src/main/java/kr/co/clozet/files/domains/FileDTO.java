package kr.co.clozet.files.domains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * packageName:kr.co.clozet.boards.domains
 * fileName        :BoardDTO.java
 * author          : sungsuhan
 * date            :2022-06-15
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-15           sungsuhan      최초 생성
 **/
@Component @Data
public class FileDTO {
    @ApiModelProperty(position = 1) private long fileId;
    @ApiModelProperty(position = 2) String name;
    @ApiModelProperty(position = 3) private String url;
    @ApiModelProperty(position = 4) Long size;

}
