package implement.game.manage

import org.bukkit.plugin.java.JavaPlugin
import java.io.{FileFilter, File}
import java.util.jar.{JarEntry, JarFile}

/**
 * The GamePackager class loads existing game types from a folder called "games" within the given plugin data folder.
 */
class GamePackager(val plugin: JavaPlugin) {

  var file = new File(plugin.getDataFolder, "games")
  if(!file.exists()) file.mkdir()
  for(f <- file.listFiles(new FileFilter() {
    override def accept(pathname: File): Boolean = pathname.getName.endsWith(".jar")
  })) tryLoad(f)

  def tryLoad(f: File): List[Class[_]] = {
    val jf = new JarFile(f).entries()
    val entries: List[JarEntry] = List[JarEntry]()
    while(jf.hasMoreElements) entries :: List[JarEntry](jf.nextElement())

    for(e <- entries) yield e.getClass
  }
}