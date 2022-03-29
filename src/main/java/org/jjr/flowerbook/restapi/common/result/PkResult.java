package org.jjr.flowerbook.restapi.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkResult {

    private String result;
    private String code;
    private String pkName;
    private Object pkValue;

}