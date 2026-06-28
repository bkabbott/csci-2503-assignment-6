package edu.georgiasouthern.jetpackcompose.ui.ui.swipe

sealed class CardAction {
    data object Accept : CardAction()
    data object Reject : CardAction()
}
