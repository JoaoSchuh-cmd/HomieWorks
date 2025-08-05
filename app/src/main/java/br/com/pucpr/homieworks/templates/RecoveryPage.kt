package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.templates.util.GenericButton
import br.com.pucpr.homieworks.templates.util.InputText
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.lightCean
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.superLightCean

@Composable
fun RecoveryPage(
    onAlreadyHaveAccount : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = superLightCean)
            .padding(horizontal = 16.dp, vertical = 40.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RecoveryHeader()
            RecoveryContent()
            RecoveryFooter(onAlreadyHaveAccount)
        }
    }
}

@Composable
fun RecoveryHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text ="Recuperação de conta",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RecoveryContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(
                    text = "Confirme o e-mail de recuperação para recebimento do código:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Text(
                    text = "j***@gmail.com",
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            InputText(
                label = "E-mail",
                fontColor = superLightCean,
                backGroundColor = darkCean,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        tint = superLightCean,
                        contentDescription = "Ícone de e-mail"
                    )
                }
            )
            GenericButton(
                text = "Enviar e-mail de recuperação",
                textColor = Color.White,
                containerColor = lightGreen,
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        tint = Color.White,
                        contentDescription = "Ícone de envio"
                    )
                }
            )
        }
    }
}

@Composable
fun RecoveryFooter(onAlreadyHaveAccount: () -> Unit) {
    GenericButton(
        textColor = Color.White,
        containerColor = lightCean,
        text = "Eu já possuo uma conta...",
        modifier = Modifier.fillMaxWidth(),
        onClick = { onAlreadyHaveAccount() }
    )
}