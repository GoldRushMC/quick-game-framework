package implement.arena

import framework.arena.Blueprintable
import framework.utility.Coordinate
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import org.bukkit.inventory.ItemStack
import implement.general.AbstractInformable
import scala.collection.JavaConverters._
import org.bukkit.block.Block

/**
 * The Base implementation for Blueprint. Also, shows how awesome Scala is.
 */
class AbstractBlueprint(name: String, desc: String, private var point1: Location, private var point2: Location, var w: Option[World], var radius: Option[Int]) extends AbstractInformable(name, desc) with Blueprintable {

  def this(name: String, desc: String, m: java.util.List[Coordinate], w: Option[World]) = {
    this(name, desc, null, null, w, null)
    val map = m.asScala
    val max = map.maxBy(x => x.v.length).v
    val min = map.minBy(x => x.v.length).v
    if(w.isDefined) {
      point1 = w.get.getBlockAt(max.getBlockX, max.getBlockY, max.getBlockZ).getLocation
      point2 = w.get.getBlockAt(min.getBlockX, min.getBlockY, min.getBlockZ).getLocation
    }
  }

  def this(name: String, desc: String) = this(name, desc, null, null, null, null)

  private[AbstractBlueprint] class Bounds[T](val upper: T, val lower: T)

  private val norm1 = point1.toVector.normalize
  private val norm2 = point2.toVector.normalize


  var xBound = bound(norm1.getBlockX, norm2.getBlockX)
  var yBound = bound(norm1.getBlockY, norm2.getBlockY)
  var zBound = bound(norm1.getBlockZ, norm2.getBlockZ)

  override def getCurrentLayout: java.util.List[Location] = if(w.isDefined) generateArea(w.get) else List[Location]().asJava

  private[arena] var relativeLayout: List[Coordinate] = (for(v: Location <- getCurrentLayout.asScala) yield new Coordinate(v.toVector, v.getBlock.getDrops.iterator().next())).toList

  /**
   * Gets the Lower and Upper Bounds of the pair given
   * @param x the first number
   * @param y the second number
   * @return a Tuple that has the order: Upper-Bound, Lower-Bound
   */
  protected def bound(x: Int, y: Int) = if(x > y) new Bounds(x, y) else new Bounds(y, x)

  /**
   * Gets the difference between two Ints within a Tuple.
   * @param t the Tuple of Ints
   * @return the difference between the Ints
   */
  protected def diff(t: Bounds[Int]): Int = t.upper - t.lower

  override def getRelativeLayout = {
    (for {
      v <- relativeLayout
    } yield (v.v, v.m)).toMap[Vector, ItemStack].asJava
  }

  override val getSize = diff(xBound) * diff(yBound) * diff(zBound)

  override val getArea = diff(xBound) * diff(zBound)

  /**
   * Default radius implementation just uses the volume method, getSize()
   */
  var getRadius: Int = if(radius.isDefined) radius.get else getSize

  /**
   * Creates a list of Locations
   */
  protected final def generateArea(w: World): java.util.List[Location] = {
    //Create a list of coordinates
    (for {
      x <- generateRelativeArea()
      b = w.getBlockAt (x.getBlockX, x.getBlockY, x.getBlockZ)
      i = b.getDrops.iterator().next() //Just grabs the first item
    } yield b.getLocation).asJava
  }

  /**
   * Creates a list of Vectors
   */
  protected final def generateRelativeArea() = {
    //Create a list of coordinates
    for {
      x <- xBound.lower to xBound.upper
      y <- yBound.lower to yBound.upper
      z <- zBound.lower to zBound.upper
    } yield new Vector(x, y, z)
  }

  override val getRelativeBoundary = (for(relV <- relativeLayout.filter(v => v.v.getBlockX >= getRadius || v.v.getBlockY >= getRadius || v.v.getBlockZ >= getRadius)) yield relV.v).asJava

  override val getCurrentBoundary = getCurrentLayout.asScala.filter(x => getRelativeBoundary.contains(x.toVector)).asJava

  override def createReferentialLayout(southWest: Location, northEast: Location) {
    if(southWest.distance(northEast) >= getSize) {
      this.w = Option.apply(southWest.getWorld)
      val xb = bound(southWest.getBlockX, northEast.getBlockX)
      val yb = bound(southWest.getBlockY, northEast.getBlockY)
      val zb = bound(southWest.getBlockZ, northEast.getBlockZ)
      val rel = getRelativeLayout
      val w = this.w.get

      val list = for {
        x <- xb.upper to xb.lower
        y <- yb.upper to yb.lower
        z <- zb.upper to zb.lower
        b = w.getBlockAt(x, y, z)
        v = b.getLocation.toVector.normalize
      } yield new Tuple2[Vector, Block](v, b)

      list.foreach(x => {
        if(rel.containsKey(x._1)) x._2.setType(rel.get(x._1).getType)
      })
    }
  }

  override def createReferentialLayout(center: Location) {

  }
}