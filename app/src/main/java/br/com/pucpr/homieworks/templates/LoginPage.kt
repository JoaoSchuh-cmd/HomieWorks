package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pucpr.homieworks.R
import br.com.pucpr.homieworks.templates.util.InputText
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.lightCean
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed

@Composable
fun LoginPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mediumCean)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo do App",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            InputText(
                label = "Usuário",
                leadingIcon  = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Ícone de usuário",
                        tint = Color.White
                    )
                }
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
                isSecret = true
            )
        }
        Row (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .width(140.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(lightCean),
                onClick = {}
            ) {
                Text(
                    text = "Cadastrar",
                    color = lightRed,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                modifier = Modifier
                    .width(140.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(lightGreen),
                onClick = {}
            ) {
                Text(
                    text = "Entrar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

