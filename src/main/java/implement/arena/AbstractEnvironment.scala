package implement.arena

import framework.arena.{Blueprintable, Environmentable}
import org.bukkit.block.Biome
import implement.general.AbstractInformable
import org.bukkit.Location
import org.bukkit.util.Vector
import scala.collection.JavaConverters._

/**
 * The Base implementation for the Environmentable interface.
 */
abstract class AbstractEnvironment(name: String, desc: String, val blueprint: Blueprintable) extends AbstractInformable(desc, null) with Environmentable{

  override val getBlueprint: Blueprintable = blueprint

  override val getBiomeType: Biome = {

    val biomes = List[Biome]()

    for(v: Location <- blueprint.getCurrentLayout.asScala) biomes :: List(v.getBlock.getBiome)

    biomes.maxBy(x => x.name())

  }

  override def isCompatibleWith(other: Environmentable): Boolean = {
    val oB = other.getBlueprint
    if(blueprint.getSize != oB.getSize || blueprint.getArea != oB.getArea) false

    val toFindIn = blueprint.getRelativeLayout.keySet()
    for(v: Vector <- oB.getRelativeLayout.keySet().asScala) {
      if(!toFindIn.contains(v)) false
    }
    true
  }
}
