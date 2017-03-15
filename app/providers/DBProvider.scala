package providers

import com.typesafe.config.ConfigFactory
import slick.jdbc.{MySQLProfile, PostgresProfile}

class DBProvider {

  def dbType = ConfigFactory.load().getString("dbType")

  val driver = if(dbType == "myMySqlDB") MySQLProfile
  else if (dbType == "myPostgresDB") PostgresProfile
  else throw new Exception("Invalid DB name")

  import driver.api._

  val db: Database = Database.forConfig(dbType)

}
