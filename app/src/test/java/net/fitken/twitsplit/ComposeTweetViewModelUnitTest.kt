package net.fitken.twitsplit

import net.fitken.twitsplit.composetweet.ComposeTweetViewModel
import org.junit.Assert.*
import org.junit.Test

class ComposeTweetViewModelUnitTest {

    private var composeTweetViewModel: ComposeTweetViewModel = ComposeTweetViewModel()

    @Test
    fun splitMessage_lessThan50_returnTrue() {
        assertEquals(composeTweetViewModel.splitMessage("I can't believe"), arrayListOf("I can't believe"))
    }

    @Test
    fun splitMessage_moreThan50_returnEquals() {
        assertEquals(composeTweetViewModel.splitMessage("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."),
                arrayListOf("1/2 I can't believe Tweeter now supports chunking", "2/2 my messages, so I don't have to do it myself."))
    }
}