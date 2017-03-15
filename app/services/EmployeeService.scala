package services

import javax.inject.Inject

import components.EmployeeComponent
import models.Employee
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class EmployeeService @Inject()(employeeComponent: EmployeeComponent) {

//  def create() = {
//    employeeComponent.create.foreach(s"")
//  }

  def insert(employee: Employee) = {
    employeeComponent.insert(employee).map(x=>s"$x rows inserted").recover{
      case ex: Throwable => ex.getMessage
    }
  }

  def delete(id: Int) = {
    employeeComponent.deleteById(id).map(x=>s"$x row deleted").recover{
      case ex: Throwable => ex.getMessage
    }
  }
  def updateNameById(id: Int, name: String) = {
    employeeComponent.updateNameById(id, name).map(x=>s"$x row updated").recover{
      case ex: Throwable => ex.getMessage
    }
  }
  def upsert(employee: Employee) = {
    employeeComponent.upsert(employee).map(x=>s"$x row updated").recover{
      case ex: Throwable => ex.getMessage
    }
  }

  def search(id: Int) = {
    employeeComponent.search(id).map{
      list => list.map(employee => s"${employee.toString}")
    }.recover{
      case ex: Throwable => ex.getMessage :: Nil
    }
  }

  def getAll() = {
    employeeComponent.getAll.map{
      list=> //s"${list.size} rows selected"
        list.map(x=> s"${x.toString}")
    }.recover{
      case ex: Throwable => ex.getMessage :: Nil
    }
  }

  def maxExperience(): Future[Option[Double]] = {
    employeeComponent.maxExperience.map{
      experience => experience.map(identity)
    }
  }




}
