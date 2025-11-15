package com.shevelev.jpcanilations.features.circlediagram

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CircleDiagram(
    modifier: Modifier = Modifier,
    arcs: List<Arc>,
    animated: Boolean = true,
    style: DrawStyle = Stroke(50.0f),
    animationDurationMillis: Int = 500,
) {
    val animateFloat = remember {
        Animatable(if (animated) 0f else 1f)
    }

    LaunchedEffect(animateFloat) {
        animateFloat.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = if (animated) animationDurationMillis else 0,
                easing = LinearEasing,
            ))
    }

    Box(
        modifier = modifier
            .drawWithCache {
                val radius = size.width / 2
                val topLeft = Offset(size.width / 2 - radius, size.height / 2 - radius)

                onDrawBehind {
                    for (i in arcs.indices) {
                        val arc = arcs[i]

                        if (arc.from >= animateFloat.value) {
                            continue
                        }

                        if (arc.to <= animateFloat.value) {
                            drawArc(
                                color = arc.color,
                                startAngle = 360f*arc.from - 90,
                                sweepAngle = 360f*(arc.to - arc.from),
                                useCenter = false,
                                topLeft = topLeft,
                                size = size,
                                style = style
                            )
                        }

                        drawArc(
                            color = arc.color,
                            startAngle = 360f*arc.from - 90,
                            sweepAngle = 360f*(animateFloat.value - arc.from),
                            useCenter = false,
                            topLeft = topLeft,
                            size = size,
                            style = style
                        )
                    }
                }
            }
    )
}
