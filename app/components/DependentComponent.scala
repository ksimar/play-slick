package components

import javax.inject.{Inject, Named}

import models.Dependent
import providers.DBProvider
import tables.DependentTable

import scala.concurrent.Future

class DependentComponent @Inject() (val table: DependentTable, val dbProvider: DBProvider)  {

  import dbProvider.driver.api._

  def create = dbProvider.db.run(table.dependentTableQuery.schema.create)
  def insert(dependent: Dependent) = dbProvider.db.run( table.dependentTableQuery += dependent )

  def deleteById(id: Int) = {
    dbProvider.db.run(table.dependentTableQuery.filter(_.id === id).delete)
  }

  def updateNameById(id: Int, name: String) = {

    dbProvider.db.run(table.dependentTableQuery.filter(_.id===id).map(record=>(record.name)).update(name))
  }

  def upsert(dependent: Dependent): Future[Int] = {
    dbProvider.db.run(table.dependentTableQuery.insertOrUpdate(dependent))
  }

  def getAll: Future[Seq[Dependent]] = {
    dbProvider.db.run(table.dependentTableQuery.result)
  }

  def search(id: Int): Future[Seq[Dependent]] = {
    dbProvider.db.run(table.dependentTableQuery.filter(_.id===id).result)
  }

  def crossJoin(id: Int) = {
    val query = for{
      (e,d) <- table.dependentTableQuery join table.table.employeeTableQuery
    } yield (e,d)
    dbProvider.db.run(query.result)
  }

  def leftJoin() = {
    val query = for{
      (e,d) <- table.dependentTableQuery joinLeft table.table.employeeTableQuery on(_.id === _.id)
    } yield (e,d)
    dbProvider.db.run(query.result)
  }

  def rightJoin() = {
    val query = for{
      (e,d) <- table.dependentTableQuery join table.table.employeeTableQuery on(_.id === _.id)
    } yield (e,d)
    dbProvider.db.run(query.result)
  }

  def fullJoin() = {
    val query = for{
      (e,d) <- table.dependentTableQuery join table.table.employeeTableQuery on(_.id === _.id)
    } yield (e,d)
    dbProvider.db.run(query.result)
  }




}
