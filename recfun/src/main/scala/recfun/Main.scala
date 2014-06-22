package recfun

import scala.annotation.tailrec

object Main {
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

    if (isFirstRow || isFirstInRow || isLastInRow) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

    @tailrec
    def balance(chars: List[Char], nrOfOpenParenthesis: Int): Boolean = {
      if (nrOfOpenParenthesis < 0) {
        false
      } else {
        if (chars.isEmpty) {
          nrOfOpenParenthesis == 0
        } else {
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
      }
    }

    balance(chars, 0)
  }



  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    if (coins.isEmpty) {
      0
    } else {
      def partition(n: Int, max: Int, coins: List[Int]): Int = {

        def headOfCoins: Int = {
          if (coins.nonEmpty) coins.head else 0
        }

        def tailOfCoins: List[Int] = {
          if (coins.nonEmpty) coins.tail else List()
        }

        if (max == 0)
          0
        else if (n == 0)
          1
        else if (n < 0)
          0
        else partition(n, headOfCoins, tailOfCoins) + partition(n - max, max, coins)

      }

      val coinsList = coins.filter(coin => coin <= money).sorted(Ordering[Int].reverse)
      partition(money, coinsList.head, coinsList.tail)
    }
  }
}
