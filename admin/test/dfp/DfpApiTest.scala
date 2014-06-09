package dfp

import org.scalatest.{Matchers, FlatSpec}
import play.api.test.Helpers._
import play.api.test.FakeApplication
import com.google.api.ads.dfp.axis.v201403.{LineItem => DfpApiLineItem, Size, CreativePlaceholder, RoadblockingType}
import scala.collection.JavaConverters._

class DfpApiTest extends FlatSpec with Matchers {
  "DfpApi" should "grab something" in running(FakeApplication()) {
    def hasA1x1Pixel(placeholders: Array[CreativePlaceholder]): Boolean = {
      val outOfPagePlaceholder: Array[CreativePlaceholder] = for {
        placeholder <- placeholders
        companion <- placeholder.getCompanions
        if (companion.getSize().getHeight() == 1 && companion.getSize().getWidth() == 1)
      } yield companion
       outOfPagePlaceholder.nonEmpty
    }

    val filtered: Seq[DfpApiLineItem] = DfpApi.getAllDfpLineItemsMaybe.filter(
      item => item.getRoadblockingType == RoadblockingType.CREATIVE_SET &&
      hasA1x1Pixel(item.getCreativePlaceholders)
    )

    for(item <- filtered) {
      println(item.getId)
    }
  }

  "DFP Api connected to PROD" should "be able to grab the id for keywords" in {
    val keywordId: Option[Long] = DfpApi.fetchTargetingIdFor("Keywords")
    keywordId.get shouldEqual(177687L)
  }

  "DFP Api connected to PROD" should "be able to grab the id for slots" in {
    val keywordId: Option[Long] = DfpApi.fetchTargetingIdFor("Slot")
    keywordId.get shouldEqual(174447L)
  }

}
