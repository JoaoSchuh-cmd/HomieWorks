package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pucpr.homieworks.templates.util.GenericButton
import br.com.pucpr.homieworks.templates.util.InputText
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.lightCean
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.superLightCean

@Composable
fun SignUpPage() {
    var session by remember { mutableIntStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(superLightCean)
            .padding(horizontal = 32.dp, vertical = 40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SignUpHeader(session)
            InputForm(session)
            SignUpFooter(session, onSessionChange = { session = it })
        }
    }
}

@Composable
fun SignUpHeader(session: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text ="Cadastrar",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        if (session == 1) SignUpInfo("Pessoais", 1) else SignUpInfo("Endereço", 2)
    }
}

@Composable
fun SignUpInfo(label: String, session: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = label,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = darkCean,
        )
        Text(
            text = "Session ${session}/2",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Black,
        )
    }
}

@Composable
fun InputForm(session: Int) {
    Column(
        modifier = Modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
       if (session == 1) PersonalInfoForm() else AddressInfoForm()
    }
}

@Composable
fun PersonalInfoForm() {
    val backGroundColor = darkCean
    val fontColor = superLightCean

    InputText(
        label = "Nome",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = backGroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Celular",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = backGroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Nome de usuário",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = backGroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Senha",
        leadingIcon = null,
        isSecret = true,
        backGroundColor = backGroundColor,
        fontColor = fontColor
    )
}

@Composable
fun AddressInfoForm() {
    val backgroundColor = darkCean
    val fontColor = superLightCean

    InputText(
        label = "Rua",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Numéro",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Cidade",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Estado",
        leadingIcon = null,
        isSecret = true,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "CEP",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
}

@Composable
fun SignUpFooter(session: Int, onSessionChange: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (session == 1) PersonalInfoFooter(onSessionChange = onSessionChange) else AddressInfoFooter(onSessionChange)

        GenericButton(
            textColor = Color.White,
            containerColor = lightCean,
            text = "Eu já possuo uma conta...",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PersonalInfoFooter(onSessionChange: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GenericButton(
            text = "Próximo",
            containerColor = lightGreen,
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = "Seta para a direita",
                    modifier = Modifier
                        .size(20.dp)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        GenericButton(
            text = "Cancelar",
            containerColor = lightRed,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AddressInfoFooter(onSessionChange: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .height(60.dp),
            onClick = {onSessionChange(1)},
            colors = ButtonDefaults.buttonColors(containerColor = lightRed, contentColor = Color.White)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Seta para a esquerda",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(text = "Voltar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .height(60.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = lightGreen, contentColor = Color.White)
        ) {
            Text(text = "Finalizado", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        }
    }
}