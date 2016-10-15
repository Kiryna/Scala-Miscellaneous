package day0

/**
  * Created by Iryna Kharaborkina on 10/15/16. 
  *
  */
object ParametricPolymorphism extends App {
  def head[A](xs: List[A]): A = xs(0)

  case class Car(make: String)

  println(head(1 :: 2 :: Nil))

  println(head(Car("Civic") :: Car("CR-V") :: Nil))

}


