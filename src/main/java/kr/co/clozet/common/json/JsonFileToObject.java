package kr.co.clozet.common.json;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.clozet.clothes.domains.Clothes;
import org.hibernate.mapping.Value;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * packageName:kr.co.clozet.json
 * fileName        :JsonFileToObject.java
 * author          : sungsuhan
 * date            :2022-06-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-07           sungsuhan      최초 생성
 **/
public class JsonFileToObject {
    public static void main(String[] args) throws Exception {

        URL path = JsonFileToObject.class.getResource("/clothes/1012188_F.json");

        File jsonFile = new File(path.getFile());

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> jsonMap = mapper.readValue(jsonFile, Map.class);
        Collection<Object> values = jsonMap.values(); // map(value 값) -> list
        List<Object> list = new ArrayList<>(values); // map(value 값) -> list

        System.out.println("JSON File --> List");
        System.out.println(list);

        System.out.println("JSON File --> Map");
        System.out.println(jsonMap);
    }

}
