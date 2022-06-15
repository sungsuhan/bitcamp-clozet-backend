package kr.co.clozet.boards.domains;

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
public class BoardDTO {
    @ApiModelProperty(position = 1) private long boardId;
    @ApiModelProperty(position = 2) boolean likes;
    @ApiModelProperty(position = 3) String open;
    @ApiModelProperty(position = 4) String picture;
    @ApiModelProperty(position = 5) String comment;
    @ApiModelProperty(position = 6) private String height;
    @ApiModelProperty(position = 7) private String weight;
    @ApiModelProperty(position = 8) private String createdDate;
    @ApiModelProperty(position = 9) private String inquiry;
    @ApiModelProperty(position = 10) private String title;
    @ApiModelProperty(position = 11) private String content;
}
