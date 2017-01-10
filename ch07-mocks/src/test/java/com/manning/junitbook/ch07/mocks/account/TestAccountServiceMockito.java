package com.manning.junitbook.ch07.mocks.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by shentong on 2017/1/4.
 */
public class TestAccountServiceMockito {

    private AccountManager mockAccountManager;

    @Before
    public void setUp()
    {
        mockAccountManager = mock(AccountManager.class );
    }


    @Test
    public void testTransferOk()
    {
        Account senderAccount = new Account( "1", 200 );
        Account beneficiaryAccount = new Account( "2", 100 );

        when( mockAccountManager.findAccountForUser( "1" ) ).thenReturn( senderAccount );
        when( mockAccountManager.findAccountForUser( "2" ) ).thenReturn( beneficiaryAccount );
        AccountService accountService = new AccountService();
        accountService.setAccountManager( mockAccountManager );
        accountService.transfer( "1", "2", 50 );

        assertEquals( 150, senderAccount.getBalance() );
        assertEquals( 150, beneficiaryAccount.getBalance() );
        verify( mockAccountManager);
    }


    @Test
    public void testTwoTransferOk()
    {
        Account objOther= mock(Account.class);
        Account objCn= mock(Account.class);

        when(objOther.hello("z3")).thenReturn("hello l4");
        when(objCn.hello("z3")).thenReturn("hello 张三");

        String other= objOther.hello("z3");
        assertEquals("hello l4", other);
        String cn= objCn.hello("z3");
        assertEquals("hello 张三", cn);

        InOrder inOrder= inOrder(objOther, objCn);
        inOrder.verify(objOther).hello("z3");
        inOrder.verify(objCn).hello("z3");
    }

    @Test
    public void testSkipExpect() {
        Account obj= mock(Account.class);

        assertEquals(null, obj.hello("z3"));
        obj.show();

        verify(obj).hello("z3");
        verify(obj).show();
    }

    @Ignore
    @Test
    public void testCallRealMethod () {
        Account obj= mock(Account.class);

        doCallRealMethod().when(obj).hello("z3");

        assertEquals("hello z3",obj.hello("z3"));
        assertEquals(null,obj.hello("l4"));
        obj.show();

        verify(obj).hello("z3");
        verify(obj).hello("l4");
        verify(obj).show();
    }

    @Test
    public void testSpy() {
        Account obj= spy(new Account());

        doNothing().when(obj).show();

        assertEquals("hello z3",obj.hello("z3"));
        assertEquals("hello z4",obj.hello("z4"));
        obj.show();

        verify(obj).hello("z3");
        verify(obj).show();
    }

    @Test
    public void testSpy2() {
        Account obj= spy(new Account());

        when(obj.hello("z3")).thenReturn("hello l4");

        assertEquals("hello l4",obj.hello("z3"));

        verify(obj).hello("z3");
    }

    @Test
    public void testSpy3() {
        Account obj= spy(new Account());

        doReturn("hello l4").when(obj).hello("z3");

        assertEquals("hello l4",obj.hello("z3"));

        verify(obj).hello("z3");
    }

    @After
    public void tearDown()
    {
//        verify( mockAccountManager);
    }
}
