/**
 * [Day 2: Red-Nosed Reports](https://adventofcode.com/2024/day/2)
 */
fun main() {
  val MAIN_INPUT_FILE = "Day02_test"
  val EXPECTED_PART_1_RESULT = 383
  val EXPECTED_PART_2_RESULT = 417
  val SAMPLE_INPUT_FILE = "Day02_sample"
  val EXPECTED_SAMPLE_PART_1_RESULT = 2
  val EXPECTED_SAMPLE_PART_2_RESULT = 4

  fun part1(lines: List<String>): Int {
    val reports: List<Report> = lines.map { it.words().mapToInt() }
    return reports.count(::isSafeReport)
  }

  fun part2(lines: List<String>): Int {
    val reports: List<Report> = lines.map { it.words().mapToInt() }
    return reports.count(::isSafeReportWithDampener)
  }

  // Test if implementation meets criteria from the description (`src/Day02_sample.txt`), like:
  val sampleInput = readInput(SAMPLE_INPUT_FILE)
  val samplePart1Result = part1(sampleInput)
  val samplePart2Result = part2(sampleInput)
  samplePart1Result.displayWith("Sample Part 1")
  samplePart2Result.displayWith("Sample Part 2")
  check(EXPECTED_SAMPLE_PART_1_RESULT == samplePart1Result)
  check(EXPECTED_SAMPLE_PART_2_RESULT == samplePart2Result)

  // Or read a large test input from the `src/Day02_test.txt` file:
  val testInput = readInput(MAIN_INPUT_FILE)
  val part1Result = part1(testInput)
  val part2Result = part2(testInput)
  part1Result.displayWith("Part 1")
  part2Result.displayWith("Part 2")
  check(EXPECTED_PART_1_RESULT == part1Result)
  check(EXPECTED_PART_2_RESULT == part2Result)
}

private typealias Index = Int
private typealias Level = Int
private typealias Report = List<Int>

private enum class ReportTrackingState {
  INITIAL, INCREASING, DECREASING, MARKED_UNSAFE
}

private fun isSafeState(reportState: ReportTrackingState): Boolean =
  listOf(ReportTrackingState.DECREASING, ReportTrackingState.INCREASING).contains(reportState)

private fun isSafeReport(report: Report): Boolean {
  val trackState = trackReportState(report)
  val trackedReportState = report.foldIndexed(ReportTrackingState.INITIAL, trackState)

  return isSafeState(trackedReportState);
}

private fun isSafeReportWithDampener(report: Report): Boolean {
  if (isSafeReport(report)) return true

  val trackState = trackReportState(report)

  for (idx in report.indices) {
    val modifiedReport = report.toMutableList().apply { removeAt(idx) }
    val modifiedFinalState = modifiedReport.foldIndexed(ReportTrackingState.INITIAL, trackState)
    if (isSafeState(modifiedFinalState)) return true
  }

  return false
}

private fun trackReportState(xs: Report): (Index, ReportTrackingState, Level) -> ReportTrackingState {
  return { idx, prevState, currentLevel ->
    if (idx == 0) ReportTrackingState.INITIAL
    else {
      val prevLevel = xs[idx - 1]
      val acceptanceDiffs = (1..3)
      val currMoreThanPrev = acceptanceDiffs.contains(currentLevel - prevLevel)
      val currLessThanPrev = acceptanceDiffs.contains(prevLevel - currentLevel)

      when (prevState) {
        ReportTrackingState.INCREASING -> {
          if (currMoreThanPrev) ReportTrackingState.INCREASING
          else ReportTrackingState.MARKED_UNSAFE
        }

        ReportTrackingState.DECREASING -> {
          if (currLessThanPrev) ReportTrackingState.DECREASING
          else ReportTrackingState.MARKED_UNSAFE
        }

        ReportTrackingState.MARKED_UNSAFE -> ReportTrackingState.MARKED_UNSAFE

        ReportTrackingState.INITIAL -> {
          if (currMoreThanPrev) ReportTrackingState.INCREASING
          else if (currLessThanPrev) ReportTrackingState.DECREASING
          else ReportTrackingState.MARKED_UNSAFE
        }
      }
    }
  }
}
