package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s1000 = singletonSet(1000)
    val sNegative1000 = singletonSet(-1000)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains set with elements from both sets") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection returns set with elements that are present in both sets") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersection 1")
      assert(!contains(s, 2), "Intersection 2")
      assert(!contains(s, 3), "Intersection 3")
    }
  }

  test("diff returns empty set if both sets contain same elements") {
    new TestSets {
      val s = diff(s1, s1)
      assert(!contains(s, 1), "diff 1")
      assert(!contains(s, 2), "diff 2")
    }
  }

  test("diff returns set with elements that are not in second set") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "diff 1")
      assert(!contains(s, 2), "diff 2")
    }
  }

  test("filter returns empty set if no elements match the predicate") {
    new TestSets {
      val s = filter(s1, x => x > 1)
      assert(!contains(s, 1), "Filter 1")
      assert(!contains(s, 2), "Filter 2")
      assert(!contains(s, 3), "Filter 3")
    }
  }

  test("filter returns non empty set if some elements match the predicate") {
    new TestSets {
      val s = filter(s3, x => x > 1)
      assert(!contains(s, 1), "Filter 1")
      assert(!contains(s, 2), "Filter 2")
      assert(contains(s, 3), "Filter 3")
    }
  }

  test("forall positive numbers") {
    new TestSets {
      val s = union(s1, union(s2,s3))
      assert(forall(s, x => x > 0), "forall positive")
    }
  }

  test("forall not all numbers bigger than 1") {
    new TestSets {
      val s = union(s2, union(s2,s1))
      assert(!forall(s, x => x > 1), "forall greater than 1")
    }
  }

  test("forall upper limit") {
    new TestSets {
      assert(forall(s1000, x => x > 1), "forall empty limit")
    }
  }

  test("forall lower limit") {
    new TestSets {
      assert(forall(sNegative1000, x => x < -1), "forall empty limit")
    }
  }

  test("forall for empty set") {
    new TestSets {
      assert(forall(intersect(s1,s2), x => x > 1), "forall empty set")
    }
  }

  test("exists for empty set") {
    new TestSets {
      assert(!exists(intersect(s1,s2), x => x > 1), "exists empty set")
    }
  }

  test("exists at least a number greater than 1") {
    new TestSets {
      assert(exists(union(s1,s2), x => x > 1), "exists greater 1")
    }
  }

  test("map multiplying by 3") {
    new TestSets {
      assert(contains(map(s3, x => x * 3), 9), "map multiplying by 3 set s3")
    }
  }
}
