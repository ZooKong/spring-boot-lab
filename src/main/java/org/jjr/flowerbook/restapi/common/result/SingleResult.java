package org.jjr.flowerbook.restapi.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> {

    private String result;
    private String code;
    private T data;

}