import kotlin.math.abs

/**
 * Day 1: Historian Hysteria
 * https://adventofcode.com/2024/day/1
 */
fun main() {
  val MAIN_INPUT_FILE = "Day01_test"
  val EXPECTED_PART_1_RESULT = 1319616
  val EXPECTED_PART_2_RESULT = 27267728

  val SAMPLE_INPUT_FILE = "Day01_sample"
  val EXPECTED_SAMPLE_PART_1_RESULT = 11
  val EXPECTED_SAMPLE_PART_2_RESULT = 31

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

  // Test if implementation meets criteria from the description (`src/Day01_sample.txt`), like:
  var sampleInput = readInput(SAMPLE_INPUT_FILE)
  val samplePart1Result = part1(sampleInput)
  val samplePart2Result = part2(sampleInput)
  samplePart1Result.displayWith("Sample Part 1")
  samplePart2Result.displayWith("Sample Part 2")
  check(EXPECTED_SAMPLE_PART_1_RESULT == samplePart1Result)
  check(EXPECTED_SAMPLE_PART_2_RESULT == samplePart2Result)


  // Or read a large test input from the `src/Day01_test.txt` file:
  val testInput = readInput(MAIN_INPUT_FILE)
  val part1Result = part1(testInput)
  val part2Result = part2(testInput)
  part1Result.displayWith("Part 1")
  part2Result.displayWith("Part 2")
  check(EXPECTED_PART_1_RESULT == part1Result)
  check(EXPECTED_PART_2_RESULT == part2Result)
}

// Related functions
private typealias Line = String
private typealias Count = Int

private enum class PairSide {
  LEFT, RIGHT
}

private fun getOneFromPairInLine(pairSide: PairSide): (Line) -> Int {
  val separatorPattern = " +".toRegex()
  return { line ->
    if (pairSide == PairSide.LEFT) line.split(separatorPattern).first().toInt()
    else line.split(separatorPattern).last().toInt()
  }
}
