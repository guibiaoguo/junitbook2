package com.manning.junitbook.ch07.mocks.account;

import mockit.*;
import org.junit.Test;

import java.io.IOException;

import static mockit.Deencapsulation.*;
import static org.junit.Assert.*;

/**
 * Created by shentong on 2017/1/4.
 */
public class TestAccountServiceJmockit {

    @Mocked
    Account account;

    @Mocked
    private AccountManager mockAccountManager;

    private AccountService accountService = new AccountService();


    /**
     * 非局部模拟Expectations类定义
     */
    @Test
    public void testMockNormalMethod1() {
        new Expectations() {
            {
                account.hello("z3");
                returns("hello l4", "hello w5");
                account.hello("张三");
                result="hello 李四";
            }
        };

        assertEquals("hello l4", account.hello("z3"));
        assertEquals("hello w5", account.hello("z3"));
        assertEquals("hello 李四", account.hello("张三"));

        try {
            account.hello("z3");
        } catch (Throwable e) {
            System.out.println("第三次调用hello(\"z3\")会抛出异常");
        }
        try {
            account.show();
        } catch (Throwable e) {
            System.out.println("调用没有在Expectations块中定义的方法show()会抛出异常");
        }
    }

    /**
     * 非局部模拟 NonStrictExpectations类定义
     */
    @Test
    public void testMockNormalMethod2() {
        new NonStrictExpectations() {
            {
                account.hello("z3");
                returns("hello l4", "hello w5");
            }
        };

        assertEquals("hello l4", account.hello("z3"));
        assertEquals("hello w5", account.hello("z3"));
        assertEquals("hello w5", account.hello("z3"));// 会返回在NonStrictExpectations块中定义的最后一个返回值
        account.show();

        new Verifications() {
            {
                account.hello("z3");
                times = 3;
                account.show();
                times = 1;
            }
        };
    }

    /**
     * 局部模拟
     * @throws IOException
     */
    @Test
    public void testMockNormalMethod() throws IOException {
        final Account obj = new Account();//也可以不用@Mocked标注，但需要final关键字
        new NonStrictExpectations(obj) {
            {
                obj.hello("z3");
                result = "hello l4";
            }
        };

        assertEquals("hello l4", obj.hello("z3"));
        assertEquals("hello 张三", obj.hello("张三"));

        new Verifications() {
            {
                obj.hello("z3");
                times = 1;
                obj.hello("张三");
                times = 1;
            }
        };
    }

    /**
     * 模拟静态方法
     */
    @Test
    public void testMockStaticMethod() {
        new NonStrictExpectations(Account.class) {
            {
                Account.getDouble(1);
                result = 3;
            }
        };

        assertEquals(3, Account.getDouble(1));

        new Verifications() {
            {
                Account.getDouble(1);
                times = 1;
            }
        };
    }

    /**
     *  模拟私有方法
     * @throws Exception
     */
    @Test
    public void testMockPrivateMethod() throws Exception {
        final Account obj = new Account();
        new NonStrictExpectations(obj) {
            {
                invoke(obj, "multiply3", 1);
                result = 4;
            }
        };

        String actual = obj.getTripleString(1);
        assertEquals("4", actual);

        new Verifications() {
            {
                invoke(obj, "multiply3", 1);
                times = 1;
            }
        };
    }

    /**
     * 设置私有属性的值
     * @throws IOException
     */
    @Test
    public void testMockPrivateProperty() throws IOException {
        final Account obj = new Account();
        new NonStrictExpectations(obj) {
            {
                setField(obj, "name", "name has bean change!");
            }
        };

        assertEquals("name has bean change!", obj.getName());
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testMockNormalMethodContent() throws IOException {
        final Account obj = new Account();
        new NonStrictExpectations(obj) {
            {
                new MockUp<Account>() {
                    @Mock
                    public int getTriple(int i) {
                        return i * 30;
                    }
                };
            }
        };

        assertEquals(30, obj.getTriple(1));
        assertEquals(60, obj.getTriple(2));
    }

    @Test
    public void testDynamicMockStaticMethodContent() throws IOException {
        final Account obj = new Account();
        new NonStrictExpectations(obj) {
            {
                new MockUp<Account>() {
                    @Mock
                    public int getDouble(int i) {
                        return i * 20;
                    }
                };
            }
        };
        assertEquals(20, Account.getDouble(1));
        assertEquals(40, Account.getDouble(2));
    }
}

