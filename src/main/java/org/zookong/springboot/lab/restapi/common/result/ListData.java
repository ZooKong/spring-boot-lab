package org.zookong.springboot.lab.restapi.common.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListData<T> {

    private int page;
    private long totalCount;
    private int totalPage;
    private List<T> rows;

}