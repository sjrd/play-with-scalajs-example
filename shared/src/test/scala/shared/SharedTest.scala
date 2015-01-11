package shared

import utest._

object SharedTest extends TestSuite {

  def tests = TestSuite {
    'HelloWorld {
      assert(SharedMessages.itWorks == "It works!")
    }
  }
}
