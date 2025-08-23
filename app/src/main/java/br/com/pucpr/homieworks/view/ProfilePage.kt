package br.com.pucpr.homieworks.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.view.util.InputText
import br.com.pucpr.homieworks.view.util.SessionHeader
import br.com.pucpr.homieworks.ui.theme.yellow
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import br.com.pucpr.homieworks.navigation.Screen
import br.com.pucpr.homieworks.util.FirebaseAuthService
import br.com.pucpr.homieworks.view.util.GenericButton
import br.com.pucpr.homieworks.view.util.GenericPage
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.util.SessionManager
import br.com.pucpr.homieworks.viewmodel.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfilePage(
   viewModel: ProfileViewModel,
   navController: NavController
) {
    var option by remember { mutableStateOf("profile") }

    viewModel.loadProfileInfo()

    GenericPage(
        { ProfileHeader(
            onProfileIconClick = {
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.Profile.route) { inclusive = true }
                }
            },
            onMenuOptionSelected = { selected ->
                option = selected
                when(option) {
                    "feed" -> { navController.navigate(Screen.Feed.route) }
                    "myjobs" -> { navController.navigate(Screen.MyJobs.route) }
                    "acceptedjobs" -> { navController.navigate(Screen.Accepted.route) }
                }
            }
        ) },
        { ProfileContent(viewModel) },
        { ProfileFooter(
            onLogoutConfirmed = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Profile.route) { inclusive = true }
                }
            },
            viewModel = viewModel)
        }
    )
}

@Composable
fun ProfileHeader(
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
) {
    val fontColor = Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            SessionHeader(
                text = "Profile",
                onProfileIconClick = onProfileIconClick,
                onMenuIconClick = onMenuOptionSelected
            )
//            Text(
//                text="Helpedits:",
//                style = MaterialTheme.typography.titleMedium,
//                color = fontColor
//            )
//            Text(
//                text = SessionManager.sessionUser!!.helpedits.toString(),
//                style = MaterialTheme.typography.titleLarge,
//                color = yellow,
//                fontWeight = FontWeight.Bold
//            )
            Text(
                text="Trabalhos realizados:",
                style = MaterialTheme.typography.titleMedium,
                color = fontColor
            )
            Text(
                text = SessionManager.sessionUser!!.worksDone.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = yellow,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Image(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Imagem de perfil",
                modifier = Modifier.size(140.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileContent(viewModel: ProfileViewModel) {
    val fontColor = darkCean
    val backgroundColor = superLightCean

    InputText(
        label = "Nome",
        value = viewModel.user.name,
        onValueChange = { viewModel.updateName(it) },
        leadingIcon =  {Icon(imageVector = Icons.Rounded.Face, contentDescription = "Ícone de pessoa", tint = fontColor)},
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Número de telefone",
        value = viewModel.user.phoneNumber,
        onValueChange = { viewModel.updatePhone(it) },
        leadingIcon = {Icon(imageVector = Icons.Rounded.Phone, contentDescription = "Ícone de telefone", tint = fontColor)},
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Email",
        value = viewModel.user.email,
        onValueChange = { viewModel.updateEmail(it) },
        leadingIcon = {Icon(imageVector = Icons.Rounded.Mail, contentDescription = "Ícone de email", tint = fontColor)},
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Senha",
        value = viewModel.user.userPassword.substring(0, 10),
        onValueChange = { viewModel.updatePassword(it) },
        leadingIcon = {Icon(imageVector = Icons.Rounded.Lock, contentDescription = "Ícone de cadeado", tint = fontColor)},
        isSecret = true,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
//    InputText(
//        label = "Endereço",
//        leadingIcon = {Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = "Ícone de telefone", tint = fontColor)},
//        isSecret = true,
//        backGroundColor = backgroundColor,
//        fontColor = fontColor
//    )
}

@Composable
fun ProfileFooter(
    onLogoutConfirmed: () -> Unit,
    viewModel: ProfileViewModel,
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showRemoveAccount by remember { mutableStateOf(false) }
    val profileSuccess = viewModel.profileSuccess
    val profileError = viewModel.profileError

    if (profileError != null) {
        Text(
            text = profileError,
            color = lightRed,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GenericButton(
                modifier = Modifier.weight(1f),
                text = "Sair",
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Ícone de saída",
                        tint = Color.White
                    )
                },
                onClick = { showLogoutDialog = true },
                containerColor = lightRed,
                textColor = Color.White
            )
            GenericButton(
                modifier = Modifier.weight(1f),
                text = "Salvar",
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = "Ícone de disquete",
                        tint = Color.White
                    )
                },
                containerColor = lightGreen,
                onClick = { viewModel.updateUserInfo() },
                textColor = Color.White
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            GenericButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Excluir conta",
                containerColor = Color.Black,
                icon = {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "Ícone de lixeira"
                    )
                },
                onClick = { showRemoveAccount = true },
                textColor = lightRed,

            )
        }
    }

    if (profileSuccess) {
        Toast.makeText(LocalContext.current, "Alterações realizadas com sucesso", Toast.LENGTH_SHORT).show()
        viewModel.loadProfileInfo()
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Sair do aplicativo") },
            text = { Text("Tem certeza que deseja sair da sua conta?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        FirebaseAuthService.logout()
                        showLogoutDialog = false
                        onLogoutConfirmed()
                    }
                ) { Text("Sim") }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showRemoveAccount) {
        AlertDialog(
            onDismissRequest = { showRemoveAccount = false },
            title = { Text("Excluir") },
            text = { Text("Tem certeza que deseja excluir essa conta?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.removeAccount()
                        showRemoveAccount = false
                        onLogoutConfirmed()
                    }
                ) { Text("Sim") }
            },
            dismissButton = {
                TextButton(onClick = { showRemoveAccount = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}