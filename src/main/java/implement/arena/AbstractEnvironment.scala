package implement.arena

import framework.arena.{Blueprintable, Environmentable}
import org.bukkit.block.Biome
import implement.general.AbstractInformable
import org.bukkit.Location

/**
 * The Base implementation for the Environmentable interface.
 */
abstract class AbstractEnvironment(name: String, desc: String, val blueprint: Blueprintable) extends AbstractInformable(desc, null) with Environmentable{

  override val getBlueprint: Blueprintable = blueprint

  override val getBiomeType: Biome = {

    val biomes = List[Biome]()

    for(v: Location <- blueprint.getCurrentLayout.asInstanceOf[List[Location]]) biomes :: List(v.getBlock.getBiome)

    biomes.maxBy(x => x.name())

  }


}
