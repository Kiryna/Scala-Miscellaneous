package day0

/**
  * Created by Iryna Kharaborkina on 10/15/16. 
  *
  */
object AdHocPolymorphism extends App {
  println(plus(1, 6))
  println(plus("Hello", "world"))

  def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)

  object Plus {
    implicit val intPlus: Plus[Int] = new Plus[Int] {
      def plus(a1: Int, a2: Int): Int = a1 + a2
    }

    implicit val strPlus: Plus[String] = new Plus[String] {
      def plus(a1: String, a2: String): String = a1 + " " + a2
    }
  }

  trait Plus[A] {
    def plus(a1: A, a2: A): A
  }
}
