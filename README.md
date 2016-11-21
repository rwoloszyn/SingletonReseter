[![Build Status](https://travis-ci.org/rwoloszyn/SingletonReseter.svg?branch=master)](https://travis-ci.org/rwoloszyn/SingletonReseter)
[![Documentation Status](https://readthedocs.org/projects/singletonreseter/badge/?version=latest)](http://singletonreseter.readthedocs.io/en/latest/?badge=latest)

==========================================================================

# SingletonReseter
Simple Java library to reset instances of singletons classes.
Library it's mostly used for reset instances of singletons during unit tests.


# Usage
 Usage is simple as calling one static method:

```java
 Singleton.reset(SimpleSingleton.class, "sInstance");
```


 **Example usage with JUnit:**

```java
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
```

# Contribution

This library is so simple that I do not know if anyone want to add anything. However you are welcome.
If you have any idea how to improve or add something useful just make a pull request.
I will check it and merge.

# Licence

Apache License Version 2.0, January 2004

# Author
@2016 Rafal Woloszyn (rafal@debugduckdesign.com)