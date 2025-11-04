package com.shevelev.jpcanilations.features.cardrotation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
internal fun CardFull(
    modifier: Modifier = Modifier,
    isRussianSideDefault: Boolean,
) {
    val startLanguage = if (isRussianSideDefault) Language.RUSSIAN else Language.ENGLISH
    var language by remember { mutableStateOf(startLanguage) }
    var initialLanguage by remember { mutableStateOf(startLanguage) }

    if (startLanguage != initialLanguage) {
        initialLanguage = startLanguage
        language = startLanguage
    }

    val rotation by animateFloatAsState(
        targetValue = if (language != startLanguage) 180f else 0f,
        animationSpec = tween(AnimationValues.CARD_ROTATION),
    )

    val animateFront by animateFloatAsState(
        targetValue = if (language != startLanguage) 1f else 0f,
        animationSpec = tween(
            durationMillis = AnimationValues.CARD_ROTATION,
            easing = AnimationValues.slowOutFastInEasing,
        ),
    )

    val animateBack by animateFloatAsState(
        targetValue = if (language == startLanguage) 1f else 0f,
        animationSpec = tween(
            durationMillis = AnimationValues.CARD_ROTATION,
            easing = AnimationValues.slowOutFastInEasing,
        ),
    )

    Card(
        modifier = modifier
            .height(250.dp)
            .padding(horizontal = 32.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            },
        shape = RoundedCornerShape(15.dp),
        onClick = {
            language = if (language == Language.RUSSIAN) Language.ENGLISH else Language.RUSSIAN
        },
    ) {
        CardSide(
            text = if (language == Language.RUSSIAN) {
                "Fire roared through the bifurcated city of Ankh-Morpork."
            } else {
                "Огонь бушевал в разделенным на две части городе Анк-Морпорке."
            },
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = if (language == startLanguage) animateBack else animateFront
                    rotationY = if (language == startLanguage) 0f else 180f
                },
        )
    }
}

@Composable
private fun CardSide(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(16.dp),
    ) {
        Text(text, modifier = Modifier.align(Alignment.Center))
    }
}

private enum class Language {
    RUSSIAN,
    ENGLISH
}

object AnimationValues {
    const val CARD_ROTATION = 400

    // This easing speeds up quickly and slows down gradually
    val slowOutFastInEasing: Easing = CubicBezierEasing(1.0f, 0.2f, 0f, 0.4f)
}
