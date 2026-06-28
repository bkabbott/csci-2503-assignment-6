package edu.georgiasouthern.jetpackcompose.ui.ui.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.georgiasouthern.jetpackcompose.ui.data.model.CardItem
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.CardAction
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.Direction
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.SwipeableCardState
import edu.georgiasouthern.jetpackcompose.ui.ui.swipe.swipableCard
import kotlin.math.abs
import kotlin.math.min

@Composable
fun SwipeCard(
    card: CardItem,
    state: SwipeableCardState,
    onAction: (CardAction) -> Unit
) {
    val offsetX = state.offset.value.x
    val swipeProgress = min(abs(offsetX) / 150f, 1f)

    val borderColor by animateColorAsState(
        targetValue = when {
            offsetX > 50f -> Color(0xFF4CAF50).copy(alpha = swipeProgress)
            offsetX < -50f -> Color(0xFFF44336).copy(alpha = swipeProgress)
            else -> Color.Transparent
        },
        label = "borderColor"
    )

    val shape = RoundedCornerShape(20.dp)

    Card(
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = borderColor, shape = shape)
            .swipableCard(
                state = state,
                onSwiped = { direction ->
                    onAction(
                        if (direction == Direction.Right) CardAction.Accept
                        else CardAction.Reject
                    )
                }
            )
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = card.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Box {
                if (offsetX > 50f) {
                    Text(
                        text = "Accept",
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color(0xFF4CAF50).copy(alpha = swipeProgress)
                        )
                    )
                } else if (offsetX < -50f) {
                    Text(
                        text = "Reject",
                        color = Color(0xFFF44336),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color(0xFFF44336).copy(alpha = swipeProgress)
                        )
                    )
                }
            }
        }

        // Scrollable body
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(card.fields, key = { it.label }) { field ->
                CardFieldRow(field = field)
            }
        }
    }
}
