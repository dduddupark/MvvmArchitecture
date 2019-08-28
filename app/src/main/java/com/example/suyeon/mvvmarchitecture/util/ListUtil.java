package com.example.suyeon.mvvmarchitecture.util;

import java.util.List;

/**
 * ListUtil
 */
public class ListUtil {

    /**
     * 리스트 빈값 확인
     *
     * @param list 확인할 리스트
     * @return 빈값 확인 결과
     */
    public static boolean isEmpty(List list) {
        boolean isEmpty = false;

        if (list == null || list.size() == 0) {
            isEmpty = true;
        }

        return isEmpty;
    }

    /**
     * 리스트 수 가져오기
     *
     * @param list 리스트
     * @return 리스트 수
     */
    public static int getListCount(List list) {
        int count = 0;

        if (!isEmpty(list)) {
            count = list.size();
        }


        return count;
    }
}
