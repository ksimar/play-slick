package providers

import com.typesafe.config.ConfigFactory
import slick.jdbc.{JdbcProfile, MySQLProfile, PostgresProfile}

trait DBConnection {

  val driver : JdbcProfile

  import driver.api._

  val db: Database

}
