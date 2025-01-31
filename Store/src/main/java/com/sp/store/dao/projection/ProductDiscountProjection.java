package com.sp.store.dao.projection;

import java.util.Date;

public interface ProductDiscountProjection {
    String getName();

    Double getPrice();

    Integer getQuantity();

    Double getDiscountPercentage();

    Date getStartDate();

    Date getEndDate();
}
