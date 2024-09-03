import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 4.dp
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val infiniteTransition = androidx.compose.animation.core.rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, delayMillis = 0, easing = LinearEasing)
        ), label = ""
    )

    val animatedColor by animateColorAsState(
        targetValue = if (animationPlayed) color.copy(alpha = 0.3f) else color,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, delayMillis = 0, easing = LinearEasing)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Canvas(modifier = Modifier
            .size(size)
            .align(Alignment.Center)
        ) {
            drawArc(
                color = animatedColor,
                startAngle = -90f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
    }

    animationPlayed = true
}