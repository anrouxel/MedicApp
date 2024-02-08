package fr.medicapp.medicapp.ui.screen.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.entity.medication.Medication
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate

@Composable
fun NotificationHome(
    notifications: List<Notification>,
    onNotificationClick: () -> Unit = {},
    onAddNotificationClick: () -> Unit = {}
) {
    Home(
        title = "Notification",
        floatingActionButtonOnClick = onAddNotificationClick,
        floatActionButtonIcon = Icons.Default.DocumentScanner
    ) {
        if (notifications.isEmpty()) {
            NoNotificationAvailable()
        } else {
            NotificationList(
                notifications = notifications,
                onNotificationClick = onNotificationClick
            )
        }
    }
}

@Composable
fun NotificationList(
    notifications: List<Notification>,
    onNotificationClick: () -> Unit = {}
) {
    notifications.forEach { notification ->
        NotificationItem(
            notification = notification,
            onNotificationClick = onNotificationClick
        )
    }
}

@Composable
fun NotificationItem(
    notification: Notification,
    onNotificationClick: () -> Unit
) {
    ReusableElevatedCard(
        onClick = onNotificationClick
    ) {
        CardContent(
            title = notification.treatment?.medication?.name ?: "",
        )
    }
}

@Composable
fun NoNotificationAvailable() {
    Text(
        text = "Vous n'avez pas d'ordonnances.\n" + "Pour en ajouter, cliquez sur le bouton en bas",
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize().wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun NotificationHomePreview() {
    val notification = Notification(
        treatment = Treatment(
            medication = Medication(
                name = "Doliprane"
            )
        ),
    )

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        NotificationHome(
            notifications = listOf(notification)
        )
    }
}

@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun NotificationHomeDarkPreview() {
    val notification = Notification(
        treatment = Treatment(
            medication = Medication(
                name = "Doliprane"
            )
        ),
    )

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        NotificationHome(
            notifications = listOf(notification)
        )
    }
}

@Preview(name = "Light Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoNotificationAvailablePreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        NotificationHome(
            notifications = emptyList()
        )
    }
}

@Preview(name = "Dark Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoNotificationAvailableDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        NotificationHome(
            notifications = emptyList()
        )
    }
}
