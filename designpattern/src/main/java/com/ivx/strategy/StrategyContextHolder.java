package com.ivx.strategy;


import com.ivx.strategy.strategyimpl.ProductionOneStrategy;
import com.ivx.strategy.strategyimpl.ProductionTwoStrategy;
import com.ivx.strategy.strategyinterface.ICompanyNameStrategy;

import java.util.*;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/11/2 10:43
 */

public class StrategyContextHolder {
    static List<ICompanyNameStrategy> strategies = Arrays.asList(
            new ProductionOneStrategy(),
            new ProductionTwoStrategy()
    );

    public static ICompanyNameStrategy getStrategy(int type) {
        for (ICompanyNameStrategy companyNameStrategy : strategies) {
            if (companyNameStrategy.supportStrategy() == type) {
                return companyNameStrategy;
            }
        }
        throw new RuntimeException("参数错误，不支持的策略");
    }
}
