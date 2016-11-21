/**
 * Copyright  2016 Rafal Woloszyn(rafal@debugduckdesign.com)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.debugduck.design.singletonreseter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

@RunWith(JUnit4.class)
public class SingletonTest {

    @Test(expected = RuntimeException.class)
    public void testWrongInstanceField() {
        SimpleSingleton singleton = SimpleSingleton.getInstance();
        singleton.setCount(123213);
        singleton.setSomeString("SomeString");
        Singleton.reset(SimpleSingleton.class, "WrongFieldName");
    }

    @Test
    public void testCorrectInstanceField() {
        String testString = "AwsomeString";
        String testString2 = "AmazingString";

        SimpleSingleton singleton = SimpleSingleton.getInstance();
        singleton.setSomeString(testString);

        SimpleSingleton singleton1 = SimpleSingleton.getInstance();
        assertEquals(testString, singleton1.getSomeString());

        //Reset singleton with library
        Singleton.reset(SimpleSingleton.class, "sInstance");
        SimpleSingleton singleton2 = SimpleSingleton.getInstance();
        assertNull(singleton2.getSomeString());

        singleton2.setSomeString(testString2);

        SimpleSingleton singleton3 = SimpleSingleton.getInstance();

        assertEquals(testString2, singleton3.getSomeString());
        assertNotSame(singleton1.getSomeString(), singleton3.getSomeString());

    }


}