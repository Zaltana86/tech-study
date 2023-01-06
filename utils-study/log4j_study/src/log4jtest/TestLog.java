package log4jtest;

/*
Author: Inklo
Time: 2022/4/2 11:52
Target:
使用log4f进行日志记录
*/


import org.apache.log4j.Logger;

public class TestLog {
    // 里面的Class类用来追踪记录日志产生的类最好写本类名
    private static final Logger logger = Logger.getLogger(TestLog.class);

    // public static void main(String[] args) {
    //     System.out.println(System.getProperty("user.dir"));
    // }

    public static void main(String[] args) {
        // 日志等级不够不会被打印
        logger.debug("准备钱");
        logger.info("去商场购物了!");
        logger.info("准备买一本书");
        try {
            int a = 1 / 0;
        } catch (
                Exception e) {
            // 可以传异常对象
            logger.error("钱不够", e);
        }
        logger.info("开始回家");
        logger.fatal("被打劫了");
    }
}
