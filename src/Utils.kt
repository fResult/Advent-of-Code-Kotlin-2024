import java.lang.IndexOutOfBoundsException
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
  .toString(16)
  .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.displayWith(tagPrefix: String? = "Result"): Unit = println("$tagPrefix: $this")

fun List<String>.unlines(): String = this.joinToString("\n")
fun List<String>.mapToInts(): List<Int> = this.map { it.toInt() }
fun String.words(): List<String> = this.split(" ")
fun <T> List<List<T>>.transpose(): List<List<T>> {
  if (this.isEmpty() || this[0].isEmpty()) return emptyList()
  val rowCount = this.size
  val colCounts = this.map { it.size }
  val maxColCount = colCounts.max()

  return List(maxColCount) { colIdx ->
    List(rowCount) { rowIdx ->
      try {
        this[rowIdx][colIdx]
      } catch (e: IndexOutOfBoundsException) {
        null
      }
    }.filterNotNull()
  }
}

typealias Index = Int
typealias Predicate<T> = (T) -> Boolean

infix fun <T1, T2, R> ((T2) -> R).after(f: (T1) -> T2): (T1) -> R = { x ->
  val g = this
  g(f(x))
}
