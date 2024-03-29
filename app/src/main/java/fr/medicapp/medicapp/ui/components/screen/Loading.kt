package fr.medicapp.medicapp.ui.components.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.button.ReusableButton
import fr.medicapp.medicapp.ui.components.text.ReusableTextLargeCard
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Loading(
    title: String,
    text: String = "Chargement en cours...",
    onClick: () -> Unit = {},
    snake: Boolean = true
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onSurface
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
        bottomBar = {
            if (snake) {
                Box(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableButton(
                        text = "Jouer au Snake pour patienter",
                        onClick = onClick
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(
                    state = rememberScrollState()
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReusableTextLargeCard(
                    modifier = Modifier,
                    value = text
                )
            }
        }
    }
}

@Preview(name = "Light Theme")
@Composable
private fun LoadingPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        Loading(
            title = "Traitements"
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun LoadingDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        Loading(
            title = "Traitements",
        )
    }
}
