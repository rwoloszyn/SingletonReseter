/**
 * Copyright  2016 Rafal Woloszyn(rafal@debugduckdesign.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.debugduck.design.singletonreseter;

import java.lang.reflect.Field;

/**
 * Simple one method class which uses Java reflection mechanism to get
 * Singleton field and null it, so Singleton instance could be tested and created
 * as new object.
 */
public class Singleton {

    /**
     * Reset singleton instance object
     *
     * <blockquote><pre><code>
     *  {
     *  ...
     *      SimpleSingleton singleton = SimpleSingleton.getInstance();
     *      singleton.setCount(123213);
     *      singleton.setSomeString("SomeString");
     *
     *      <b>Singleton.reset(SimpleSingleton.class, "sInstance");</b>
     *  }
     *
     *
     *  <b>Example with JUnit:</b>
     *  &#064;RunWith(JUnit4.class)
     *   public class SingletonReseterTest {
     *
     *      SimpleSingleton singleton;
     *      String globalTestString = "GlobalTestString";
     *
     *      &#064;Before
     *      public void setup() {
     *          //Initialize singleton for each test. Without resetting in teardown
     *          //method this will be the same object.
     *          singleton = SimpleSingleton.getInstance();
     *      }

     *      &#064;After
     *      public void teardown() {
     *          //After each test call singleton reset, so it's instance is created
     *          //every time for each separate test.
     *          <b>Singleton.reset(SimpleSingleton.class, "sInstance");</b>
     *      }
     *
     *      &#064;Test
     *      public void simpleValueTest() {
     *          String test = "SomeAwesomeStringTest";
     *          singleton.setSomeString(test);
     *          assertEquals(test, SimpleSingleton.getInstance().getSomeString());

     *          //After equal test pass, we set another value, which should be the same
     *          //in other tests as Singleton is designed for, but teardown will be called
     *          //after this test executes, so our singleton will be nilled and created again.
     *          singleton.setSomeString(globalTestString);
     *      }
     *
     *      &#064;Test
     *      public void simpleValueTest2() {
     *          assertNotSame(globalTestString, SimpleSingleton.getInstance().getSomeString());
     *          assertNull(SimpleSingleton.getInstance().getSomeString());
     *      }
     *
     *  }
     *
     * </code></pre></blockquote>
     *
     *
     * @param clazz     singleton class to reset
     * @param fieldName Singleton instance field name
     * @throws RuntimeException when cannot find singleton instance field or class is not
     * not singleton
     */
    public static void reset(Class clazz, String fieldName) throws RuntimeException {
        Field instanceField;
        try {
            instanceField = clazz.getDeclaredField(fieldName);
            instanceField.setAccessible(true);
            instanceField.set(null, null);
        } catch (Exception e) {
            System.out.println("Cannot reset singleton instanceField ex: " + e.toString());
            throw new RuntimeException();
        }
    }
}
