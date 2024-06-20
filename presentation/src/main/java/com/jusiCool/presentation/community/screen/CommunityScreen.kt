package com.jusiCool.presentation.community.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.presentation.community.component.CommunityList
import com.jusiCool.presentation.community.component.WritingCommunityButton
import com.jusiCool.presentation.communityList.component.TemList

const val communityRoute = "communityRoute"

fun NavController.navigateToCommunity() {
    this.navigate(communityRoute)
}

fun NavGraphBuilder.communityRoute(
    navigateToCommunityWriting: () -> Unit,
    navigateToDetailCommunity: () -> Unit,
    popUpBackStack: () -> Unit
) {
    composable(route = communityRoute) {
        CommunityRoute(
            navigateToDetailCommunity = navigateToDetailCommunity,
            navigateToCommunityWriting = navigateToCommunityWriting,
            popUpBackStack = popUpBackStack,
        )
    }
}

@Composable
internal fun CommunityRoute(
    modifier: Modifier = Modifier,
    navigateToDetailCommunity: () -> Unit,
    navigateToCommunityWriting: () -> Unit,
    popUpBackStack: () -> Unit,
    // data1: TemList,
    // data2: CommunityListItemTemData
) {
    CommunityScreen(
        modifier = modifier,
        navigateToDetailCommunity = navigateToDetailCommunity,
        navigateToCommunityWriting = navigateToCommunityWriting,
        popUpBackStack = popUpBackStack,
        // data1 = data1,
        // data2 = data2
    )
}

@Composable
internal fun CommunityScreen(
    modifier: Modifier = Modifier,
    navigateToDetailCommunity: () -> Unit,
    navigateToCommunityWriting: () -> Unit,
    popUpBackStack: () -> Unit,
    // data1: TemList,
    // data2: CommunityListItemTemData
) {
    JusiCoolAndroidTheme { colors, typography ->  
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.GRAY50)
        ) {
            Column {
                JDSArrowTopBar(
                    startIcon = {
                        LeftArrowIcon(
                            modifier = Modifier.clickableSingle { popUpBackStack() }
                        )
                    },
                    betweenText = "임의 값" // data1.company
                )
                CommunityList(
                    // data = data2,
                    navigateToDetailCommunity = navigateToDetailCommunity
                )
            }
            WritingCommunityButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 24.dp,
                        bottom = 24.dp
                    )
                ,
                onClick = navigateToCommunityWriting
            )
        }
    }
}

@Preview
@Composable
private fun CommunityScreenPre() {
    CommunityRoute(
        navigateToDetailCommunity = { /*TODO*/ },
        navigateToCommunityWriting = { /*TODO*/ },
        popUpBackStack = { /*TODO*/ },
        // data1 = TemList(company = "마이크로소프트 커뮤니티", count = 12)
    )
}