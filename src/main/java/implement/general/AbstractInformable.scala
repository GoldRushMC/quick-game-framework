package implement.general

import framework.general.Informable

/**
 * Removes some boilerplate code from other classes.
 */
abstract class AbstractInformable(private var name: String, private var description: String) extends Informable {

  def this() = this(null, "")

  override def getDescription: String = description
  override def getName: String = if(name == null) getClass.getName else name
}