package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.templates.util.InputText
import br.com.pucpr.homieworks.templates.util.SessionHeader
import br.com.pucpr.homieworks.ui.theme.yellow
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.text.font.FontWeight
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean

@Composable
fun ProfilePage(
    onProfileIconClick: () -> Unit,
    onMenuIconClick: () -> Unit
) {
    GenericPage(
        { ProfileHeader(onProfileIconClick = onProfileIconClick, onMenuIconClick = onMenuIconClick) },
        { ProfileContent() },
        { ProfileFooter() }
    )
}

@Composable
fun ProfileHeader(
    onProfileIconClick: () -> Unit,
    onMenuIconClick: () -> Unit
) {
    val helpedits = 0
    val workDone = 0
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
                onMenuIconClick = onMenuIconClick
            )
            Text(
                text="Helpedits:",
                style = MaterialTheme.typography.titleMedium,
                color = fontColor
            )
            Text(
                text = helpedits.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = yellow,
                fontWeight = FontWeight.Bold
            )
            Text(
                text="Trabalhos realizados:",
                style = MaterialTheme.typography.titleMedium,
                color = fontColor
            )
            Text(
                text = workDone.toString(),
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

@Composable
fun ProfileContent() {
    val fontColor = darkCean
    val backgroundColor = superLightCean

    InputText(
        label = "Nome",
        leadingIcon =  {Icon(imageVector = Icons.Rounded.Face, contentDescription = "Ícone de pessoa", tint = fontColor)},
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Nome de usuário",
        leadingIcon = {Icon(imageVector = Icons.Rounded.Person, contentDescription = "Ícone de pessoa", tint = fontColor)},
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Senha",
        leadingIcon = {Icon(imageVector = Icons.Rounded.Lock, contentDescription = "Ícone de cadeado", tint = fontColor)},
        isSecret = true,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Número de telefone",
        leadingIcon = {Icon(imageVector = Icons.Rounded.Phone, contentDescription = "Ícone de telefone", tint = fontColor)},
        isSecret = true,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Endereço",
        leadingIcon = {Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = "Ícone de telefone", tint = fontColor)},
        isSecret = true,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
}

@Composable
fun ProfileFooter() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(lightRed),
                onClick = {}
            ) {
                Text(
                    text = "Desconectar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Ícone de saída",
                    tint = Color.White
                )
            }
            Button(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(lightGreen),
                onClick = {}
            ) {
                Text(
                    text = "Salvar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "Ícone de disquete",
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                onClick = {},
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Black,
                    contentColor = lightRed
                ),
                border = BorderStroke(2.dp, lightRed)
            ) {
                Text(
                    text = "Excluir conta",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    imageVector = Icons.Default.DeleteForever,
                    contentDescription = "Ícone de lixeira"
                )
            }
        }
    }
}