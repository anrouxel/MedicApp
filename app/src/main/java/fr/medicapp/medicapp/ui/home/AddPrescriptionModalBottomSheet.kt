package fr.medicapp.medicapp.ui.home

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ui.theme.EUGreen100
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun AddPrescriptionModalBottomSheet(showBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    val context = LocalContext.current

    var hasImage by remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {sucess: Boolean ->
            hasImage = sucess
        }
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            if (cameraPermissionState.status.isGranted) {
                if (hasImage) {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = "OCR"
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    IconButton(
                        onClick = {
                            /*scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onDismissRequest()
                                }
                            }*/

                            val uri: Uri = context.createImageFile()
                            imageUri = uri
                            cameraLauncher.launch(uri)
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Camera,
                            contentDescription = "Camera",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(50.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    IconButton(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onDismissRequest()
                                }
                            }

                            imagePicker.launch("image/*")
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Image,
                            contentDescription = "Gallery",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(50.dp)
                        )
                    }
                }
            } else {
                Column {
                    Text(text = "Nous avons besoin de la permission pour accéder à la caméra")
                    Button(
                        onClick = {
                            cameraPermissionState.launchPermissionRequest()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EUGreen100
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Autoriser")
                    }
                }
            }
        }
    }
}

fun Context.createImageFile(): Uri {
    val provider: String = "${applicationContext.packageName}.fileprovider"
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"

    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        cacheDir      /* directory */
    ).apply {
        createNewFile()
    }

    return FileProvider.getUriForFile(applicationContext, provider, image)
}

@Preview
@Composable
private fun AddPrescriptionModalBottomSheetPreview() {
    AddPrescriptionModalBottomSheet(showBottomSheet = true, onDismissRequest = {})
}