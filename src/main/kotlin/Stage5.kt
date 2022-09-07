import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File

fun addCard() {
    println("The card:")
    LOG_INFO += "The card:\n"
    val term = getTerm()
    if (term.isNotBlank()) {
        println("The definition of the card:")
        LOG_INFO += "The definition of the card:\n"
        val definition = getDefinition(term)
        if (definition.isNotBlank()) {
            CARDS.add(Card(term, definition))
            println("The pair (\"$term\":\"$definition\") has been added.\n")
            LOG_INFO += "The pair (\"$term\":\"$definition\") has been added.\n\n"
        }
    }
}

fun removeCard() {
    println("Which card?")
    LOG_INFO += "Which card?\n"
    val term = readln()
    LOG_INFO += "$term\n"
    if (TERMS.contains(term)) {
        TERMS.remove(term)
        var definition = ""
        for (card in CARDS) {
            if (card.term == term) {
                definition = card.definition
                break
            }
        }
        DEFINITIONS.remove(definition)
        CARDS.remove(Card(term, definition))
        println("The card has been removed.\n")
        LOG_INFO += "The card has been removed.\n\n"
    } else {
        println("Can't remove \"$term\": there is no such card.\n")
        LOG_INFO += "Can't remove \"$term\": there is no such card.\n\n"
    }
}

fun import() {
    println("File name:")
    LOG_INFO += "File name:\n"
    val fileName = readln()
    LOG_INFO += "$fileName\n"
    importFromFile(fileName)
}

fun importFromFile(fileName: String) {
    try {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, Card::class.java)
        val cardsAdapter = moshi.adapter<List<Card?>>(type)

        val file = File(fileName)
        val json = file.readText()
        val fileCards = cardsAdapter.fromJson(json)
        if (fileCards != null) {
            for (fileCard in fileCards) {
                if (fileCard != null) {
                    if (!TERMS.contains(fileCard.term)) {
                        CARDS.add(fileCard)
                        TERMS.add(fileCard.term)
                        DEFINITIONS[fileCard.definition] = fileCard.term
                    } else {
                        var definitionToReplace = ""
                        for (card in CARDS) {
                            if (card.term == fileCard.term) {
                                definitionToReplace = card.definition
                                card.definition = fileCard.definition
                                break
                            }
                        }
                        DEFINITIONS[definitionToReplace] = fileCard.term
                    }
                }
            }
            println("${fileCards.size} cards have been loaded.\n")
            LOG_INFO += "${fileCards.size} cards have been loaded.\n\n"
        }
    } catch (_: Exception) {
        println("File not found.\n")
        LOG_INFO += "File not found.\n\n"
    }
}

fun export() {
    println("File name:")
    LOG_INFO += "File name:\n"
    val fileName = readln()
    LOG_INFO += "$fileName\n"
    exportToFile(fileName)
}

fun exportToFile(fileName: String) {
    try {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, Card::class.java)
        val cardsAdapter = moshi.adapter<List<Card?>>(type)
        val json = cardsAdapter.toJson(CARDS)

        val file = File(fileName)
        file.writeText(json)
        println("${CARDS.size} cards have been saved.\n")
        LOG_INFO += "${CARDS.size} cards have been saved.\n\n"
    } catch (_: Exception) {
    }
}

fun ask() {
    try {
        println("How many times to ask?")
        LOG_INFO += "How many times to ask?\n"
        val times = readln().toInt()
        LOG_INFO += "$times\n"
        for (i in 0 until times) {
            println("Print the definition of \"${CARDS[i].term}\":")
            LOG_INFO += "Print the definition of \"${CARDS[i].term}\":\n"
            val answer = readln()
            LOG_INFO += "$answer\n"
            val result = checkAnswer(answer, CARDS[i])
            println(result)
            LOG_INFO += "$result\n"
        }
        println()
        LOG_INFO += "$\n"
    } catch (_: Exception) {
    }
}