/* 
 * ========================================================================
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ========================================================================
 */

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test-case for the calculator program.
 * 
 * @version $Id$
 */
public class CalculatorTest
{

    @Test
    public void add()
    {
        Calculator calculator = new Calculator();
        double result = calculator.add( 1, 1 );
        assertEquals( 2, result, 0 );
    }

    @Test
    public void testAssertArrayEquals() {
        int[] a = new int[]{0,1};
        int[] b = new int[]{0,1};
        assertArrayEquals("a=b",a,b);
    }

    @Test
    public void testAssertEqual() {
        String a = "test1";
        String b = "test1";
        assertEquals("a==b",a,b);
    }

    @Test
    public void testAssertSame() {
        Calculator a = new Calculator();
        Calculator b = a;
        assertSame("a=b",a,b);
    }

    @Test
    public void testAssertTrue() {
        boolean a = true;
        assertTrue("a==true",a);
    }

    @Test
    public void testAssertNotNull() {
        String a = "aa";
        assertNotNull("a not null",a);
    }
}
