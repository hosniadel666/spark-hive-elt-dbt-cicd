package com.sideproject.main

import com.crealytics.spark.excel.{ExcelDataFrameReader, WorkbookReader}
import com.sideproject.schema.Schema
import com.sideproject.spark.SparkManager
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SaveMode


object App {
  def main(args: Array[String]): Unit = {
    // Set log level to "error"
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.ERROR)

    val sparkMngr = new SparkManager
    val spark = sparkMngr.enableHive
      .startTestingSparkSession

    val sheetNames = WorkbookReader(Map("path" -> "/home/hosniadel/Documents/GitHub/spark-hive-elt-dbt-cicd/data/SQLSaturdayEventHistory.xlsx")
      , spark.sparkContext.hadoopConfiguration
    ).sheetNames

    sheetNames.foreach(sheet => {
      val df = spark.read.excel(
        // inferSchema = true,
        header = true,
        dataAddress = "'%s'!".format(sheet)
      ).schema(Schema.selectSchema(sheet))
       .load("/home/hosniadel/Documents/GitHub/spark-hive-elt-dbt-cicd/data/SQLSaturdayEventHistory.xlsx")

    df.write
      .mode(SaveMode.Append)
      .saveAsTable(sheet)
    })
  }
}
