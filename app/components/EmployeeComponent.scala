package components

import javax.inject.{Inject}

import models.Employee
//import play.api.db
import providers.DBProvider
import tables.EmployeeTable

import scala.concurrent.Future

//(@Named("mySQL") dbProvider: DBProvider)
class EmployeeComponent @Inject()(table: EmployeeTable, dbProvider: DBProvider) {


  import  dbProvider.driver.api._

  def create = dbProvider.db.run(table.employeeTableQuery.schema.create)
  def insert(emp: Employee) = dbProvider.db.run{
    table.employeeTableQuery += emp
  }
  def deleteById(empId: Int) = {
    dbProvider.db.run(table.employeeTableQuery.filter(_.id === empId).delete)
  }

  def updateNameById(empID: Int, name: String) = {

    dbProvider.db.run(table.employeeTableQuery.filter(_.id===empID).map(record=>(record.name)).update(name))
  }

  def upsert(emp: Employee): Future[Int] = {
    dbProvider.db.run(table.employeeTableQuery.insertOrUpdate(emp))
  }

  def getAll: Future[List[Employee]] = {
    dbProvider.db.run(table.employeeTableQuery.to[List].result)
  }

  def search(id: Int): Future[List[Employee]] = {
    dbProvider.db.run(table.employeeTableQuery.filter(_.id===id).to[List].result)
  }

  def maxExperience(): Future[Option[Double]] = {
    val query = table.employeeTableQuery.map(_.experience).max
    dbProvider.db.run(query.result)
  }

 // val searchCompiled = Compiled(search _)

//  def compiledSearch(id: Int) = {
//    val compiledQuery = searchCompiled(id).result
//  }

}

//object EmployeeComponent extends EmployeeComponent
