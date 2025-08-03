package br.com.pucpr.homieworks.templates

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pucpr.homieworks.R
import br.com.pucpr.homieworks.templates.util.GenericButton
import br.com.pucpr.homieworks.templates.util.InputText
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean

@Composable
fun LoginPage() {
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
                    leadingIcon  = {
                        Icon(
                            imageVector = Icons.Filled.Mail,
                            contentDescription = "Ícone de e-mail",
                            tint = Color.White
                        )
                    },
                    isSecret = false,
                    backGroundColor = darkCean
                )

                Spacer(modifier = Modifier.height(16.dp))

                InputText(
                    label = "Senha",
                    leadingIcon  = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Ícone de senha",
                            tint = Color.White
                        )
                    },
                    isSecret = true,
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
                        text = "Entrar",
                        textColor = darkCean,
                        containerColor = superLightCean,
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Clique aqui se esqueceu sua senha",
                            fontSize = 15.sp,
                            color = superLightCean,
                            style = MaterialTheme.typography.displayMedium,
                        )
                    }
                }
                GenericButton(
                    text = "Cadastrar",
                    containerColor = darkCean,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

