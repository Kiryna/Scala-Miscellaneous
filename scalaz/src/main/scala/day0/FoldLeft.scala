package day0

import scala.collection.immutable

/**
  * Created by Iryna Kharaborkina on 9/12/16. 
  *
  */
object FoldLeft extends App {
  println("With IntMonoid: " + sum(1 to 4))
  println("With IntMonoid: " + plus(1, 4))
  println("With IntMonoid: " + (1 |+| 4))

  println("With StringMonoid: " + sum(List("Hello, ", "monoid", "!")))
  println("With StringMonoid: " + plus("Hello, ", "monoid!"))
  println("With StringMonoid: " + ("Hello, " |+| "monoid!"))


  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }

  def plus[A: Monoid](a: A, b: A): A = implicitly[Monoid[A]].mappend(a, b)

  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    val F: Monoid[A] = implicitly[Monoid[A]]
    override val value: A = a
  }

  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
  }

  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
    }

    implicit val FoldLeftRange: FoldLeft[immutable.IndexedSeq] = new FoldLeft[immutable.IndexedSeq] {
      def foldLeft[A, B](xs: immutable.IndexedSeq[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
    }
  }

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

  trait MonoidOp[A] {
    val F: Monoid[A]
    val value: A
    def |+|(a2: A) = F.mappend(value, a2)
  }

}
