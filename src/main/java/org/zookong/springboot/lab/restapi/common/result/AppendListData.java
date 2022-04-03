package org.zookong.springboot.lab.restapi.common.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppendListData<T> {

    private long totalCount;
    private int totalPage;
    private String keyName;
    private long lastKeyNum;
    private List<T> rows;

}