package com.ivx.strategy.strategyimpl;


import com.ivx.strategy.CompanyType;
import com.ivx.strategy.strategyinterface.ICompanyNameStrategy;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/11/2 10:40
 * @apiNote
*/

public class ProductionTwoStrategy implements ICompanyNameStrategy {
    @Override
    public String getCompanyName() {
        return "策略2";
    }

    @Override
    public int supportStrategy() {
        return CompanyType.PRODUCTION_TWO;
    }
}
