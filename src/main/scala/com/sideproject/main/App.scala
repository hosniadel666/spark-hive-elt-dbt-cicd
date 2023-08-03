package com.sideproject.main

import com.crealytics.spark.excel.{ExcelDataFrameReader, WorkbookReader}
import com.sideproject.logging.Logger
import com.sideproject.schema.Schema
import com.sideproject.spark.SparkManager
import org.apache.spark.sql.SaveMode


object App {
  def main(args: Array[String]): Unit = {
    Logger.info("Creating Spark Session")

    val sparkMngr = new SparkManager
    val spark = sparkMngr.enableHive
      .startTestingSparkSession

    val sheetNames = WorkbookReader(Map("path" -> "/home/hosniadel/Documents/GitHub/spark-hive-elt-dbt-cicd/data/SQLSaturdayEventHistory.xlsx")
      , spark.sparkContext.hadoopConfiguration
    ).sheetNames

    Logger.info("The excel sheet contains %d sheets".format(sheetNames.length))
    sheetNames.zipWithIndex.foreach { case (sheet, index) => {
      Logger.info("%d - Sheet %s".format(index, sheet))
    }
    }

    Logger.info("Start extract and load excel sheets into Hive")
    sheetNames.zipWithIndex.foreach { case (sheet, index) => {

      Logger.info("%d - Start extracting sheet %s".format(index, sheet))
      val df = spark.read.excel(
        //         inferSchema = true,
        header = true,
        dataAddress = "'%s'!".format(sheet)
      ).schema(Schema.selectSchema(sheet))
        .load("/home/hosniadel/Documents/GitHub/spark-hive-elt-dbt-cicd/data/SQLSaturdayEventHistory.xlsx")

      df.show(5)

      Logger.info("%d - Start loading sheet %s into Hive".format(index, sheet))
      df.na.drop("all").write
        .mode(SaveMode.Overwrite)
        .saveAsTable(sheet)
      Logger.info("%d - Finish loading sheet %s into Hive".format(index, sheet))
    }
    }
  }
}
