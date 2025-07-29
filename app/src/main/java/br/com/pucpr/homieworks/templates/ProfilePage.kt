package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import br.com.pucpr.homieworks.ui.theme.lightCean
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.yeallow
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.text.font.FontWeight
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.mediumRed

@Composable
fun ProfilePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mediumCean)
            .padding(vertical = 32.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileHeader()
            ProfileContent()
            ProfileFooter()
        }

    }
}

@Composable
fun ProfileHeader() {
    val helpedits = 0
    val workDone = 0

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            SessionHeader("Profile")
            Text(
                text="Helpedits:",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = helpedits.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = yeallow,
                fontWeight = FontWeight.Bold
            )
            Text(
                text="Trabalhos realizados:",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = workDone.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = yeallow,
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
    InputText(
        label = "Nome",
        leadingIcon =  {Icon(imageVector = Icons.Rounded.Face, contentDescription = "Ícone de pessoa", tint = mediumCean)},
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Nome de usuário",
        leadingIcon = {Icon(imageVector = Icons.Rounded.Person, contentDescription = "Ícone de pessoa", tint = mediumCean)},
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Senha",
        leadingIcon = {Icon(imageVector = Icons.Rounded.Lock, contentDescription = "Ícone de cadeado", tint = mediumCean)},
        isSecret = true,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Número de telefone",
        leadingIcon = {Icon(imageVector = Icons.Rounded.Phone, contentDescription = "Ícone de telefone", tint = mediumCean)},
        isSecret = true,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Endereço",
        leadingIcon = {Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = "Ícone de telefone", tint = mediumCean)},
        isSecret = true,
        backGroundColor = lightCean,
        fontColor = mediumCean
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
                    contentColor = mediumRed
                ),
                border = BorderStroke(2.dp, mediumRed)
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