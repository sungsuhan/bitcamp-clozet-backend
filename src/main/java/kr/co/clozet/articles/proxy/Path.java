package kr.co.clozet.articles.proxy;

/**
 * packageName:kr.co.clozet.articles.proxy
 * fileName        :Path.java
 * author          : sungsuhan
 * date            :2022-07-11
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-07-11           sungsuhan      최초 생성
 **/
public enum Path {
    UPLOAD_PATH;
    @Override
    public String toString() {
        String result = "";
        switch(this) {
            case UPLOAD_PATH:
                result = "C:\\";
                break;
        }

        return result;
    }
}