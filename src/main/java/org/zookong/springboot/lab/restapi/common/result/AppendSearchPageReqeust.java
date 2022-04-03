package org.zookong.springboot.lab.restapi.common.result;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppendSearchPageReqeust {

    private String isAll = "N";
    private int pageSize = 10;
    private Long firstKeyNum = -1l;
    private String keyName;

    public void setIsAll(String isAll) {
        if(isAll.equals("Y")){
            setPageSizeZero();
        }
        this.isAll = isAll;
    }

    public void setPageSize(int pageSize) {
        if(this.isAll.equals("Y")){
            setPageSizeZero();
        }else{
            if(pageSize!=0) this.pageSize = pageSize;
        }
    }

    public void setPageSizeZero() {
        this.pageSize = 0;
    }
}