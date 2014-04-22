package implement.arena

import framework.arena.Blueprintable
import framework.utility.Coordinate
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.util.Vector

/**
 * The Base implementation for Blueprint. Also, shows how awesome Scala is.
 */
abstract class AbstractBlueprint(point1: Location, point2: Location) extends Blueprintable {

  private[AbstractBlueprint] class Bounds[T](val upper: T, val lower: T)

  var xBound = bound(point1.getBlockX, point2.getBlockX)
  var yBound = bound(point1.getBlockY, point2.getBlockY)
  var zBound = bound(point1.getBlockZ, point2.getBlockZ)

  private[arena] var relativeLayout: List[Coordinate] = generateArea(point1.getWorld)
  var getCurrentLayout: java.util.List[Location]

  /**
   * Gets the Lower and Upper Bounds of the pair given
   * @param x the first number
   * @param y the second number
   * @return a Tuple that has the order: Upper-Bound, Lower-Bound
   */
  private def bound(x: Int, y: Int) = if(x > y) new Bounds(x, y) else new Bounds(y, x)

  /**
   * Gets the difference between two Ints within a Tuple.
   * @param t the Tuple of Ints
   * @return the difference between the Ints
   */
  private def diff(t: Bounds[Int]): Int = t.upper - t.lower

  def getRelativeLayout = {
    (for {
      v <- relativeLayout
    } yield (v.v, v.m)).toMap[Vector, Material].asInstanceOf[java.util.Map[Vector, Material]]
  }

  def getSize = diff(xBound) * diff(yBound) * diff(zBound)

  def getArea = diff(xBound) * diff(zBound)

  /**
   * Creates a list List of Coordinates
   *
   * @return the List<Coordinate> of Coordinates
   */
  private final def generateArea(w: World): List[Coordinate] = {
    //Create a list of coordinates
    (for {
    x <- xBound.lower to xBound.upper
    y <- yBound.lower to yBound.upper
    z <- zBound.lower to zBound.upper
    b = w.getBlockAt (x, y, z)
    } yield new Coordinate(b.getLocation.toVector, b.getType)).toList
  }
}