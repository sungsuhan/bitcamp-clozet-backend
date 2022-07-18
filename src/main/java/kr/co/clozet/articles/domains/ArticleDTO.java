package kr.co.clozet.articles.domains;

import io.swagger.annotations.ApiModelProperty;
import kr.co.clozet.users.domains.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * packageName:kr.co.clozet.articles.domains
 * fileName        :ArticleDTO.java
 * author          : sungsuhan
 * date            :2022-06-15
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-15           sungsuhan      최초 생성
 **/
@Component @Data
public class ArticleDTO {
    @ApiModelProperty(position = 1) private long articleId;
    @ApiModelProperty(position = 2) String title;
    @ApiModelProperty(position = 3) String writtenDate;
    @ApiModelProperty(position = 4) String open;
    @ApiModelProperty(position = 5) String content;
    @ApiModelProperty(position = 6) String height;
    @ApiModelProperty(position = 7) String weight;
    @ApiModelProperty(position = 8) User user;
    @ApiModelProperty(position = 9) long fileId;
    @ApiModelProperty(position = 10) long clothesId;
    @ApiModelProperty(position = 11) String comment;
    @ApiModelProperty(position = 12) String qna;
    @ApiModelProperty(position = 13) int view;
    @ApiModelProperty(position = 14) String token;
    @ApiModelProperty(position = 15) String nickname;


}
