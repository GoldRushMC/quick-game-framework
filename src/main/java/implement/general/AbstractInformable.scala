package implement.general

import framework.general.Informable

/**
 * Removes some boilerplate code from other classes.
 */
abstract class AbstractInformable(name: String, val description: String) extends Informable {

  override val getDescription: String = description
  override val getName: String = if(name == null) getClass.getName else name
}