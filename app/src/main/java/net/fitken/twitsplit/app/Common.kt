package net.fitken.twitsplit.app

const val MAX_MESSAGE_LENGTH = 50
fun splitMessage(message: String): ArrayList<String> {
    if (message.length > MAX_MESSAGE_LENGTH) {
        var numOfMessage = Math.round((message.length.toFloat() / MAX_MESSAGE_LENGTH.toFloat()))
        var arrMessages: ArrayList<String> = ArrayList()

        var startIndex = 0
        var endIndex = 0
        for (i in 1..numOfMessage) {
            var prefix = "$i/$numOfMessage"
            endIndex = startIndex + (MAX_MESSAGE_LENGTH - prefix.length)
            if (endIndex > message.length) {
                endIndex = message.length
            }

            var msg = message.substring(startIndex, endIndex)
            var lastSpaceIndex = startIndex + msg.lastIndexOf(" ")
            if (i == numOfMessage) {
                lastSpaceIndex = endIndex
            }
            arrMessages.add("$prefix ${message.substring(startIndex, lastSpaceIndex).trim()}")
            startIndex = lastSpaceIndex
        }
        return arrMessages
    }
    return arrayListOf(message)
}