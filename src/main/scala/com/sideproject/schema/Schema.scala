package com.sideproject.schema

import org.apache.spark.sql.types._

object Schema {
  def selectSchema(sheet: String): StructType = {
    sheet match {
      case "Events" => eventsSheetSchema
      case "EventSessions" => eventSessionsSheetSchema
      case "SessionsDownloads" => sessionDonloadSheetSchema
      case "EventSpeakers" => eventSpeakersSheetSchema
      case "SessionsSpeakers" => sessionSpeakersSheetSchema
      case "EventSponsors" => eventSponsorSheetSchema
    }
  }
  private val eventsSheetSchema: StructType = new StructType()
    .add("EventID", LongType, true)
    .add("EventName", StringType, true)
    .add("EventDate", TimestampType, true)
    .add("AttendeeEstimate", DoubleType, true)
    .add("Timezone", StringType, true)
    .add("EventDescription", StringType, true)
    .add("twitterHashtag", StringType, true)
    .add("Location", StringType, true)
    .add("Street", StringType, true)
    .add("City", StringType, true)
    .add("State", StringType, true)
    .add("Zipcode", StringType, true)

  private val eventSessionsSheetSchema: StructType = new StructType()
    .add("EventID", LongType, true)
    .add("SessionID", LongType, true)
    .add("Track", StringType, true)
    .add("Title", StringType, true)
    .add("Description", StringType, true)
    .add("SpeakerRole", StringType, true)
    .add("Speaker", StringType, true)
    .add("Level", StringType, true)
    .add("DownloadLinks", StringType, true)
    .add("HasDownLoads", StringType, true)
    .add("SpeakerUrl", StringType, true)
    .add("Track.1", StringType, true)
    .add("Abstract", StringType, true)
    .add("speakers", StringType, true)
    .add("LanguageDesc", StringType, true)
    .add("LanguageCode", StringType, true)
    .add("check", IntegerType, true)

  private val sessionDonloadSheetSchema: StructType = new StructType()
    .add("EventID", LongType, true)
    .add("SessionID", LongType, true)
    .add("DownloadURL", StringType, true)
    .add("DownloadTitle", StringType, true)

  private val eventSpeakersSheetSchema: StructType = new StructType()
    .add("EventID", LongType, true)
    .add("SpeakerID", LongType, true)
    .add("Name", StringType, true)
    .add("Description", StringType, true)
    .add("Twitter", StringType, true)
    .add("Linkedin", StringType, true)
    .add("ContactURL", StringType, true)
    .add("imageURL", StringType, true)
    .add("imageHeight", DoubleType, true)
    .add("imageWidth", DoubleType, true)

  private val sessionSpeakersSheetSchema: StructType = new StructType()
    .add("SessionID", LongType, true)
    .add("EventID", LongType, true)
    .add("SpeakerId", LongType, true)
    .add("SpeakerName", StringType, true)

  private val eventSponsorSheetSchema: StructType = new StructType()
    .add("EventID", LongType, true)
    .add("SponsorID", LongType, true)
    .add("name", StringType, true)
    .add("label", StringType, true)
    .add("url", StringType, true)
    .add("imageURL", StringType, true)
    .add("imageHeight", DoubleType, true)
    .add("imageWidth", DoubleType, true)

}
