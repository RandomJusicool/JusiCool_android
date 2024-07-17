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
        if (data.isNotEmpty()) {
            if (isToggleSelected) {
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
                            strokeWidth = 2f
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
            } else {
                val maxHeight = 284.dp // 캔버스의 최대 높이를 284dp로 설정

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxHeight)
                ) {
                    val maxPoints = 31
                    val totalWidth = size.width
                    val pointSpacing = totalWidth / (maxPoints - 1)
                    val maxHigh = data.maxOf { it.presentPrice }
                    val minLow = data.minOf { it.presentPrice }
                    val priceRange = maxHigh - minLow

                    val points = data.take(maxPoints).mapIndexed { index, model ->
                        val x = index * pointSpacing
                        val y =
                            maxHeight.toPx() - ((model.presentPrice - minLow).toFloat() / priceRange * maxHeight.toPx())
                        Offset(x, y)
                    }

                    for (i in 0 until points.size - 1) {
                        drawLine(
                            color = Color.Blue,
                            start = points[i],
                            end = points[i + 1],
                            strokeWidth = 7f
                        )
                    }
                }
            }
        } else {
            Text(text = "데이터가 없습니다")
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
            GetDayModel(86900, 87400, 86700, 86900, 1851251, 0.0, "2024-07-16 AM 09:15"),
            GetDayModel(86900, 87500, 86800, 87300, 879560, 0.4, "2024-07-16 AM 09:25"),
            GetDayModel(86900, 87500, 86800, 87300, 879560, 0.4, "2024-07-16 AM 09:35"),
            GetDayModel(86900, 87500, 86800, 87300, 879560, 0.4, "2024-07-16 AM 09:45"),
            GetDayModel(86900, 87500, 86800, 87300, 879560, 0.4, "2024-07-16 AM 09:55"),
            GetDayModel(86900, 87500, 86800, 87300, 879560, 0.4, "2024-07-16 AM 10:05"),
            GetDayModel(86900, 87500, 86800, 87300, 879560, 0.4, "2024-07-16 AM 10:15"),
            GetDayModel(86900, 87500, 86800, 87300, 879560, 0.4, "2024-07-16 AM 10:25"),
            GetDayModel(87700, 87800, 87500, 87600, 210163, -0.2, "2024-07-16 AM 10:45"),
            GetDayModel(87300, 87300, 87200, 87300, 29495, 0.0, "2024-07-16 AM 10:55"),
            GetDayModel(87300, 87300, 87200, 87300, 29495, 0.0, "2024-07-16 AM 11:05"),
            GetDayModel(87200, 87400, 87200, 87200, 186338, 0.0, "2024-07-16 AM 11:15"),
            GetDayModel(87700, 87700, 87600, 87600, 13761, -0.2, "2024-07-16 AM 11:25"),
            GetDayModel(87700, 87700, 87600, 87600, 21855, -0.2, "2024-07-16 AM 11:35"),
            GetDayModel(87500, 87600, 87500, 87500, 22532, 0.0, "2024-07-16 AM 11:45"),
            GetDayModel(87600, 87700, 87600, 87700, 118226, 0.1, "2024-07-16 AM 11:55"),
            GetDayModel(87900, 87900, 87700, 87900, 301911, 0.0, "2024-07-16 PM 12:05"),
            GetDayModel(87900, 87900, 87700, 87900, 301911, 0.0, "2024-07-16 PM 12:15"),
            GetDayModel(87800, 87700, 87700, 87700, 73220, -0.2, "2024-07-16 PM 12:35"),
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
