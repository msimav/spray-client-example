package ms.crawler

import akka.actor.Actor

class Printer extends Actor {
  def receive: Receive = {
    case x => println(x)
      context.system.shutdown
  }
}
