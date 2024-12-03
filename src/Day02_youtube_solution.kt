/**
 * [Day 2: Red-Nosed Reports](https://adventofcode.com/2024/day/2)
 *
 * Solution from [Youtube - Advent of Code 2024 in Kotlin. Day 2.](https://www.youtube.com/watch?v=ANZ8xEvuYqk)
 */
fun main() {
  fun isReportSafe(levels: List<ReportLevel>): Boolean {
    val acceptanceDiffs = -3..3
    val levelDiffs = levels.zipWithNext { a, b -> a - b }
    val inAcceptanceDiff: Predicate<Int> = { it in acceptanceDiffs }
    val isPosInAcceptance: Predicate<Int> = { it > 0 && inAcceptanceDiff(it) }
    val isNegInAcceptance: Predicate<Int> = { it < 0 && inAcceptanceDiff(it) }

    return (levelDiffs.all(isPosInAcceptance) || levelDiffs.all(isNegInAcceptance))
  }

  fun isReportSafeWithDampener(levels: List<ReportLevel>): Boolean {
    return levels.indices.any { idx ->
      val modifiedReport = levels.toMutableList().apply { removeAt(idx) }
      isReportSafe(modifiedReport)
    }
  }

  val sampleInput = readInput("Day02_test")

  fun part1(lines: List<String>): Int {
    return lines.map { it.words().mapToInts() }.count(::isReportSafe)
  }

  fun part2(lines: List<String>): Int {
    return lines.map { it.words().mapToInts() }.count(::isReportSafeWithDampener)
  }

  part1(sampleInput).displayWith("Part1")
  part2(sampleInput).displayWith("Part2")
}
