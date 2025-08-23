package br.com.pucpr.homieworks.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.pucpr.homieworks.R
import br.com.pucpr.homieworks.navigation.Screen
import br.com.pucpr.homieworks.view.util.GenericButton
import br.com.pucpr.homieworks.view.util.InputText
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.viewmodel.LoginViewModel

@Composable
fun LoginPage(
    viewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    val loading = viewModel.loading
    val loginError = viewModel.loginError
    val loginSuccess = viewModel.loginSuccess

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mediumCean)
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_horizontal),
                    contentDescription = "Logo do App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )

                InputText(
                    label = "E-mail",
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon  = {
                        Icon(
                            imageVector = Icons.Filled.Mail,
                            contentDescription = "Ícone de e-mail",
                            tint = superLightCean
                        )
                    },
                    isSecret = false,
                    fontColor = superLightCean,
                    backGroundColor = darkCean
                )

                Spacer(modifier = Modifier.height(16.dp))

                InputText(
                    label = "Senha",
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon  = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Ícone de senha",
                            tint = superLightCean
                        )
                    },
                    isSecret = true,
                    fontColor = superLightCean,
                    backGroundColor = darkCean
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    GenericButton(
                        text = if (loading) "Entrando..." else "Entrar",
                        textColor = darkCean,
                        containerColor = superLightCean,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (email.isBlank() || password.isBlank()) {
                                viewModel.updateLoginError("E-mail e senha não podem estar vazios")
                            } else {
                                viewModel.fazerLogin(email, password)
                            }
                        },
                    )

                    if (loginError != null) {
                        Text(
                            text = loginError,
                            color = lightRed,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    if (loginSuccess) {
                        LaunchedEffect(Unit) {
                            navController.navigate(Screen.Feed.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    }
//                    TextButton(
//                        onClick = { navController.navigate(Screen.Recovery.route) {
//                            popUpTo(Screen.Login.route) { inclusive = true }
//                        } },
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                            .fillMaxWidth()
//                    ) {
//                        Text(
//                            text = "Clique aqui se esqueceu sua senha",
//                            color = superLightCean,
//                            style = MaterialTheme.typography.bodyLarge,
//                        )
//                    }
                }
                GenericButton(
                    text = "Cadastrar",
                    containerColor = Color.Black,
                    textColor = superLightCean,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Screen.SignUp.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    } }
                )
            }
        }
    }
}


