package com.jusiCool.presentation.communityList.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.presentation.communityList.component.CommunityMainList

const val communityRoute = "communityRoute"

fun NavController.navigateToCommunity() {
    this.navigate(communityRoute)
}

fun NavGraphBuilder.communityRoute(
    popUpBackStack: () -> Unit,
    navigateToCommunity: () -> Unit
) {
    composable(route = communityRoute) {
        CommunityRoute(
            popUpBackStack = popUpBackStack,
            navigateToCommunity = navigateToCommunity
        )
    }
}

@Composable
internal fun CommunityRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunity: () -> Unit
) {
    CommunityScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToCommunity = navigateToCommunity
    )
}

@Composable
internal fun CommunityScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunity: () -> Unit,
    // data: TemList
    ) {
    JusiCoolAndroidTheme { colors, _ ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(colors.GRAY50)
        ) {
            Column {
                JDSArrowTopBar(
                    startIcon = {
                        LeftArrowIcon(
                            modifier = Modifier.clickableSingle { popUpBackStack() }
                        )
                    },
                    betweenText = "커뮤니티 목록"
                )
                CommunityMainList(
                    // data = data,
                    navigateToDetailCommunity = navigateToCommunity
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommunityScreenPre() {
    CommunityRoute(popUpBackStack = { /*TODO*/ }) {
        
    }
}