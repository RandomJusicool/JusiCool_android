package com.jusiCool.presentation.stockDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.button.JDSToggleButton
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

enum class TimeSegment(val depiction: String) {
    ONE_MINUTE("1분"),
    TEN_MINUTES("10분"),
    THIRTY_MINUTES("30분"),
    SIXTY_MINUTES("60분"),
    ONE_DAY("1일"),
}

@Composable
fun StockGraphCard(
    modifier: Modifier = Modifier,
    whichTimeSegmentSelected: TimeSegment,
    toggleOnClick: () -> Unit,
    setWhichTimeSegmentSelected: (TimeSegment) -> Unit,
) {
    val (isToggleSelected, setIsToggleSelected) = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = JDSColor.WHITE,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(
                vertical = 20.dp,
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(
            20.dp,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "차트",
                style = JDSTypography.subTitle,
                color = JDSColor.Black
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "라인 차트",
                    style = JDSTypography.bodySmall,
                    color = JDSColor.GRAY600,
                )
                JDSToggleButton(
                    height = 24.dp,
                    width = 43.dp,
                    isSelected = isToggleSelected,
                    onClick = toggleOnClick,
                    setIsSelected = setIsToggleSelected
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                12.dp,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                TimeSegment.values().forEach {
                    ChartTimeSegmentItem(
                        text = it.depiction,
                        isSelected = whichTimeSegmentSelected == it,
                        onClick = { setWhichTimeSegmentSelected(it) })
                }
            }
        }
        if (isToggleSelected) {
            // TODO: 주식 그래프 추가
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(284.dp)
            ) {
                Text(text = "캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트, 캔들 차트")
            }
        } else {
            // TODO: 주식 그래프 추가
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(284.dp)
            ) {
                Text(text = "선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프")
            }
        }
    }
}

@Composable
fun ChartTimeSegmentItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickableSingle { onClick() }
            .border(
                width = 1.dp,
                color = if (isSelected) JDSColor.MAIN
                else JDSColor.GRAY200,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
    ) {
        Text(
            text = text,
            style = JDSTypography.label,
            color = if (isSelected) JDSColor.MAIN
            else JDSColor.GRAY400
        )
    }
}

@Preview
@Composable
fun StockGraphCardPreview() {
    val (whichTimeSegmentSelected, setWhichTimeSegmentSelected) = remember {
        mutableStateOf(
            TimeSegment.ONE_MINUTE
        )
    }

    StockGraphCard(
        whichTimeSegmentSelected = whichTimeSegmentSelected,
        setWhichTimeSegmentSelected = setWhichTimeSegmentSelected,
        toggleOnClick = {}
    )
}

@Preview
@Composable
fun ChartTimeSegmentItemPreview() {
    Column {
        ChartTimeSegmentItem(
            text = TimeSegment.ONE_DAY.depiction,
            isSelected = true,
            onClick = {},
        )
        ChartTimeSegmentItem(
            text = TimeSegment.ONE_DAY.depiction,
            isSelected = false,
            onClick = {},
        )
    }
}