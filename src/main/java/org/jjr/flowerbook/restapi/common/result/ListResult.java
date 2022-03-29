package org.jjr.flowerbook.restapi.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResult<T> {

    private String result;
    private String code;
    private ListData<T> data;

}