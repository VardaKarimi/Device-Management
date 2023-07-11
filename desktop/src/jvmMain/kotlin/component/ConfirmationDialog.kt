package component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(
        onCloseRequest = onCancel,
        title = "Are you sure?"
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFFDBDFEA))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp))
            {  Text(
                text = title,
                style = TextStyle(fontWeight = FontWeight.Bold),

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = message)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    CustomButton(
                        onClick = onConfirm,
                        modifier = Modifier.padding(end = 8.dp),
                        text = "Yes"
                    )
                    CustomButton(
                        onClick = onCancel,
                        text = "Cancel"
                    )
                }}

        }
    }
}

