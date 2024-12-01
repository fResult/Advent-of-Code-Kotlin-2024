/**
 * Day 2: xxxxx
 * https://adventofcode.com/2024/day/2
 */
fun main() {
  val EXPECTED_PART_1_RESULT = 1
  val EXPECTED_PART_2_RESULT = 1
  val EXPECTED_SAMPLE_PART_1_RESULT = 1
  val EXPECTED_SAMPLE_PART_2_RESULT = 1

  fun part1(input: List<String>): Int {
    return input.size
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  // Test if implementation meets criteria from the description, like:
  val sampleFile = "Day02_sample"
  check(part1(readInput(sampleFile)) == EXPECTED_SAMPLE_PART_1_RESULT)
  check(part2(readInput(sampleFile)) == EXPECTED_SAMPLE_PART_2_RESULT)

  val fileName =
//    sampleFile
    "Day02_test"

  // Or read a large test input from the `src/Day01_test.txt.txt.txt` file:
  val testInput = readInput(fileName)
  val part1Result = part1(testInput)
  val expectedPart1Result =
//    EXPECTED_SAMPLE_PART_1_RESULT
    EXPECTED_PART_1_RESULT
  check(expectedPart1Result == part1Result)

  val part2Result = part2(testInput)
  val expectedPart2Result =
//    EXPECTED_SAMPLE_PART_2_RESULT
    EXPECTED_PART_2_RESULT
  check(expectedPart2Result == part2Result)

  // Read the input from the `src/Day01_test.txt` or `src/Day01_sample.txt` file.
  part1Result.displayWith("Part 1")
  part2(testInput).displayWith("Part 2")
}