package sample

import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger


object App {
  def main(args: Array[String]): Unit = {
    // Set log level to "error"
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.ERROR)

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .getOrCreate()

    // Create a sample DataFrame
    val data = Seq(("Alice", 25), ("Bob", 30), ("Charlie", 35))
    val columns = Seq("Name", "Age")
    val df = spark.createDataFrame(data).toDF(columns: _*)

    // Show the DataFrame contents
    df.show()
  }
}
