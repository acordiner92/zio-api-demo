import zio.json.*

case class User(
    firstName: String,
    lastName: String,
    age: Int
)

object User {
  implicit val decoder: JsonDecoder[User] =
    DeriveJsonDecoder.gen[User]

  implicit val encoder: JsonEncoder[User] =
    DeriveJsonEncoder.gen[User]
}
