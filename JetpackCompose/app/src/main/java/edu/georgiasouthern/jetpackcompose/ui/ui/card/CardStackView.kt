package edu.georgiasouthern.jetpackcompose.ui.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import edu.georgiasouthern.jetpackcompose.ui.data.model.CardItem
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.CardAction
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.Direction
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.SwipeableCardState
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.rememberSwipeableCardState

@Composable
fun CardStackView(
    cards: List<CardItem>,
    onAction: (CardItem, CardAction) -> Unit
) {
    // Create all states up front so they survive recomposition as currentIndex changes.
    // Mirroring DatingApp's approach: pair each card with a persistent state object.
    val states = cards.reversed().map { it to rememberSwipeableCardState() }

    var hint by remember { mutableIntStateOf(0) } // unused count, forces recompose on swipe

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        val allSwiped = states.all { it.second.swipedDirection != null }

        if (allSwiped) {
            CompletionState()
            return@Box
        }

        // Render in reversed order so the first card sits on top (highest zIndex).
        states.forEach { (card, state) ->
            if (state.swipedDirection == null) {
                // Determine this card's visual depth position among still-unswipped cards.
                val unswiped = states.filter { it.second.swipedDirection == null }
                val stackIndex = unswiped.indexOfFirst { it.first.id == card.id }
                val isTop = stackIndex == 0

                val scale = when (stackIndex) {
                    0 -> 1.00f
                    1 -> 0.95f
                    2 -> 0.90f
                    else -> 0.85f
                }
                val yOffsetDp = (stackIndex * 10).dp

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex((unswiped.size - stackIndex).toFloat())
                        .scale(scale)
                        .padding(top = yOffsetDp)
                ) {
                    SwipeCard(
                        card = card,
                        state = state,
                        onAction = if (isTop) { action ->
                            onAction(card, action)
                            hint++ // trigger recompose so stack re-evaluates depth
                        } else {
                            {}  // background cards are visual-only
                        }
                    )
                }

                // Track programmatic swipes (button-triggered) in same LaunchedEffect pattern.
                LaunchedEffect(card.id, state.swipedDirection) {
                    // intentionally empty — swipedDirection drives recompose above
                }
            }
        }
    }
}

@Composable
private fun CompletionState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Complete",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "All sections reviewed!",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
