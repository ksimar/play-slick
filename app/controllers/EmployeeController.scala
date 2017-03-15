package controllers

import javax.inject.Inject

import models.Employee
import play.api.mvc.{Action, Controller}
import services.EmployeeService

import scala.concurrent.ExecutionContext.Implicits.global

class EmployeeController @Inject()(employeeService: EmployeeService) extends Controller{

   def insertAction = Action.async {
     implicit request =>
       val employee = Employee(6, "Harsimran", 10)
       val res = employeeService.insert(employee)
       //Ok(views.html.index(res))
       res.map(x=>Ok(x))
   }

  def deleteAction = Action.async {
    implicit request =>
      val res = employeeService.delete(6)
      res.map(x=>Ok(x))
  }
  def updateAction = Action.async {
    implicit request =>
      val id = 5
      val name = "Ashish"
      val res = employeeService.updateNameById(id, name)
      res.map(x=>Ok(x))
  }

  def upsertAction = Action.async {
    implicit request =>
      val employee = Employee(7,"Chandan", 2)
      val res = employeeService.upsert(employee)
      res.map(x=>Ok(x))
  }

  def getAllAction = Action.async {
    implicit request =>
      val res = employeeService.getAll()
        res.map(x=>Ok(x.toString))
  }

  def searchAction = Action.async {
    implicit request =>
      val id = 1
      val res = employeeService.search(id)
        res.map(x=>Ok(x.toString))
  }

  def maxExperienceAction = Action.async {
    implicit request =>
      val res = employeeService.maxExperience
      res.map(experience => Ok(experience.toString))
  }
  //def joinAction = Action.async {}


}
