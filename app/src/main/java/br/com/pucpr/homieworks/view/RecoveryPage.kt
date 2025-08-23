//package br.com.pucpr.homieworks.view
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.Send
//import androidx.compose.material.icons.filled.CheckCircle
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.material.icons.filled.LockReset
//import androidx.compose.material.icons.filled.Numbers
//import androidx.compose.material.icons.filled.Phone
//import androidx.compose.material.icons.filled.Save
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import br.com.pucpr.homieworks.navigation.Screen
//import br.com.pucpr.homieworks.view.util.GenericButton
//import br.com.pucpr.homieworks.view.util.InputText
//import br.com.pucpr.homieworks.ui.theme.darkCean
//import br.com.pucpr.homieworks.ui.theme.lightCean
//import br.com.pucpr.homieworks.ui.theme.lightGreen
//import br.com.pucpr.homieworks.ui.theme.lightRed
//import br.com.pucpr.homieworks.ui.theme.superLightCean
//import br.com.pucpr.homieworks.viewmodel.RecoveryViewModel
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun RecoveryPage(
//    viewModel: RecoveryViewModel,
//    navController: NavController,
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = superLightCean)
//            .padding(horizontal = 16.dp, vertical = 40.dp),
//        contentAlignment = Alignment.Center,
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            RecoveryHeader()
//            RecoveryContent(viewModel, {
//                navController.navigate(Screen.Login.route) {
//                    popUpTo(Screen.Recovery.route)
//                }
//            })
//            RecoveryFooter({
//                navController.navigate(Screen.Login.route) {
//                    popUpTo(Screen.Recovery.route) { inclusive = true }
//                }
//            })
//        }
//    }
//}
//
//@Composable
//fun RecoveryHeader() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.Top
//    ) {
//        Text(
//            modifier = Modifier.fillMaxWidth(),
//            text ="Recuperação de conta",
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            style = MaterialTheme.typography.headlineLarge,
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun RecoveryContent(viewModel: RecoveryViewModel, onRecoverySuccess: () -> Unit) {
//    var session by remember { mutableIntStateOf(1) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.9f)
//            .padding(16.dp)
//    ) {
//        Column(
//            modifier = Modifier.align(Alignment.Center),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//        ) {
//            when (session) {
//                1 -> RecoverySessionOne(
//                    onSessionChange = {
//                        viewModel.sendRecoverySMS(viewModel.recoveryPhone)
//                        session = it
//                    },
//                    viewModel = viewModel
//                )
//                2 -> RecoverySessionTwo(
//                    onSessionChange = { session = it },
//                    viewModel = viewModel
//                )
//                3 -> RecoverySessionThree(viewModel = viewModel, onRecoverySuccess = { onRecoverySuccess() })
//            }
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun RecoverySessionOne(onSessionChange: (Int) -> Unit, viewModel: RecoveryViewModel) {
//    val recoveryPhone = viewModel.recoveryPhone
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(30.dp)
//    ) {
//        Text(
//            text = "Confirme o celular de recuperação para recebimento do código:",
//            style = MaterialTheme.typography.bodyLarge,
//            color = Color.Black
//        )
////        Text(
////            text = "j***@gmail.com",
////            style = MaterialTheme.typography.bodyLarge,
////            fontStyle = FontStyle.Italic,
////            fontWeight = FontWeight.Bold,
////            color = Color.Black
////        )
//    }
//    InputText(
//        label = "Celular",
//        value = recoveryPhone,
//        onValueChange = { viewModel.updateRecoveryPhoneNum(it) },
//        fontColor = superLightCean,
//        backGroundColor = darkCean,
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Default.Phone,
//                tint = superLightCean,
//                contentDescription = "Ícone de telefone"
//            )
//        }
//    )
//    GenericButton(
//        text = "Enviar SMS de recuperação",
//        textColor = Color.White,
//        containerColor = lightGreen,
//        icon = {
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.Send,
//                tint = Color.White,
//                contentDescription = "Ícone de envio"
//            )
//        },
//        onClick = { onSessionChange(2) }
//    )
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun RecoverySessionTwo(onSessionChange: (Int) -> Unit, viewModel: RecoveryViewModel) {
//    val confirmRecoveryCode = viewModel.confirmRecoveryCode
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text(
//            text = "Digite abaixo o código recebido no e-mail:",
//            color = Color.Black,
//            style = MaterialTheme.typography.bodyLarge,
//            fontStyle = FontStyle.Italic
//        )
//
//        InputText(
//            value = confirmRecoveryCode,
//            onValueChange = { viewModel.updateConfirmeRecoveryCode(it) },
//            label = "Código",
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Numbers,
//                    contentDescription = "Ícone de números",
//                    tint = superLightCean
//                )
//            },
//            fontColor = superLightCean,
//            backGroundColor = darkCean
//        )
//        GenericButton(
//            text = "Verificar código",
//            textColor = Color.White,
//            containerColor = lightGreen,
//            modifier = Modifier.fillMaxWidth(),
//            icon = {
//                Icon(
//                    imageVector = Icons.Filled.CheckCircle,
//                    tint = Color.White,
//                    contentDescription = "Ícone de check"
//                )
//            },
//            onClick = {
//                //TODO Fazer validação do código
//                onSessionChange(3)
//            }
//        )
//        TextButton(
//            onClick = {  },
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = "Reenviar código",
//                color = darkCean,
//                style = MaterialTheme.typography.bodyLarge,
//            )
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun RecoverySessionThree(onRecoverySuccess: () -> Unit, viewModel: RecoveryViewModel) {
//    var loading = viewModel.loading
//    var recoverySuccess = viewModel.recoverySuccess
//    var recoveryError = viewModel.recoveryError
//
//    var newPassword = viewModel.newPassword
//    var confirmNewPassword = viewModel.confirmPassword
//
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text(
//            text = "Digite e confirme a nova senha nos campos abaixo:",
//            color = Color.Black,
//            style = MaterialTheme.typography.bodyLarge,
//            fontStyle = FontStyle.Italic
//        )
//        InputText(
//            value = newPassword,
//            onValueChange = { viewModel.updateNewPassword(it) },
//            label = "Nova senha",
//            backGroundColor = darkCean,
//            fontColor = superLightCean,
//            isSecret = true,
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    tint = superLightCean,
//                    contentDescription = "Ícone de cadeado"
//                )
//            }
//        )
//        InputText(
//            label = "Confirme a nova senha",
//            value = confirmNewPassword,
//            onValueChange = { viewModel.updateConfirmPassword(it) },
//            backGroundColor = darkCean,
//            fontColor = superLightCean,
//            isSecret = true,
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.LockReset,
//                    tint = superLightCean,
//                    contentDescription = "Ícone de redefinição de senha"
//                )
//            }
//        )
//        GenericButton(
//            text = "Salvar",
//            containerColor = lightGreen,
//            textColor = Color.White,
//            modifier = Modifier.fillMaxWidth(),
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Save,
//                    tint = Color.White,
//                    contentDescription = "Ícone de disquete"
//                )
//            },
//            onClick = { viewModel.recoverUserPassword(confirmNewPassword) }
//        )
//    }
//
//    if (recoveryError != null) {
//        Text(
//            text = recoveryError,
//            color = lightRed,
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.bodyMedium,
//            fontWeight = FontWeight.Bold
//        )
//    }
//
//    if (recoverySuccess) { onRecoverySuccess() }
//}
//
//@Composable
//fun RecoveryFooter(onAlreadyHaveAccount: () -> Unit) {
//    GenericButton(
//        textColor = Color.White,
//        containerColor = lightCean,
//        text = "Eu já possuo uma conta...",
//        modifier = Modifier.fillMaxWidth(),
//        onClick = { onAlreadyHaveAccount() }
//    )
//}