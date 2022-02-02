import zio.*

trait UserService {
  def create(
      firstName: String,
      lastName: String,
      age: Int
  ): ZIO[Console, Throwable, User]
}

class UserServiceLive(console: Console) extends UserService {
  def create(
      firstName: String,
      lastName: String,
      age: Int
  ) =
    console.printLine("We are creating a user")
    ZIO.succeed(User("John", "Smith", 29))
}

object UserServiceLive {
  val layer: URLayer[Console, UserService] =
    (UserServiceLive(_)).toLayer[UserService]
}

object UserService {
  def create(
      firstName: String,
      lastName: String,
      age: Int
  ): ZIO[UserService with Console, Throwable, User] =
    ZIO.serviceWithZIO[UserService](_.create(firstName, lastName, age))
}
