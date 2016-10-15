package day0

/**
  * Created by Iryna Kharaborkina on 10/15/16. 
  *
  */
object SubtypePolymorphism extends App {

  println(PlusInt(5) plus PlusInt(6) value)

  def plus[A <: Plus[A]](a1: A, a2: A): A = a1 plus a2

  trait Plus[A] {
    def plus(a2: A): A
  }

  case class PlusInt(value: Int) extends Plus[PlusInt] {
    override def plus(a2: PlusInt): PlusInt = PlusInt(value + a2.value)
  }

}
