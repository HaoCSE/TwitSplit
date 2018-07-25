package net.fitken.twitsplit.app

const val MAX_MESSAGE_LENGTH = 50
fun splitMessage(message: String): ArrayList<String> {
    if (message.length > MAX_MESSAGE_LENGTH) {
        var numOfMessage: Int = Math.ceil((message.length.toDouble() / MAX_MESSAGE_LENGTH.toDouble())).toInt()

        val arrMessages: ArrayList<String> = ArrayList()

        var startIndex = 0
        var endIndex: Int
        var index = 1
        var additionLength = 0
        while (index <= numOfMessage) {
            var prefix = "$index/$numOfMessage"
            numOfMessage = Math.ceil((message.length.toDouble() + numOfMessage * prefix.length + 1 + additionLength) / MAX_MESSAGE_LENGTH.toDouble()).toInt()
            prefix = "$index/$numOfMessage"
            endIndex = startIndex + (MAX_MESSAGE_LENGTH - prefix.length)
            if (endIndex > message.length) {
                endIndex = message.length
            }

            val msg = message.substring(startIndex, endIndex)
            var lastSpaceIndex = startIndex + msg.lastIndexOf(" ")
            if (index == numOfMessage) {
                lastSpaceIndex = endIndex
            }
            additionLength = endIndex - lastSpaceIndex
            arrMessages.add("$prefix ${message.substring(startIndex, lastSpaceIndex).trim()}")
            startIndex = lastSpaceIndex
            index++
        }
        return arrMessages
    }
    return arrayListOf(message)
}