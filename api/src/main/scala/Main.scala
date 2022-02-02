import zhttp.http.*
import zhttp.service.Server
import zio.*
import zio.json.*

object HelloWorld extends ZIOAppDefault {

  // Create HTTP route
  val app: RHttpApp[UserService with Console] = Http.collectZIO[Request] {
    case Method.GET -> !! / "text" => ZIO.succeed(Response.text("Hello World!"))
    case Method.GET -> !! / "json" => {
      val userResponse = UserService
        .create("john", "smith", 47)
        .orDie
      val json = userResponse.map(x => x.toJson)
      json.map(x => Response.json(x))
    }

  }

  // Run it like any simple app
  override val run =
    Server
      .start(8090, app.silent)
      .provideCustomLayer(UserServiceLive.layer)
}
