package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def isFirstInRow: Boolean = {
      c == 0
    }

    def isLastInRow: Boolean = {
      c == r
    }

    def isFirstRow: Boolean = {
      r == 0
    }

    if (isFirstRow) return 1
    if (isFirstInRow) return 1
    if (isLastInRow) return 1

    pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

    @tailrec
    def balance(chars: List[Char], nrOfOpenParenthesis: Int): Boolean = {
      if (nrOfOpenParenthesis < 0) {
        return false
      }

      if (chars.isEmpty) {
        if (nrOfOpenParenthesis == 0) return true else return false
      }

      if (chars.head == ')') {
        balance(chars.tail, nrOfOpenParenthesis - 1)
      }
      else if (chars.head == '(') {
        balance(chars.tail, nrOfOpenParenthesis + 1)
      }
      else {
        balance(chars.tail, nrOfOpenParenthesis)
      }
    }

    balance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}
