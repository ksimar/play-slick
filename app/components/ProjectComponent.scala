package components

import javax.inject.Inject

import models.Project
import play.api.db
import providers.DBProvider
import tables.{EmployeeTable, ProjectTable}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ProjectComponent @Inject() (val table: ProjectTable,val  dbProvider: DBProvider) {

  import dbProvider.driver.api._

  def create = dbProvider.db.run(table.projectTableQuery.schema.create)

  def insert(proj: Project) = dbProvider.db.run{
    table.projectTableQuery += proj
  }

  def deleteById(projId: Int) = {
    dbProvider.db.run(table.projectTableQuery.filter(_.id === projId).delete)
  }

  def updateNameById(projID: Int, name: String) = {

    dbProvider.db.run(table.projectTableQuery.filter(_.id===projID).map(record=>(record.name)).update(name))
  }

  def upsert(proj: Project): Future[Int] = {
    dbProvider.db.run(table.projectTableQuery.insertOrUpdate(proj))
  }

  def getAll() = {
    dbProvider.db.run(table.projectTableQuery.to[List].result)
  }

  def search(id: Int): Future[Option[Project]] = {
    dbProvider.db.run(table.projectTableQuery.filter(_.id===id).result.headOption)
  }

  def combinedActions(proj1: Project, proj2: Project) = {
    val create = table.projectTableQuery.schema.create
    val insert1 = table.projectTableQuery += proj1
    val insert2 = table.projectTableQuery += proj2
    val insert3 = table.projectTableQuery += Project(11, "abc")
    val select = table.projectTableQuery.to[List].result
    val select2 = table.projectTableQuery.filter(_.id === 1).to[List].result
    val seq = insert1.andThen(insert3).andThen(insert2).cleanUp {
      case Some(_) => select
      case _ => select2
    }

    dbProvider.db.run(seq)
  }

}
