package fr.medicapp.medicapp.ui.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    title: String,
    floatingActionButtonOnClick: () -> Unit,
    floatActionButtonIcon: ImageVector,
    content: @Composable () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        text = title,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = floatingActionButtonOnClick,
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    imageVector = floatActionButtonIcon,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            content()
        }
    }
}

@Preview(name = "Light Theme")
@Composable
private fun HomePreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        Home(
            title = "Traitements",
            floatingActionButtonOnClick = {},
            floatActionButtonIcon = Icons.Filled.DocumentScanner
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun HomeDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        Home(
            title = "Traitements",
            floatingActionButtonOnClick = {},
            floatActionButtonIcon = Icons.Filled.DocumentScanner
        )
    }
}
