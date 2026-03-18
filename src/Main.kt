fun main() {
    listOf(
        "1 / 2 + 1 / 3",
        "3 / 4 - 2 / 5",
        "2 / 3 * 3 / 7",
        "5 / 6 / 2",
        "-3 / 5 + 4 / 9",
        "1 / 2 + 1 / 3 - 1 / 4",
        "(1 / 2 + 2 / 3) / (3 / 4 - 1 / 5)",
        "((1 + 2) / 3) * ((4 - 2) / 5)",
        "-(3 / 7 + 2 / 5) * (4 / 3)",
        "1 / (2 + 3 / (4 - 1))",
        "(1 / 2 + 1 / 3) * (3 / 4 - 2 / 5)",
        "(5 / 6 - 1 / 2) / (7 / 8 + 1 / 4)",
        "((1 / 2 + 1 / 3) * (2 / 5 - 1 / 7)) / (3 / 4)",
        "1 - 2 / 3 + 3 / 4 - 4 / 5",
        "((1/2)*(3/4)/(5/6)) + ((7/8)-(2/3))",
        "(1 / 2 / (3 / 4)) * ((5 / 6 + 7 / 8) / (9 / 10))",
        "-((1/2 + 2/3) * (3/4 - 5/6))",
        "((1 + 1/2) / (2 + 1/3)) * (3/4 + 1/5)",
        "1 / (2 / (3 / (4 / 5)))",
        "(1/2 + (2/3 * (3/4 - 1/5))) / ((1/6 + 2/7) * 3/8)"
    ).forEach { input ->
        val tokens = Tokenizer.tokenize(input)
        val expression = Parser.parse(tokens)
        val evaluation = Evaluator.evaluate(expression)

        println("INPUT: $input")
        println("TOKENS: $tokens")
        println("EXPRESSION: $expression")
        println("EVALUATION: $evaluation\n")
    }
}