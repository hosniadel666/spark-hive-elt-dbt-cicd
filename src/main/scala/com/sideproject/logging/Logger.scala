package com.sideproject.logging

import org.apache.log4j.{LogManager, Logger}

object Logger {
  private val log: Logger = LogManager.getLogger(getClass.getName)

  def info(message: String): Unit = {
    log.info(message)
  }

  def warn(message: String): Unit = {
    log.warn(message)
  }

  def error(message: String): Unit = {
    log.error(message)
  }
}
