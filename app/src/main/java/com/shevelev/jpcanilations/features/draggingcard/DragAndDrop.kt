package com.shevelev.jpcanilations.features.draggingcard

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

internal val LocalDragTargetInfo = compositionLocalOf { DragTargetInfo() }

private const val DRAG_CONTENT_SCALE = 1f
private const val DRAG_CONTENT_RELATIVE_WIDTH = 0.45f

@Composable
internal fun LongPressDraggable(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val state = remember { DragTargetInfo() }
    CompositionLocalProvider(
        LocalDragTargetInfo provides state,
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            content()
            if (state.isDragging) {
                var targetSize by remember {
                    mutableStateOf(IntSize.Zero)
                }
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            val offset = (state.dragPosition + state.dragOffset)
                            scaleX = DRAG_CONTENT_SCALE
                            scaleY = DRAG_CONTENT_SCALE
                            alpha = if (targetSize == IntSize.Zero) 0f else .75f
                            translationX = offset.x.minus(targetSize.width / 2)
                            translationY = offset.y.minus(targetSize.height)
                        }
                        .onGloballyPositioned {
                            targetSize = it.size
                        },
                ) {
                    state.draggableComposable?.invoke(
                        Modifier.fillMaxWidth(DRAG_CONTENT_RELATIVE_WIDTH),
                    )
                }
            }
        }
    }
}

@Composable
fun <T> DragTarget(
    modifier: Modifier = Modifier,
    dataToDrop: T,
    content: @Composable ((Modifier) -> Unit),
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    Box(
        modifier = modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(onDragStart = {
                    currentState.dataToDrop = dataToDrop
                    currentState.isDragging = true
                    currentState.dragPosition = currentPosition + it
                    currentState.draggableComposable = content
                }, onDrag = { change, dragAmount ->
                    change.consume()
                    currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                }, onDragEnd = {
                    currentState.isDragging = false
                    currentState.dragPosition = Offset.Zero
                    currentState.dragOffset = Offset.Zero
                    currentState.draggableComposable = null
                }, onDragCancel = {
                    currentState.isDragging = false
                    currentState.dragPosition = Offset.Zero
                    currentState.dragOffset = Offset.Zero
                    currentState.draggableComposable = null
                })
            },
    ) {
        content(Modifier.fillMaxWidth())
    }
}

@Composable
fun <T> DropTarget(
    modifier: Modifier = Modifier,
    onDrop: (T) -> Unit,
    content: @Composable() (BoxScope.(isInBound: Boolean) -> Unit),
) {
    val dragInfo = LocalDragTargetInfo.current

    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isInBound by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.onGloballyPositioned {
            it.boundsInWindow().let { rect ->
                isInBound = rect.contains(dragPosition + dragOffset)
            }
        },
    ) {
        if (isInBound && !dragInfo.isDragging) {
            @Suppress("UNCHECKED_CAST")
            (dragInfo.dataToDrop as? T)?.let {
                dragInfo.dataToDrop = null
                onDrop(it)
            }
        }

        content(isInBound)
    }
}

internal class DragTargetInfo {
    var isDragging: Boolean by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable (Modifier) -> Unit)?>(null)
    var dataToDrop by mutableStateOf<Any?>(null)
}
