package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.pucpr.homieworks.templates.util.InputText
import br.com.pucpr.homieworks.ui.theme.lightCean
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.mediumCean

@Composable
fun SignUpPage() {
    var session by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightCean)
            .padding(horizontal = 32.dp, vertical = 30.dp)
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
            color = mediumCean,
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
    BoxWithConstraints {
        val spacing = maxHeight * 0.03f

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = mediumCean, shape = RoundedCornerShape(10.dp))
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
           if (session == 1) PersonalInfoForm() else AddressInfoForm()
        }
    }
}

@Composable
fun PersonalInfoForm() {
    InputText(
        label = "Nome",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Celular",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Nome de usuário",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Senha",
        leadingIcon = null,
        isSecret = true,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
}

@Composable
fun AddressInfoForm() {
    InputText(
        label = "Rua",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Numéro",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Cidade",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "Estado",
        leadingIcon = null,
        isSecret = true,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
    InputText(
        label = "CEP",
        leadingIcon = null,
        isSecret = false,
        backGroundColor = lightCean,
        fontColor = mediumCean
    )
}

@Composable
fun SignUpFooter(session: Int, onSessionChange: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (session == 1) PersonalInfoFooter(onSessionChange = onSessionChange) else AddressInfoFooter(onSessionChange)
        Button(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(text = "Eu já possuo uma conta...", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PersonalInfoFooter(onSessionChange: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .height(60.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = lightRed, contentColor = Color.White)
        ) { Text(text = "Cancelar", fontSize = 18.sp, fontWeight = FontWeight.Bold) }
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .height(60.dp),
            onClick = {onSessionChange(2)},
            colors = ButtonDefaults.buttonColors(containerColor = lightGreen, contentColor = Color.White)
        ) {
            Text(text = "Próximo", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Seta para a direita",
                modifier = Modifier
                    .size(20.dp)
            )
        }
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