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

const val communityListRoute = "communityListRoute"

fun NavController.navigateToMainCommunity() {
    this.navigate(communityListRoute)
}

fun NavGraphBuilder.communityListRoute(
    popUpBackStack: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    composable(route = communityListRoute) {
        CommunityListRoute(
            popUpBackStack = popUpBackStack,
            navigateToCommunity = navigateToCommunity
        )
    }
}

@Composable
internal fun CommunityListRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    CommunityListScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToCommunity = navigateToCommunity
    )
}

@Composable
internal fun CommunityListScreen(
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
                    navigateToCommunity = navigateToCommunity
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommunityMainScreenPre() {
    CommunityListRoute(popUpBackStack = { /*TODO*/ }) {

    }
}