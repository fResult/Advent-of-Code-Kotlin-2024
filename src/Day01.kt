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

  fun part2(lines: List<String>): Int {
    val rightToCountMap = hashMapOf<Int, Count>()
    lines.map(getOneFromPairInLine(PairSide.RIGHT))
      .forEach { rightToCountMap[it] = rightToCountMap.getOrDefault(it, 0) + 1 }
    val lefts = lines.map(getOneFromPairInLine(PairSide.LEFT))

    return lefts.sumOf { it * rightToCountMap.getOrDefault(it, 0) }
  }

  // Test if implementation meets criteria from the description, like:
  val sampleFile = "Day01_sample"
  check(part1(readInput(sampleFile)) == 11)

  val fileName =
//    sampleFile
    "Day01_test"

  // Or read a large test input from the `src/Day01_test.txt.txt.txt` file:
  val testInput = readInput(fileName)
  val part1Result = part1(testInput)

  val expectedPart1Result = 1319616
  check(expectedPart1Result == part1Result)

  val part2Result = part2(testInput)
  val expectedPart2Result = 27267728
  check(expectedPart2Result == part2Result)

  // Read the input from the `src/Day01.txt` file.
  part1Result.displayWith("Part 1")
  part2(testInput).displayWith("Part 2")
}

typealias Line = String
typealias Count = Int

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
