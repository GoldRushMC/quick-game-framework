package implement.general

import framework.general.Informable

/**
 * Removes some boilerplate code from other classes.
 */
abstract class AbstractInformable(val description: String, name: String) extends Informable {

  override def getDescription: String = description
  override def getName: String = if(name == null) getClass.getName else name
}