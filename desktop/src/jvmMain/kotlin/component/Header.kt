package component

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import navigations.NavController


@Composable
fun Header(navController: NavController) {
    var showConfirmDialog by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }

    //animation
    var visible by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(end = 10.dp, top = 5.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Icon",
                modifier = Modifier.size(50.dp),
                tint = Color(0xFFFDBDFEA)
            )
        }
        Spacer(modifier = Modifier.width(24.dp))



        Card {
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Logout",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = "Profile",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    DropdownMenuItem(
                        onClick = { showConfirmDialog = true },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = "Logout",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 4.dp).clickable { }
                        )

                    }
                }

        }


    }
    if (showConfirmDialog) {
        ConfirmationDialog(
            title = "Logout",
            message = "Are You sure you want to Logout?",
            onConfirm = {
                logout(navController)
            },
            onCancel = { showConfirmDialog = false }
        )
    }
}

