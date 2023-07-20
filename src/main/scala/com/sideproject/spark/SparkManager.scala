package com.sideproject.spark

import com.sideproject.configuration.AppConf
import org.apache.spark.sql.SparkSession

class SparkManager {
  private val master_t = AppConf.getString("spark.master")
  private val appName_t = AppConf.getString("spark.app.name")
  private val warehouseDir_t = AppConf.getString("spark.sql.warehouse.dir")
  private val hiveMetaStore_t = AppConf.getString("hive.metastore.uris")

  private var spark: SparkSession = null
  private var isHiveEnable: Boolean = false

  def startTestingSparkSession: SparkSession = {
    var sparkBuilder = SparkSession.builder()
      .master(master_t)
      .appName(appName_t)

    if (isHiveEnable)
      sparkBuilder = sparkBuilder.enableHiveSupport()
        .config("hive.metastore.uris", hiveMetaStore_t)
        .config("spark.sql.warehouse.dir", warehouseDir_t)

    this.spark = sparkBuilder.getOrCreate()
    this.spark
  }

  def startLocalSparkSession: SparkSession  = {
    var sparkBuilder = SparkSession.builder()
      .master(master_t)
      .appName(appName_t)


    if (isHiveEnable)
      sparkBuilder = sparkBuilder.enableHiveSupport()

    this.spark = sparkBuilder.getOrCreate()
    this.spark
  }
  def enableHive: SparkManager = {
    this.isHiveEnable = true
    this
  }
}

