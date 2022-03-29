package org.jjr.flowerbook.core.common.domain;

import org.jjr.flowerbook.core.common.enums.OrderType;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class PagingParam {


    private int page = 1;
    private int pageSize = 10;

    private OrderType orderType = OrderType.DESC;
    private String orderKey;

    private Set<String> limitedOrderKeys = new HashSet<>();

    public int getPage() {
        if (page < 1) {
            return 1;
        } else {
            return page;
        }
    }

    public void setPage(int page) {
        this.page = (page < 1) ? 1 : page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = (pageSize < 0) ? this.pageSize : pageSize;
    }

    public void setPageSizeZero() {
        this.pageSize = 0;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public boolean isSatisfiedOrder() {

        // null, 빈문자열 혹은 공백으로 이루어진 키인가?
        if (!StringUtils.hasText(this.orderKey))
            return false;

        if (this.orderType == null)
            return false;

        // 제한 order key 범위내에 존재하는가?
        if (!this.limitedOrderKeys.contains(this.orderKey))
            return false;

        return true;
    }

    public void addLimitedOrderKey(String... orderKeys) {
        for (String orderKey : orderKeys) {
            if (!StringUtils.hasText(orderKey))
                continue;
            this.limitedOrderKeys.add(orderKey);
        }
    }

}