val CARDS = mutableListOf<Card>()
var LOG_INFO = ""

fun main(args: Array<String>) {
    checkImportArg(args)
    while (true) {
        println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        LOG_INFO += "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):\n"
        val answer = readln()
        LOG_INFO += "$answer\n"
        when (answer) {
            "add" -> addCard()
            "remove" -> removeCard()
            "import" -> import()
            "export" -> export()
            "ask" -> ask()
            "hardest card" -> hardestCards()
            "reset stats" -> resetStats()
            "log" -> log()
            else -> {
                println("Bye bye!")
                break
            }
        }
    }
    checkExportArg(args)
}