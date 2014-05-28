package framework.utility

import org.bukkit.permissions.{PermissionDefault, Permission}
import implement.commands.GameCommand
import org.bukkit.entity.Player
import scala.collection.JavaConverters._

/**
 * The game authentication system uses this as a simple in-memory check
 * <br/>
 * <br/>
 * The hierarchy goes as such (least to most permissions):
 * <ol>
 *  <li>visitor</li>
 *  <li>participant</li>
 *  <li>host</li>
 *  <li>designer</li>
 *  <li>admin</li>
 *  <li>op</li>
 * </ol>
 * The namespaces are:
 * <ol>
 *  <li>create</li>
 *  <li>play</li>
 *  <li>control</li>
 * </ol>
 */
class GameAuth(val authName: String, val authGroup: String, var associatedCommands: List[Class[GameCommand]]) {

  def this(authName: String, authGroup: String) = this(authName, authGroup, List())

  val permission: Permission = new Permission(authName, PermissionDefault.FALSE)
}

object GameAuth {
  val defaultAuth = new Permission("visit", "visitor")

  var auths: List[GameAuth] = List(
    new GameAuth("login", "visitor"),
    new GameAuth("play", "participant"),
    new GameAuth("create", "designer"),
    new GameAuth("alter", "admin"),
    //    new GameAuth("boot", "admin"),
    //    new GameAuth("ban", "op"),
    new GameAuth("control", "host")
  )

  def getAuthsForGroup(group: String) = auths.filter(x => x.authGroup.equals(group))

  def getAuthByName(name: String): Permission = {
    val auth = auths.find(x => x.authName.equals(name))
    if (auth.isDefined) auth.get.permission
    else defaultAuth
  }

  def getAuthsByCommand(command: GameCommand) = for(a <- auths.filter(x => x.associatedCommands.exists(x => command.isInstanceOf[x.type]))) yield a.permission


  def addAuthType(name: String, group: String, commands: List[GameCommand]): Boolean = {
    if (auths.exists(x => x.authName.equals(name))) false

    auths = auths ++ List(new GameAuth(name, group))
    true

  }

  def removeAuthType(name: String): Boolean = {
    if (auths.exists(x => x.authName.equals(name))) {
      auths.dropWhile(x => x.authName.equals(name))
      true
    } else false
  }

  def authorize(authName: String, player: Player): Boolean = {
    val auth = auths.find(x => x.authName.equals(authName))
    if(auth.isDefined) auth.get.permission.getPermissibles.add(player)
    else false
  }

  def deauthorize(authName: String, player: Player): Boolean = {
    val auth = auths.find(x => x.authName.equals(authName))
    if(auth.isDefined) auth.get.permission.getPermissibles.remove(player)
    else false
  }
}