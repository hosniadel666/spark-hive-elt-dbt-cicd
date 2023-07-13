package com.sideproject.configuration

import com.typesafe.config.{Config, ConfigFactory}

import java.io.File

/**
 * @todo We need to centralize our application configurations
 */
object AppConf {
  private val conf: Config = ConfigFactory.load()

  def getString(key: String): String = conf.getString(key)

  def getInt(key: String): Int = conf.getInt(key)

  def getLong(key: String): Long = conf.getLong(key)

  def getBoolean(key: String): Boolean = conf.getBoolean(key)

  def getDouble(key: String): Double = conf.getDouble(key)

}