package kr.co.clozet.files.domains;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.closets.domains.Closet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName:kr.co.clozet.auth.domains
 * fileName        :Board.java
 * author          : sungsuhan
 * date            :2022-05-18
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-18           sungsuhan      최초 생성
 **/
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="files")
public class File {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.AUTO) private long fileId;
    @Column private String fileName;
    @Column private String uuid;
    @Column private String folderPath;

    @OneToMany(mappedBy = "file")
    List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "file")
    List<Closet> closets = new ArrayList<>();



}
