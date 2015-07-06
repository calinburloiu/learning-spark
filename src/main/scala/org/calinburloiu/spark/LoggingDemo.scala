package org.calinburloiu.spark

import com.typesafe.scalalogging.slf4j.{StrictLogging}

object LoggingDemo extends StrictLogging {

  def main(args: Array[String]): Unit = {
    logger.error("Crisis in Greece!")
    logger.info("info")
    logger.debug("debug")
  }
}
