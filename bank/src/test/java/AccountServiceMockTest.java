import ie.stephen.dao.AccountDaoImplementation;
import ie.stephen.model.Account;
import ie.stephen.model.AccountImplementation;
import ie.stephen.service.AccountServiceImplementation;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class AccountServiceMockTest {

    @Mock
    private AccountDaoImplementation accountDao;

    @InjectMocks
    private AccountServiceImplementation accountService;

    private static final ByteArrayOutputStream outContext = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    static void setup() {
        System.setOut(new PrintStream(outContext));
    }

    @Test
    void testNewCustomer() {
        accountService.createAccount(1, 0);
        verify(accountDao, times(1)).newAccount(0);
        verify(accountDao, times(1)).newCustomerAccount(1, 0);
    }

    @Test
    void testWithdraw() {
        Account toReturn = new AccountImplementation(1, 3000, 0);
        when(accountDao.findAccount(1)).thenReturn(toReturn);
        accountService.withdraw(1, 3000);
        Assert.assertEquals(0, toReturn.getBalance(), 1);
    }

    @Test
    void testDeposit() {
        Account toReturn = new AccountImplementation(1, 3000, 0);
        when(accountDao.findAccount(1)).thenReturn(toReturn);
        accountService.deposit(1, 3000);
        Assert.assertEquals(6000, toReturn.getBalance(), 1);
    }

    @AfterAll
    static void tearDown() {
        System.setOut(originalOut);
    }
}
