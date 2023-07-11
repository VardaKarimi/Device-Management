package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color = Color(0xFFF27374D),
    contentColor: Color = Color(0xFFFE8AA42),
    elevation: ButtonElevation = ButtonDefaults.elevation(0.dp),
    shape: RoundedCornerShape = MaterialTheme.shapes.small as RoundedCornerShape,
    padding: Dp = 16.dp
) {
    Button(
        modifier = modifier.padding(horizontal = padding),
        onClick = onClick,
        shape = shape,
        elevation = elevation,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Box(modifier = Modifier.padding(horizontal = padding / 2)) {
            Text(
                text = text,
                style = MaterialTheme.typography.button,
                color = Color(0xFFFE8AA42)
            )
        }
    }
}
