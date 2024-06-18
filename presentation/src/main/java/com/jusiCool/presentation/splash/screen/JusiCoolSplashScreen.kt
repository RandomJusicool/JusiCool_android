package com.jusiCool.presentation.splash.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.icon_image.image.CardsImage
import com.example.design_system.icon_image.image.CloudImage
import com.example.design_system.icon_image.image.GraphImage
import com.example.design_system.icon_image.image.LogoImage
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

const val splashRoute = "splashRoute"

fun NavController.navigationToSplash() {
    this.navigate(splashRoute)
}

fun NavGraphBuilder.splashRoute() {
    composable(splashRoute) {
        JusiCoolSplashRoute()
    }
}

@Composable
fun JusiCoolSplashRoute(modifier: Modifier = Modifier) {
    JusiCoolSplashScreen(modifier = modifier)
}

@Composable
fun JusiCoolSplashScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(JDSColor.WHITE)
            .padding(start = 24.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxSize(0.15f))
        Row {
            Text(
                text = "스마트한",
                style = JDSTypography.titleLarge,
                color = JDSColor.Black,
            )
            Spacer(modifier = Modifier.width(2.dp))
            CardsImage(
                modifier = Modifier
                    .width(62.dp)
                    .height(62.dp)
            )
        }
        Spacer(modifier = Modifier.fillMaxSize(0.0221f))
        Row {
            GraphImage(
                modifier = Modifier
                    .width(62.dp)
                    .height(62.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "주식의 시작",
                style = JDSTypography.titleLarge,
                color = JDSColor.Black,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            LogoImage(
                Modifier
                    .padding(0.dp)
                    .width(220.dp)
                    .height(32.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            CloudImage(
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
            )
        }
    }
}

@Preview
@Composable
fun JusiCoolSplashScreenPreview() {
    JusiCoolSplashScreen()
}