//package services
//
//import javax.inject.Inject
//
//import components.EmployeeComponent
//import models.Employee
//
//class ProjectService @Inject()(employeeComponent: EmployeeComponent) {
//
//  //  def create() = {
//  //    employeeComponent.create.foreach(s"")
//  //  }
//
//  def insert(employee: Employee) = {
//    employeeComponent.insert(employee).map(x => s"$x rows inserted").recover {
//      case ex: Throwable => ex.getMessage
//    }
//  }
//
//  def delete(id: Int) = {
//    employeeComponent.deleteById(id).map(x => s"$x row deleted").recover {
//      case ex: Throwable => ex.getMessage
//    }
//  }
//
//  def updateNameById(id: Int, name: String) = {
//    employeeComponent.updateNameById(id, name).map(x => s"$x row updated").recover {
//      case ex: Throwable => ex.getMessage
//    }
//  }
//
//  def upsert(employee: Employee) = {
//    employeeComponent.upsert(employee).map(x => s"$x row updated").recover {
//      case ex: Throwable => ex.getMessage
//    }
//  }
//
//}