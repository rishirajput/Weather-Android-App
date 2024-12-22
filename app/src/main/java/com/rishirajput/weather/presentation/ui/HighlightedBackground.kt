import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.presentation.ui.theme.highLightColor

fun Modifier.highlightedBackGround(color: Color = highLightColor) = this
    .background(color, shape = RoundedCornerShape(16.dp))