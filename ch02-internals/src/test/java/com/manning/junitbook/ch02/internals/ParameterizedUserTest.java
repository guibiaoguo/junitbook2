package com.manning.junitbook.ch02.internals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by shentong on 2017/1/4.
 */
@RunWith(Parameterized.class)
public class ParameterizedUserTest {

    private User user;

    @Parameterized.Parameters
    public static Collection<User[]> getTestParameters() {
        Collection c = Arrays.asList(new User[][]{
                {new User(1,"zhangsan","admin")},
                {new User(2,"lisi","admin")}
        });
        return c;
    }

    public ParameterizedUserTest (User user) {
        this.user = user;
    }

    @Test
    public void testPassword() {
        assertEquals(user.getPassword(),"admin");
        assertTrue(user.getId() >0);
    }

}
