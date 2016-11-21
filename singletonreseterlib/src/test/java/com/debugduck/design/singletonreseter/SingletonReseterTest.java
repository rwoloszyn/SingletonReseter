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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

@RunWith(JUnit4.class)
public class SingletonReseterTest {

    SimpleSingleton singleton;
    String globalTestString = "GlobalTestString";

    @Before
    public void setup() {
        //Initialize singleton for each test. Without resetting in teardown
        //method this will be the same object.
        singleton = SimpleSingleton.getInstance();
    }

    @After
    public void teardown() {
        //After each test call singleton reset, so it's instance is created
        //every time for each separate test.
        Singleton.reset(SimpleSingleton.class, "sInstance");
    }


    @Test
    public void simpleValueTest() {
        String test = "SomeAwesomeStringTest";
        singleton.setSomeString(test);
        assertEquals(test, SimpleSingleton.getInstance().getSomeString());

        //After equal test pass, we set another value, which should be the same
        //in other tests as Singleton is designed for, but teardown will be called
        //after this test executes, so our singleton will be nilled and created again.
        singleton.setSomeString(globalTestString);
    }

    @Test
    public void simpleValueTest2() {
        assertNotSame(globalTestString, SimpleSingleton.getInstance().getSomeString());
        assertNull(SimpleSingleton.getInstance().getSomeString());
    }

}
