/**
 * [Day 3: Mull It Over](https://adventofcode.com/2024/day/3)
 */
fun main() {
  val MAIN_INPUT_FILE = "Day03_test"
  val EXPECTED_PART_1_RESULT = 173529487
  val EXPECTED_PART_2_RESULT = 6
  val SAMPLE_INPUT_FILE = "Day03_sample"
  val EXPECTED_SAMPLE_PART_1_RESULT = 161
  val EXPECTED_SAMPLE_PART_2_RESULT = 48

  val times: (Int, Int) -> Int = { x, y -> x * y }
  fun matchedResultToMultiplicationResult(matchResult: MatchResult): Int {
    val patternToReplace = "[mul(|)]".toRegex()
    val delimeter = ","
    return matchResult.value.replace(patternToReplace, "").split(delimeter).mapToInts().fold(1, times)
  }

  fun matchPatternThenGetSums(targetPattern: Regex): (String) -> Int {
    return { line ->
      targetPattern.findAll(line).map(::matchedResultToMultiplicationResult).sum()
    }
  }

  fun part1(lines: List<String>): Int {
    val mulPattern = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex()
    val sumAfterMatchPattern = matchPatternThenGetSums(mulPattern)

    return lines.sumOf(sumAfterMatchPattern)
  }

  fun part2(lines: List<String>): Int {
    val mulPattern = "(?<=do\\(\\).*)mul\\(\\d{1,3},\\d{1,3}\\)|(?<!don't\\(\\).*)mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
    val sumAfterMatchPattern = matchPatternThenGetSums(mulPattern)

    return lines.sumOf(sumAfterMatchPattern)
  }

  // Test if implementation meets criteria from the description (`src/Day03_sample.txt`), like:
  val sampleInput = readInput(SAMPLE_INPUT_FILE)
  val samplePart1Result = part1(sampleInput)
  val samplePart2Result = part2(sampleInput)
  samplePart1Result.displayWith("Sample Part 1")
  samplePart2Result.displayWith("Sample Part 2")
  check(EXPECTED_SAMPLE_PART_1_RESULT == samplePart1Result)
  check(EXPECTED_SAMPLE_PART_2_RESULT == samplePart2Result)

  // Or read a large test input from the `src/Day03_test.txt` file:
  val testInput = readInput(MAIN_INPUT_FILE)
  val part1Result = part1(testInput)
  val part2Result = part2(testInput)
  part1Result.displayWith("Part 1")
  part2Result.displayWith("Part 2")
  check(EXPECTED_PART_1_RESULT == part1Result)
//  check(EXPECTED_PART_2_RESULT == part2Result)
}

// Related functions goes here
