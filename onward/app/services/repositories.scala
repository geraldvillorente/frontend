package services

import common.Edition
import model.{Trail, Content}
import scala.concurrent.Future
import conf.ContentApi
import feed.{TopReferrersAgent, MostReadAgent}
import com.gu.openplatform.contentapi.model.ItemResponse

trait Related extends ConciergeRepository {
  def related(edition: Edition, path: String): Future[Seq[Trail]] = {

    val response = ContentApi.item(path, edition)
      .showRelated(true)
      .response

    val trails = response.map { response =>
      response.relatedContent map { Content(_) }
    }

    trails recoverApi404With Nil
  }

  def getPopularInTag(edition: Edition, tag: String): Future[Seq[Trail]] = {

    val response = ContentApi.search(edition)
      .tag(tag)
      .pageSize(50)
      .dateId("date/last7days")
      .response

    val trails: Future[Seq[Content]] = response.map { response =>
      response.results.map(Content(_)).sortBy(content => - MostReadAgent.getViewCount(content.id).getOrElse(0)).take(10)
    }

    trails
  }

}