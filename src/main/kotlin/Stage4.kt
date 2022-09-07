val TERMS = mutableSetOf<String>()
val DEFINITIONS = mutableMapOf<String, String>()

fun getTerm(): String {
    val answer = readln()
    LOG_INFO += "$answer\n"
    return if (TERMS.contains(answer)) {
        println("The card \"$answer\" already exists.\n")
        LOG_INFO += "The card \"$answer\" already exists.\n\n"
        ""
    } else {
        TERMS.add(answer)
        answer
    }
}

fun getDefinition(term: String): String {
    val answer = readln()
    LOG_INFO += "$answer\n"
    return if (DEFINITIONS.contains(answer)) {
        println("The definition \"$answer\" already exists.\n")
        LOG_INFO += "The definition \"$answer\" already exists.\n\n"
        ""
    } else {
        DEFINITIONS[answer] = term
        answer
    }
}

fun checkAnswer(answer: String, card: Card) =
    if (answer == card.definition) {
        "Correct!"
    } else {
        CARDS[CARDS.indexOf(card)].errors++
        if (DEFINITIONS.contains(answer)) {
            "Wrong. The right answer is \"${card.definition}\", but your definition is correct for " +
                "\"${DEFINITIONS[answer]}\"."
        } else {
            "Wrong. The right answer is \"${card.definition}\"."
        }
    }