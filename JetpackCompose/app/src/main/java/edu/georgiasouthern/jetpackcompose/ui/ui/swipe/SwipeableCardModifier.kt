package edu.georgiasouthern.jetpackcompose.ui.ui.swipe

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

fun Modifier.swipableCard(
    state: SwipeableCardState,
    onSwiped: (Direction) -> Unit,
    onSwipeCancel: () -> Unit = {}
): Modifier = pointerInput(Unit) {
    coroutineScope {
        detectDragGestures(
            onDragCancel = {
                launch {
                    state.reset()
                    onSwipeCancel()
                }
            },
            onDrag = { change, dragAmount ->
                launch {
                    // Accumulate from targetValue so rapid drags don't lose distance.
                    val original = state.offset.targetValue
                    val summed   = original + dragAmount
                    val newValue = Offset(
                        x = summed.x.coerceIn(-state.maxWidth,  state.maxWidth),
                        y = summed.y.coerceIn(-state.maxHeight, state.maxHeight)
                    )
                    if (change.positionChange() != Offset.Zero) change.consume()
                    state.drag(newValue.x, newValue.y)
                }
            },
            onDragEnd = {
                launch {
                    val offsetX = state.offset.targetValue.x
                    if (abs(offsetX) > state.maxWidth / 4) {
                        val direction = if (offsetX > 0) Direction.Right else Direction.Left
                        state.swipe(direction)
                        onSwiped(direction)
                    } else {
                        state.reset()
                        onSwipeCancel()
                    }
                }
            }
        )
    }
}.graphicsLayer {
    translationX = state.offset.value.x
    translationY = state.offset.value.y
    rotationZ    = (state.offset.value.x / 60f).coerceIn(-40f, 40f)
}
