package ms.crawler.workers

import akka.actor.{ActorLogging, Props, Actor}
import spray.http._
import spray.http.StatusCodes._

import ms.crawler.Printer

object CrawlerWorker {
  val props = Props[CrawlerWorker]
}

class CrawlerWorker extends Actor with ActorLogging {

  val printer = context.actorOf(Props[Printer], "printer")
  val regex = """<a .*?href="(.*?)".*?>""".r

  def receive: Receive = {
    case HttpResponse(OK, entity, _, _) =>
      val it = regex findAllMatchIn entity.asString
      val links = it map { _.group(1) }
      printer ! links.mkString("\n")
  }
}
