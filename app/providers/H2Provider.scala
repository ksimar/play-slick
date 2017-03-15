//package providers
//
//import java.util.UUID
//
//import slick.jdbc.H2Profile
//
//
//trait H2Provider @Inject()(dbConnection: DBConnection) {
//
//  val driver = H2Profile
//
//  import driver.api.Database
//
//  val randomDB = "jdbc:h2:mem:test" + UUID.randomUUID().toString + ";"
//  val h2Url = randomDB + "MODE=MySql;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'src/test/resources/schema.sql'\\;RUNSCRIPT FROM 'src/test/resources/initaldata.sql'"
//  println(s"\n\n~~~~~~~~~~~~~~~~~~~~~ $h2Url ~~~~~~~~~~~~~~~~~~~~~~~\n\n")
//
//  val db: Database = Database.forURL(url = h2Url, driver = "org.h2.Driver")
//
//}
