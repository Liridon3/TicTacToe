import java.util.Arrays

fun main(args: Array<String>) {
    var initial = "_________"
    var gameGrid = Array(3) { Array(3) {""} }
    fillGrid(gameGrid, initial)
    printGrid(gameGrid)
    loop@ while (true)
    {
        playerMove(gameGrid)
        printGrid(gameGrid)
        var result = analyze(gameGrid)
        if (result == "X wins") {
            break
        } else if (result == "O wins") {
            break
        } else if (result == "Draw") {
            break
        }
        else {
            continue@loop
        }
    }
    println(analyze(gameGrid))




}

fun fillGrid (gameGrid: Array<Array<String>>, initial: String): Array<Array<String>> {
    var beg = 0
    for (r in 0 .. 2) {
        for (c in 0..2) {
            gameGrid[r][c] = initial[beg].toString()
            beg++
        }
    }
    return gameGrid
}

fun printGrid (gameGrid: Array<Array<String>>) {
    println("---------")
    println("| ${gameGrid[0][0]} ${gameGrid[0][1]} ${gameGrid[0][2]} |")
    println("| ${gameGrid[1][0]} ${gameGrid[1][1]} ${gameGrid[1][2]} |")
    println("| ${gameGrid[2][0]} ${gameGrid[2][1]} ${gameGrid[2][2]} |")
    println("---------")
}

fun printAndEnter(info: String): String {
    println(info)
    return readln()
}

fun playerMove(gameGrid: Array<Array<String>>) {
    var column: Int?
    var row: Int?
    while (true) {
        var moveList: String = printAndEnter("Your move?")
        column = moveList.substringAfter(" ").toIntOrNull()
        row = moveList.substringBefore(" ").toIntOrNull()
        if (column == null || row == null) {
            println("You should enter numbers!")
        } else if (column !in 1..3 || row !in 1..3) {
            println("Coordinates should be from 1 to 3!")
        } else if (gameGrid[row - 1][column - 1] == "X" || gameGrid[row - 1][column - 1] == "O") {
            println("This cell is occupied! Choose another one!")
        } else break
    }
    if (row != null) {
        if (column != null) {
            if (Arrays.deepToString(gameGrid).count{it == 'X'} > Arrays.deepToString(gameGrid).count{it == 'O'})
            {
                gameGrid[row - 1][column - 1] = "O"
            } else {
                gameGrid[row - 1][column - 1] = "X"
            }
        }
    }
}

fun analyze(gameGrid: Array<Array<String>>): String {
    var input = Arrays.deepToString(gameGrid)
    input = input.replace("[", "")
    input = input.replace(",", "")
    input = input.replace("]", "")
    input = input.replace(" ", "")
    var xWins: Boolean =
        input.subSequence(0..2) == "XXX" || input.subSequence(3..5) == "XXX" || input.subSequence(6..8) == "XXX" ||
                (input[0] == 'X' && input[3] == 'X' && input[6] == 'X') ||
                (input[1] == 'X' && input[4] == 'X' && input[7] == 'X') ||
                (input[2] == 'X' && input[5] == 'X' && input[8] == 'X') ||
                (input[0] == 'X' && input[4] == 'X' && input[8] == 'X') ||
                (input[2] == 'X' && input[4] == 'X' && input[6] == 'X')
    var oWins: Boolean =
        input.subSequence(0..2) == "OOO" || input.subSequence(3..5) == "OOO" || input.subSequence(6..8) == "OOO" ||
                (input[0] == 'O' && input[3] == 'O' && input[6] == 'O') ||
                (input[1] == 'O' && input[4] == 'O' && input[7] == 'O') ||
                (input[2] == 'O' && input[5] == 'O' && input[8] == 'O') ||
                (input[0] == 'O' && input[4] == 'O' && input[8] == 'O') ||
                (input[2] == 'O' && input[4] == 'O' && input[6] == 'O')
    val analyzeVar: String = if (xWins) {"X wins"}
                                else if (oWins) {"O wins"}
                                else if (input.count { it == '_' } == 0) {"Draw"}
                                else "false"
    return analyzeVar

}
