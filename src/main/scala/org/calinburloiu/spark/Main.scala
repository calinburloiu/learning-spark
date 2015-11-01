package org.calinburloiu.spark

import com.typesafe.scalalogging.slf4j.StrictLogging
import org.apache.spark.sql.SaveMode
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

object Main extends StrictLogging {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("Learning Spark with Calin")
    val sc = new SparkContext(conf)

    val lines = sc.textFile(args(0))
    val words = lines.flatMap { line =>
      line.split("""[\s,<.>/?;:'"\|!]+""")
    }
    val countedWords = words.map { word =>
      if (word == "spice") {
        logger.error("Uh! Spicy!")
      }

      (word.toLowerCase, 1L)
    }.reduceByKey(_ + _).map { case (word, count) =>
      (count, word)
    }

    val sortedCounts = countedWords.sortByKey(ascending = false)

    sortedCounts.take(25).foreach { case (count, word) =>
      logger.info(s"$count\t$word")
    }

    sc.stop()
  }
}
