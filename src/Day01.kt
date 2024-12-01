import kotlin.math.abs

/**
 * Day 1: Historian Hysteria
 * https://adventofcode.com/2024/day/1
 */
fun main() {
  fun part1(lines: List<String>): Int {
    val sortedLefts = lines.map(getOneFromPairInLine(PairSide.LEFT)).sorted()
    val sortedRights = lines.map(getOneFromPairInLine(PairSide.RIGHT)).sorted()
    val zippedSorted = sortedLefts.zip(sortedRights)

    return zippedSorted.sumOf { (left, right) -> abs(left - right) }
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  // Test if implementation meets criteria from the description, like:
  val sampleFile = "Day01_sample"
  check(part1(readInput(sampleFile)) == 11)

  val fileName =
//    sampleFile
  "Day01_test"


  // Or read a large test input from the `src/Day01_test.txt.txt.txt` file:
  val testInput = readInput(fileName)
  val result = part1(testInput)
  println("result: $result")
//    check(part1(testInput) == 1)
//
//    // Read the input from the `src/Day01.txt` file.
//    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}

typealias Line = String

enum class PairSide {
  LEFT, RIGHT
}

private fun getOneFromPairInLine(pairSide: PairSide): (Line) -> Int {
  val separatorPattern = " +".toRegex()
  return { line ->
    if (pairSide == PairSide.LEFT) line.split(separatorPattern).first().toInt()
    else line.split(separatorPattern).last().toInt()
  }
}
