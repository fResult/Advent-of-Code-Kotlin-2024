/**
 * [Day 4: xxxxx](https://adventofcode.com/2024/day/3)
 */
fun main() {
  val MAIN_INPUT_FILE = "Day04_test"
  val EXPECTED_PART_1_RESULT = 1
  val EXPECTED_PART_2_RESULT = 1
  val SAMPLE_INPUT_FILE = "Day04_sample"
  val EXPECTED_SAMPLE_PART_1_RESULT = 1
  val EXPECTED_SAMPLE_PART_2_RESULT = 1

  fun part1(input: List<String>): Int {
    return input.size
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  // Test if implementation meets criteria from the description (`src/Day04_sample.txt`), like:
  val sampleInput = readInput(SAMPLE_INPUT_FILE)
  val samplePart1Result = part1(sampleInput)
  val samplePart2Result = part2(sampleInput)
  samplePart1Result.displayWith("Sample Part 1")
  samplePart2Result.displayWith("Sample Part 2")
  check(EXPECTED_SAMPLE_PART_1_RESULT == samplePart1Result)
  check(EXPECTED_SAMPLE_PART_2_RESULT == samplePart2Result)

  // Or read a large test input from the `src/Day04_test.txt` file:
  val testInput = readInput(MAIN_INPUT_FILE)
  val part1Result = part1(testInput)
  val part2Result = part2(testInput)
  part1Result.displayWith("Part 1")
  part2Result.displayWith("Part 2")
  check(EXPECTED_PART_1_RESULT == part1Result)
  check(EXPECTED_PART_2_RESULT == part2Result)
}

// Related functions goes here
