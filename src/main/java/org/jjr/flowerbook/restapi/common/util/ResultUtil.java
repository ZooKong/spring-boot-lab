package org.jjr.flowerbook.restapi.common.util;

import org.jjr.flowerbook.core.common.domain.PagingParam;
import org.jjr.flowerbook.restapi.common.result.*;

import java.util.List;

/**
 * API 결과 생성 유틸
 *
 * @author Created by 유주빈 on 2021.06.06
 */
public class ResultUtil {

    public static final String SUCCESS = "success";

    public static ListResult success(List rows, Number totalCount, PagingParam pagingParam) {
        ListResult result = new ListResult();
        result.setCode("0");
        result.setResult(SUCCESS);
        
        ListData listData  = new ListData();
        listData.setTotalCount(totalCount.longValue());
        listData.setPage(pagingParam.getPage());
        listData.setTotalPage((pagingParam.getPageSize() == 0) ? 0 : (int) Math.ceil(totalCount.doubleValue() / pagingParam.getPageSize()));
        listData.setRows(rows);

        result.setData(listData);
        return result;
    }

    public static SingleResult success(Object data) {
        SingleResult result = new SingleResult();
        result.setCode("0");
        result.setResult(SUCCESS);
        result.setData(data);
        return result;
    }

    public static PkResult success(String pkName, Object pkValue) {
        PkResult result = new PkResult();
        result.setCode("0");
        result.setResult(SUCCESS);
        result.setPkName(pkName);
        result.setPkValue(pkValue);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode("0");
        result.setResult(SUCCESS);
        return result;
    }

}