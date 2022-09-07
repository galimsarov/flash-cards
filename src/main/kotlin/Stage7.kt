fun checkImportArg(args: Array<String>) {
    if (args.contains("-import")) {
        try {
            val fileName = args[args.indexOf("-import") + 1]
            importFromFile(fileName)
        } catch (_: Exception) {
        }
    }
}

fun checkExportArg(args: Array<String>) {
    if (args.contains("-export")) {
        try {
            val fileName = args[args.indexOf("-export") + 1]
            exportToFile(fileName)
        } catch (_: Exception) {
        }
    }
}