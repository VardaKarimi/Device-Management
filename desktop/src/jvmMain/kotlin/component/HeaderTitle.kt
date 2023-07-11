package component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HeaderTitle(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF27374D)
        )
    }
}