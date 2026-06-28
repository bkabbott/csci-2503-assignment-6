package edu.georgiasouthern.jetpackcompose.ui.ui.swipe

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

class SwipeableCardState(
    internal val maxWidth: Float,
    internal val maxHeight: Float
) {
    val offset = Animatable(Offset.Zero, Offset.VectorConverter)

    var swipedDirection: Direction? by mutableStateOf(null)
        private set

    // Uses animateTo (not snapTo) so the card follows the finger with a slight
    // spring feel rather than jumping frame-to-frame.
    internal suspend fun drag(x: Float, y: Float) {
        offset.animateTo(Offset(x, y))
    }

    internal suspend fun reset() {
        offset.animateTo(Offset.Zero, tween(400))
    }

    suspend fun swipe(direction: Direction, animationSpec: AnimationSpec<Offset> = tween(400)) {
        val endX = maxWidth * 1.5f
        val endY = maxHeight
        when (direction) {
            Direction.Left  -> offset.animateTo(Offset(x = -endX, y = offset.value.y), animationSpec)
            Direction.Right -> offset.animateTo(Offset(x = endX,  y = offset.value.y), animationSpec)
        }
        swipedDirection = direction
    }
}

@Composable
fun rememberSwipeableCardState(): SwipeableCardState {
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    val maxWidth  = with(density) { config.screenWidthDp.dp.toPx() }
    val maxHeight = with(density) { config.screenHeightDp.dp.toPx() }
    return remember { SwipeableCardState(maxWidth, maxHeight) }
}
