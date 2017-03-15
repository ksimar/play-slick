package tables

import javax.inject.Inject

import models.Dependent
import providers.DBProvider
import slick.jdbc.MySQLProfile.api._

class DependentTable @Inject() (val table: EmployeeTable, dBProvider: DBProvider){

  private[tables] class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent_table") {
    val id = column[Int]("id", O.Unique)
    val name = column[String]("name")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")

    //def pk = primaryKey("dependent_pk", (id,name))
    def employeeDependentFK = foreignKey("employee_dependent_fk", id, table.employeeTableQuery)(_.id)
    def * = (id, name, relation, age) <>(Dependent.tupled, Dependent.unapply)
  }

  val dependentTableQuery = TableQuery[DependentTable]

}
