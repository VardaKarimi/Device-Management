package screens.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import database.httpClient.Device
import database.httpClient.PrintDevices
import navigations.NavController

@Composable
fun DropDownContent(
    devices: List<Device>,
    selectedDeviceType: String,
    onDeviceTypeSelected: (String) -> Unit,
    navController: NavController
) {
    var mExpanded by remember { mutableStateOf(false) }
    val mDevices = listOf("Laptops", "Desktops", "Keyboards", "Headphones", "Mouses")
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            readOnly = true,
            value = mSelectedText,
            onValueChange = { mSelectedText },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text("Select Devices") },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded }
                )
            }
        )

        DropdownMenu(
            expanded = mExpanded,
            focusable = false,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            mDevices.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                    onDeviceTypeSelected(label)
                }) {
                    Text(text = label)
                }
            }
        }

        if (selectedDeviceType.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            PrintDevices(
                initialDevices = devices,
                selectedDeviceType = selectedDeviceType,
                navController
            )
        }
    }
}

