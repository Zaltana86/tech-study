package com.ivx.strategy.strategyinterface;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/11/2 10:08
 * @apiNote 查询公司名的策略
*/

public interface ICompanyNameStrategy {
    String getCompanyName();
    int supportStrategy();
}
