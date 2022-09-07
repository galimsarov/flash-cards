import java.io.File

fun hardestCards() {
    val maxErrorCards = getMaxErrorCards()
    when (maxErrorCards.size) {
        0 -> {
            println("There are no cards with errors.\n")
            LOG_INFO += "There are no cards with errors.\n\n"
        }
        1 -> {
            println(
                "The hardest card is \"${maxErrorCards[0].term}\". " +
                    "You have ${maxErrorCards[0].errors} errors answering it.\n"
            )
            LOG_INFO += "The hardest card is \"${maxErrorCards[0].term}\". " +
                "You have ${maxErrorCards[0].errors} errors answering it.\n\n"
        }
        else -> {
            println("The hardest cards are " +
                "${maxErrorCards.map { it.term }.joinToString(", ") { "\"$it\"" }}. " +
                "You have ${maxErrorCards[0].errors} errors answering them.\n"
            )
            LOG_INFO += "The hardest cards are " +
                "${maxErrorCards.map { it.term }.joinToString(", ") { "\"$it\"" }}. " +
                "You have ${maxErrorCards[0].errors} errors answering them.\n\n"
        }
    }
}

private fun getMaxErrorCards(): List<Card> {
    return if (CARDS.isEmpty()) {
        listOf()
    } else {
        val maxErrors = CARDS.maxOf { it.errors }
        if (maxErrors == 0) {
            listOf()
        } else {
            CARDS.filter { it.errors == maxErrors }
        }
    }
}

fun resetStats() {
    CARDS.forEach { it.errors = 0 }
    println("Card statistics have been reset.\n")
    LOG_INFO += "Card statistics have been reset.\n\n"
}

fun log() {
    try {
        println("File name:")
        LOG_INFO += "File name:\n"
        val fileName = readln()
        LOG_INFO += "$fileName\n"
        val file = File(fileName)
        file.writeText(LOG_INFO)
        println("The log has been saved.\n")
        LOG_INFO += "The log has been saved.\n\n"
    } catch (_: Exception) {
    }
}