package br.com.pucpr.homieworks.templates

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
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import br.com.pucpr.homieworks.templates.util.GenericButton
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean

@Composable
fun ProfilePage(
    onProfileIconClick: () -> Unit,
    onMenuIconClick: (String) -> Unit
) {
    var option by remember { mutableStateOf("profile") }

    GenericPage(
        { ProfileHeader(
            onProfileIconClick = onProfileIconClick,
            onMenuOptionSelected = { selected ->
                option = selected
                onMenuIconClick(selected)
            }
        ) },
        { ProfileContent() },
        { ProfileFooter() }
    )
}

@Composable
fun ProfileHeader(
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
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
                onMenuIconClick = onMenuOptionSelected
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
                onClick = {},
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
                onClick = {},
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
                onClick = {},
                textColor = lightRed,

            )
        }
    }
}