import ie.stephen.dao.AccountDao;
import ie.stephen.model.Account;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
public class AccountDaoMockTest {

    @Autowired
    AccountDao accountDao;

    @Test
    public void testFindAccount() {
        Account returned = accountDao.findAccount(1);
        Assert.assertEquals(returned.getAccountId(), 1);
    }
}
