package fr.medicapp.medicapp.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun ButtonContent(
    text: String,
    icon: ImageVector? = null
) {
    Row(
        modifier = Modifier
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(100.dp)),
                imageVector = icon,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(name = "Light Theme")
@Composable
private fun ButtonContentPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ButtonContent(
            text = "Add",
            icon = Icons.Default.Add
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun ButtonContentDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ButtonContent(
            text = "Add",
            icon = Icons.Default.Add
        )
    }
}
