package com.miaofan.util;


import cn.hutool.core.convert.NumberChineseFormatter;
import com.miaofan.entity.BillItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/7/29 14:40
 * Target:
 */

public class BillUtil {
    public static Double getTotalPrice(List<BillItem> billItemList) {
        double sum  = billItemList.stream().mapToDouble(BillItem::getSingleTotalPrice).sum();
        BigDecimal bigDecimal = new BigDecimal(sum);
        return bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static String getpPinyinPrice(List<BillItem> billItemList) {
        double sum = billItemList.stream().mapToDouble(BillItem::getSingleTotalPrice).sum();
        return  NumberChineseFormatter.format(sum, true);

    }
}
