package kr.co.clozet.common.dataStructure;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * packageName:kr.co.clozet.common.dataStructure
 * fileName        :Box2.java
 * author          : sungsuhan
 * date            :2022-07-11
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-07-11           sungsuhan      최초 생성
 **/
@Component
public class Box2<T> {
    private ArrayList<T> list;
    public void add(T item) {list.add(item);}
}
