/**
 * [Day 2: Red-Nosed Reports](https://adventofcode.com/2024/day/2)
 */
fun main() {
  val MAIN_INPUT_FILE = "Day02_test"
  val EXPECTED_PART_1_RESULT = 383
  val EXPECTED_PART_2_RESULT = 1
  val SAMPLE_INPUT_FILE = "Day02_sample"
  val EXPECTED_SAMPLE_PART_1_RESULT = 2
  val EXPECTED_SAMPLE_PART_2_RESULT = 1

  fun part1(lines: List<String>): Int {
    val result = lines.map { line ->
      {
        val levels = line.split(" ").map { level -> level.toInt() }
        val levelsUnderCriteria = underCriteria(levels)

        levels.foldIndexed(ReportTrackingState.INITIAL, levelsUnderCriteria)
      }
    }
    return result.map { it() }.count(::isSafeReport)
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  // Test if implementation meets criteria from the description (`src/Day02_sample.txt`), like:
  val sampleInput = readInput(SAMPLE_INPUT_FILE)
  val samplePart1Result = part1(sampleInput)
  val samplePart2Result = part2(sampleInput)
  samplePart1Result.displayWith("Sample Part 1")
//  samplePart2Result.displayWith("Sample Part 2")
  check(EXPECTED_SAMPLE_PART_1_RESULT == samplePart1Result)
//  check(EXPECTED_SAMPLE_PART_2_RESULT == samplePart2Result)

  // Or read a large test input from the `src/Day02_test.txt` file:
  val testInput = readInput(MAIN_INPUT_FILE)
  val part1Result = part1(testInput)
//  val part2Result = part2(testInput)
  part1Result.displayWith("Part 1")
//  part2Result.displayWith("Part 2")
  check(EXPECTED_PART_1_RESULT == part1Result)
//  check(EXPECTED_PART_2_RESULT == part2Result)
}

private typealias Index = Int

private enum class ReportTrackingState {
  INITIAL, INCREASING, DECREASING, MARKED_UNSAFE
}

private fun isSafeReport(reportState: ReportTrackingState): Boolean =
  listOf(ReportTrackingState.DECREASING, ReportTrackingState.INCREASING).contains(reportState)

private fun isCurrMoreThanPrevFrom1To3(current: Int, previous: Int): Boolean =
  listOf(1, 2, 3).contains(current - previous)

private fun isCurrLessThanPrevFrom1To3(current: Int, previous: Int): Boolean =
  listOf(1, 2, 3).contains(previous - current)

private fun underCriteria(xs: List<Int>): (Index, ReportTrackingState, Int) -> ReportTrackingState {
  return { idx, prevState, x ->
    if (idx > 0) {
      when (prevState) {
        ReportTrackingState.INCREASING -> if (isCurrMoreThanPrevFrom1To3(
            x,
            xs[idx - 1]
          )
        ) ReportTrackingState.INCREASING else ReportTrackingState.MARKED_UNSAFE

        ReportTrackingState.DECREASING -> if (isCurrLessThanPrevFrom1To3(
            x,
            xs[idx - 1]
          )
        ) ReportTrackingState.DECREASING else ReportTrackingState.MARKED_UNSAFE

        ReportTrackingState.MARKED_UNSAFE -> ReportTrackingState.MARKED_UNSAFE

        ReportTrackingState.INITIAL -> {
          if (isCurrMoreThanPrevFrom1To3(x, xs[idx - 1])) {
            ReportTrackingState.INCREASING
          } else if (isCurrLessThanPrevFrom1To3(x, xs[idx - 1])) {
            ReportTrackingState.DECREASING
          } else {
            ReportTrackingState.MARKED_UNSAFE
          }
        }
      }
    } else
      ReportTrackingState.INITIAL
  }
}



