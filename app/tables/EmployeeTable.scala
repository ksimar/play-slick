package tables

import javax.inject.Inject

import models.Employee
import providers.DBProvider
import slick.jdbc.MySQLProfile.api._

class EmployeeTable @Inject()(val dBProvider: DBProvider) {

  class EmployeeTable(tag: Tag) extends Table[Employee](tag, "experience") {
    val id = column[Int]("id", O.PrimaryKey)
    val name = column[String]("name")
    val experience = column[Double]("experience")

    def * = (id, name, experience) <>(Employee.tupled, Employee.unapply)

  }

  val employeeTableQuery = TableQuery[EmployeeTable]

}
