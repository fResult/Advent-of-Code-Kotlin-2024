/**
 * [Day 3: Mull It Over](https://adventofcode.com/2024/day/3)
 */
fun main() {
  val MAIN_INPUT_FILE = "Day03_test"
  val EXPECTED_PART_1_RESULT = 173529487
  val EXPECTED_PART_2_RESULT = 99532691
  val SAMPLE_INPUT_FILE = "Day03_sample"
  val EXPECTED_SAMPLE_PART_1_RESULT = 161
  val EXPECTED_SAMPLE_PART_2_RESULT = 48

  val times: (Int, Int) -> Int = { x, y -> x * y }
  fun matchedResultToMultiplicationResult(matchResult: MatchResult): Int {
    val patternToReplace = "[mul(|)]".toRegex()
    val delimeter = ","
    if (matchResult.value.isEmpty())
      return 0
    val test = matchResult.value.replace(patternToReplace, "")
//    test.displayWith("test")
    return test.split(delimeter).mapToInts().fold(1, times)
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

  /**
   * This solution is from:
   * [Youtube - Advent of Code 2024 in Kotlin. Day 3.](https://www.youtube.com/watch?v=ubz-Hm8Zci8)
   */
  fun part2(lines: List<String>): Int {
//    val mulFunction = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
//    val positiveLookMullBehindDo = "(?<=do\\(\\).*)$mulFunction".toRegex()
//    val negativeLookMullBehindDont = "(?<!don't\\(\\).*)$mulFunction".toRegex()
//    val mulPattern = "${positiveLookMullBehindDo}|${negativeLookMullBehindDont}".toRegex()
//    val sumAfterMatchPattern = matchPatternThenGetSums(mulPattern)
//
//    return lines.sumOf(sumAfterMatchPattern)
    val mulPattern = "mul\\(\\d{1,3},\\d{1,3}\\)|do(n't)?\\(\\)".toRegex()
    val all = lines.flatMap { line ->
      mulPattern.findAll(line).map { instruction(it.value) }
    }

    all.displayWith("All")

    var enabled = true
    var acc = 0

    all.forEach { instruction ->
      when (instruction) {
        is Do -> enabled = true
        is Dont -> enabled = false
        is Mull  -> {
          if (!enabled) println("Encounter disbled $instruction")
          else {
            val (x, y) = instruction
            acc += x * y
          }
        }
      }
    }

    return acc
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
  check(EXPECTED_PART_2_RESULT == part2Result)
}

// Related functions goes here
private sealed interface Instruction
private data object Do : Instruction
private data object Dont : Instruction
private data class Mull(val x: Int, val y: Int) : Instruction

private fun instruction(str: String): Instruction {
  return when {
    str == "do()" -> Do
    str == "don't()" -> Dont
    str.startsWith("mul") -> {
      val (x, y) = str.removeSurrounding("mul(", ")").split(",").mapToInts()
      Mull(x, y)
    }

    else -> error("Unknown string: ${str}")
  }
}
