
object DateFormatter {
    fun formatTimestamp(timestamp: String): String {
        val parts = timestamp.split("T")
        val date = parts[0].split("-").reversed().joinToString("/")
        val time = parts[1].substring(0, 5)
        return "$time de $date"
    }
}