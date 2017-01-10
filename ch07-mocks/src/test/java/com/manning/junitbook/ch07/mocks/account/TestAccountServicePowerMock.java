package com.manning.junitbook.ch07.mocks.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.Assert.*;
/**
 * Created by shentong on 2017/1/4.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Account.class})
public class TestAccountServicePowerMock {

    private AccountManager mockAccountManager;

    @Before
    public void setUp()
    {
        mockAccountManager = mock(AccountManager.class );
    }

    @Test
    public void testTransferOk()
    {
        Account senderAccount = new Account( "1", 400 );
        Account beneficiaryAccount = new Account( "2", 300 );
        when( mockAccountManager.findAccountForUser( "1" ) ).thenReturn( senderAccount );
        when( mockAccountManager.findAccountForUser( "2" ) ).thenReturn( beneficiaryAccount );
        AccountService accountService = new AccountService();
        accountService.setAccountManager( mockAccountManager );
        accountService.transfer( "1", "2", 50 );

        assertEquals( 350, senderAccount.getBalance() );
        assertEquals( 350, beneficiaryAccount.getBalance() );
    }

    /**
     * 模拟静态方法
     */
    @Test
    public void testMockStaticMethod() {
        mockStatic(Account.class);
        when(Account.getDouble(1)).thenReturn(3);

        int actual = Account.getDouble(1);
        assertEquals(3, actual);

        verifyStatic();
        Account.getDouble(1);
    }

    /**
     * 模拟私有方法(doCallRealMethod方式)
     * @throws Exception
     */
    @Test
    public void testMockPrivateMethod() throws Exception {
        Account obj = mock(Account.class);

        when(obj, "multiply3", 1).thenReturn(4);
        doCallRealMethod().when(obj).getTripleString(1);

        String actual = obj.getTripleString(1);
        assertEquals("4", actual);

        verifyPrivate(obj).invoke("multiply3", 1);
    }

    /**
     *  模拟私有方法(spy方式)
     * @throws Exception
     */
    @Test
    public void testMockPrivateMethod2() throws Exception {
        Account obj = spy(new Account());
        when(obj, "multiply3", 1).thenReturn(4);

        String actual = obj.getTripleString(1);
        assertEquals("4", actual);

        verifyPrivate(obj).invoke("multiply3", 1);
    }

    /**
     * 模拟构造方法
     * @throws Exception
     */
    @Test
    public void testStructureWhenPathDoesntExist() throws Exception {
        final String directoryPath = "mocked path";

        File directoryMock = mock(File.class);

        whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock);
        when(directoryMock.exists()).thenReturn(true);

        File file=new File(directoryPath);
        assertTrue(file.exists());

        verifyNew(File.class).withArguments(directoryPath);
        verifyPrivate(directoryMock).invoke("exists");
    }
}
