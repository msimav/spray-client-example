package ms.crawler.workers

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import spray.http._

object Main extends App {
  implicit val system = ActorSystem("crawler-system")
  val crawler = system.actorOf(CrawlerWorker.props, "crawler")

  val request = HttpRequest(HttpMethods.GET, "http://msimav.net/")

  IO(Http).tell(request, crawler)
}