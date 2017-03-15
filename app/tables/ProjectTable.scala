package tables

import javax.inject.Inject

import models.Project
import providers.DBProvider
import slick.jdbc.MySQLProfile.api._


class ProjectTable @Inject() (val table: EmployeeTable, dBProvider: DBProvider) {

  private[tables] class ProjectTable(tag: Tag) extends Table[Project](tag, "project_table") {
    val id = column[Int]("id",O.PrimaryKey)
    val name = column[String]("name")

    def employeeProjectFK = foreignKey("employee_project_fk", id, table.employeeTableQuery)(_.id)
    def * = (id, name) <>(Project.tupled,Project.unapply)

  }
  val projectTableQuery = TableQuery[ProjectTable]

}
