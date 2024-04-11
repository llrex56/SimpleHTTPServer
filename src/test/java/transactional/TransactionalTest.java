package transactional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author luozhenhong
 * @version 1.0
 * 2022/9/8 11:15
 */
public class TransactionalTest {

    @Resource
    private TransactionTemplate transactionTemplate;

    public void transactional1() {
        transactionTemplate.executeWithoutResult(status -> {
            // do biz
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void transactional2() {
        // do biz
    }
}
