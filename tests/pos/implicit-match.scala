object `implicit-match` {
  object invariant {
    case class Box[T](value: T)
    implicit val box: Box[Int] = Box(0)
    inline def unbox <: Any = implicit match {
      case b: Box[t] => b.value
    }
    val i: Int = unbox
    val i2 = unbox
    val i3: Int = i2
  }

  object covariant {
    case class Box[+T](value: T)
    implicit val box: Box[Int] = Box(0)
    inline def unbox <: Any = implicit match {
      case b: Box[t] => b.value
    }
    val i: Int = unbox
    val i2 = unbox
    val i3: Int = i2
  }

  object contravariant {
    case class TrashCan[-T](trash: T => Unit)
    implicit val trashCan: TrashCan[Int] = TrashCan { i => ; }
    inline def trash <: Nothing => Unit = implicit match {
      case c: TrashCan[t] => c.trash
    }
    val t1: Int => Unit = trash
    val t2 = trash
    val t3: Int => Unit = t2
  }
}
