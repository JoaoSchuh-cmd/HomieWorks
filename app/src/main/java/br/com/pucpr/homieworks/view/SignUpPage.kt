package br.com.pucpr.homieworks.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocalPostOffice
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import br.com.pucpr.homieworks.Screen
import br.com.pucpr.homieworks.view.util.GenericButton
import br.com.pucpr.homieworks.view.util.InputText
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.lightCean
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.viewmodel.SignUpViewModel

@Composable
fun SignUpPage(
    viewModel: SignUpViewModel,
    navController: NavController
) {
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
            SignUpContent(
                session = session,
                onSessionChange = { session = it },
                onCancel = { navController.navigate(Screen.Login.route){
                    popUpTo(Screen.SignUp.route) { inclusive = true }
                } },
                viewModel = viewModel,
            )
            SignUpFooter(
                onAlreadyHaveAccount = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                },

            )
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
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge
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
fun SignUpContent(
    session: Int,
    onSessionChange: (Int) -> Unit,
    onCancel: () -> Unit,
    viewModel: SignUpViewModel,
) {
    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (session == 1) PersonalInfoForm(viewModel) else AddressInfoForm(viewModel)

        if (session == 1) PersonalInfoFooter(onSessionChange = onSessionChange, onCancel = onCancel) else AddressInfoFooter(onSessionChange, viewModel)
    }
}

@Composable
fun PersonalInfoForm(viewModel: SignUpViewModel) {
    val backGroundColor = darkCean
    val fontColor = superLightCean

    InputText(
        label = "Nome",
        value = viewModel.user.name,
        onValueChange = { viewModel.updateName(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Face,
                tint = fontColor,
                contentDescription = "Ícone de face"
            )
        },
        isSecret = false,
        backGroundColor = backGroundColor,
        fontColor = fontColor,
    )
    InputText(
        label = "Celular",
        value = viewModel.user.phoneNumber,
        onValueChange = { viewModel.updatePhone(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Phone,
                tint = fontColor,
                contentDescription = "Ícone de telefone"
            )
        },
        isSecret = false,
        backGroundColor = backGroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "E-mail",
        value = viewModel.user.email,
        onValueChange = { viewModel.updateEmail(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                tint = fontColor,
                contentDescription = "Ícone de e-mail"
            )
        },
        isSecret = false,
        backGroundColor = backGroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Senha",
        value = viewModel.user.userPassword,
        onValueChange = { viewModel.updatePassword(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                tint = fontColor,
                contentDescription = "Ícone de cadeado"
            )
        },
        isSecret = true,
        backGroundColor = backGroundColor,
        fontColor = fontColor
    )
}

@Composable
fun AddressInfoForm(viewModel: SignUpViewModel) {
    val backgroundColor = darkCean
    val fontColor = superLightCean

    InputText(
        label = "Rua",
        value = viewModel.user.addressStreet,
        onValueChange = { viewModel.updateAddressStreet(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Map,
                contentDescription = "Ícone de mapa",
                tint = fontColor
            )
        },
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Número",
        value = viewModel.user.addressNum,
        onValueChange = { viewModel.updateAddressNumber(it) } ,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Numbers,
                tint = fontColor,
                contentDescription = "Ícone de número"
            )
        },
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Cidade",
        value = viewModel.user.addressCity,
        onValueChange = { viewModel.updateAddressCity(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LocationCity,
                contentDescription = "Ícone de cidade",
                tint = fontColor
            )
        },
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Estado",
        value = viewModel.user.addressState,
        onValueChange = { viewModel.updateAddressState(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Flag,
                contentDescription = "Ícone de estado",
                tint = fontColor
            )
        },
        isSecret = true,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "CEP",
        value = viewModel.user.addressPostalCode,
        onValueChange = { viewModel.updateAddressPostalCode(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LocalPostOffice,
                contentDescription = "Ícone de CEP",
                tint = fontColor
            )
        },
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
}

@Composable
fun SignUpFooter(onAlreadyHaveAccount: () -> Unit, ) {
    GenericButton(
        textColor = Color.White,
        containerColor = lightCean,
        text = "Eu já possuo uma conta...",
        modifier = Modifier.fillMaxWidth(),
        onClick = { onAlreadyHaveAccount() }
    )
}

@Composable
fun PersonalInfoFooter(onSessionChange: (Int) -> Unit, onCancel: () -> Unit) {
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
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSessionChange(2) }
        )
        GenericButton(
            text = "Cancelar",
            containerColor = lightRed,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onCancel() }
        )
    }
}

@Composable
fun AddressInfoFooter(
    onSessionChange: (Int) -> Unit,
    viewModel: SignUpViewModel,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GenericButton(
            modifier = Modifier.weight(1f),
            text = "Voltar",
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Seta para a esquerda",
                    modifier = Modifier
                        .size(20.dp)
                )
            },
            containerColor = lightRed,
            onClick = { onSessionChange(1) }
        )

        GenericButton(
            modifier = Modifier.weight(1f),
            containerColor = lightGreen,
            text = "Finalizado",
            onClick = { viewModel.fazerCadastro(viewModel.user) }
        )
    }
}