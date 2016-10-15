package day0

/**
  * Created by Iryna Kharaborkina on 9/11/16. 
  *
  */
object SumFunction extends App {
  def sum[A: Monoid](seq: Seq[A]):A = {
    val m = implicitly[Monoid[A]]
    seq.foldLeft(m.mzero)(m.mappend)
  }

  val multiMonoid = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a * b
    def mzero: Int = 1
  }

  println("With IntMonoid: " + sum(1 to 4))
  println("With multiMonoid: " + sum(1 to 4)(multiMonoid))
  println("With StringMonoid: " + sum(List("Hello, ", "monoid", "!")))

  object Monoid {

    implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
      def mappend(a: Int, b: Int): Int = a + b
      def mzero: Int = 0
    }

    implicit val StringMonoid: Monoid[String] = new Monoid[String] {
      def mappend(a: String, b: String): String = a + b
      def mzero: String = ""
    }
  }


  trait Monoid[A] {
    def mappend(a: A, b: A): A
    def mzero: A
  }

}
