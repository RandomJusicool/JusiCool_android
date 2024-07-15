package com.jusiCool.presentation.stockDetail.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.button.JDSToggleButton
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.domain.model.day.GetDayModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import kotlin.math.abs
import kotlin.math.min

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
    setWhichTimeSegmentSelected: (TimeSegment) -> Unit,
    data: ImmutableList<GetDayModel>
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
                    onClick = {
                        setIsToggleSelected(!isToggleSelected)
                    },
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
        } else {
            // TODO: 주식 그래프 추가
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(284.dp)
            ) {
                Text(text = "선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프, 선형 그래프")
            }
            if (!isToggleSelected) {
                val maxHeight = 284.dp // 캔버스의 최대 높이를 284dp로 설정

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxHeight)
                ) {
                    val maxCandles = 31
                    val candleWidth = size.width / (maxCandles * 1.5f) // 1.5는 봉 사이의 간격을 고려한 값
                    val spaceBetweenCandles = candleWidth * 0.5f
                    val maxHigh = data.maxOf { it.highPrice }
                    val minLow = data.minOf { it.lowPrice }
                    val priceRange = maxHigh - minLow

                    data.take(maxCandles).forEachIndexed { index, model ->
                        val x = index * (candleWidth + spaceBetweenCandles) + candleWidth / 2

                        // 각 가격에 대한 y 좌표를 최대 높이에 맞춰 조정
                        val highY =
                            maxHeight.toPx() - ((model.highPrice - minLow).toFloat() / priceRange * maxHeight.toPx())
                        val lowY =
                            maxHeight.toPx() - ((model.lowPrice - minLow).toFloat() / priceRange * maxHeight.toPx())
                        val openY =
                            maxHeight.toPx() - ((model.marketPrice - minLow).toFloat() / priceRange * maxHeight.toPx())
                        val closeY =
                            maxHeight.toPx() - ((model.presentPrice - minLow).toFloat() / priceRange * maxHeight.toPx())

                        val color =
                            if (model.presentPrice >= model.marketPrice) Color.Red else Color.Blue

                        // 고가와 저가를 연결하는 선을 그립니다.
                        drawLine(
                            color = color,
                            start = Offset(x, highY),
                            end = Offset(x, lowY),
                            strokeWidth = 4f
                        )

                        // 시가와 종가를 연결하는 사각형을 그립니다.
                        drawRect(
                            color = color,
                            topLeft = Offset(
                                x - candleWidth / 2,
                                min(openY, closeY)
                            ),
                            size = androidx.compose.ui.geometry.Size(
                                candleWidth,
                                abs(openY - closeY)
                            )
                        )
                    }
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
        data = immutableListOf(
            GetDayModel(100, 110, 90, 105, 105, 1500, 5, "2023-07-01"), // 상승
            GetDayModel(105, 115, 100, 110, 110, 1600, 5, "2023-07-02"), // 상승
            GetDayModel(110, 120, 105, 115, 115, 1700, 5, "2023-07-03"), // 상승
            GetDayModel(115, 125, 110, 120, 120, 1800, 5, "2023-07-04"), // 상승
            GetDayModel(120, 130, 115, 125, 125, 1900, 5, "2023-07-05"), // 상승
            GetDayModel(135, 145, 130, 125, 125, 3700, -5, "2023-07-23"), // 하락
            GetDayModel(125, 135, 120, 115, 115, 3800, -5, "2023-07-24"), // 하락
            GetDayModel(170, 180, 165, 175, 175, 2900, 5, "2023-07-15"), // 상승
            GetDayModel(125, 135, 120, 130, 130, 2000, 5, "2023-07-06"), // 상승
            GetDayModel(130, 140, 125, 135, 135, 2100, 5, "2023-07-07"), // 상승
            GetDayModel(140, 150, 135, 145, 145, 2300, 5, "2023-07-09"), // 상승
            GetDayModel(150, 160, 145, 155, 155, 2500, 5, "2023-07-11"), // 상승
            GetDayModel(155, 165, 150, 160, 160, 2600, 5, "2023-07-12"), // 상승
            GetDayModel(160, 170, 155, 165, 165, 2700, 5, "2023-07-13"), // 상승
            GetDayModel(165, 175, 160, 170, 170, 2800, 5, "2023-07-14"), // 상승
            GetDayModel(175, 185, 170, 180, 180, 3000, 5, "2023-07-16"), // 상승
            GetDayModel(180, 190, 175, 185, 185, 3100, 5, "2023-07-17"), // 상승
            GetDayModel(185, 195, 180, 175, 175, 3200, -5, "2023-07-18"), // 하락
            GetDayModel(165, 175, 160, 155, 155, 3400, -5, "2023-07-20"), // 하락
            GetDayModel(155, 165, 150, 145, 145, 3500, -5, "2023-07-21"), // 하락
            GetDayModel(145, 155, 140, 135, 135, 3600, -5, "2023-07-22"), // 하락
            GetDayModel(115, 125, 110, 105, 105, 3900, -5, "2023-07-25"), // 하락
            GetDayModel(105, 115, 100, 95, 95, 4000, -5, "2023-07-26")    // 하락
        )
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
