package com.shevelev.jpcanilations.features.glitch

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shevelev.jpcanilations.ui.theme.Cyan500
import com.shevelev.jpcanilations.ui.theme.Pink500
import com.shevelev.jpcanilations.ui.theme.Red500
import com.shevelev.jpcanilations.ui.theme.Yellow500
import com.shevelev.jpcanilations.ui.theme.Zinc900
import com.shevelev.jpcanilations.ui.theme.Zinc950
import kotlin.random.Random

/**
 * Based on this article
 */
@Composable
internal fun GlitchButton(
    modifier: Modifier = Modifier,
) {
    var key by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        key = Random.nextInt()
                    }
                )
            }
            .pointerHoverIcon(PointerIcon.Hand)
            .glitchEffect(
                key,
                remember { listOf(Cyan500, Yellow500, Pink500) })
            .padding(4.dp)
            .rings(
                ringSpace = 2.dp
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Zinc950, Zinc900)
                ),
                shape = CutCornerShape(20),
            )
            // Padding around the inner compose
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .then(modifier)
    ) {
        Text(
            text = "Tap to Glitch",
            style = TextStyle(
                color = Red500,
            ),
        )
    }
}

@Preview()
@Composable
fun GlitchButtonPreview() {
    GlitchButton()
}