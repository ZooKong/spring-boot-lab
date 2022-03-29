package org.jjr.flowerbook.core.common.util;


import java.util.*;

public class DateUtil {

    /**
     * 현재의 Unix Time을 얻는 메서드
     *
     * @return : 차이 시간을 반환
     * @author Created by 유주빈 on 2019.07.10
     */
    public static int getNowUnixTime() {

        Date date = new Date();
        long result = date.getTime() / 1000;

        return (int) result;
    }

}