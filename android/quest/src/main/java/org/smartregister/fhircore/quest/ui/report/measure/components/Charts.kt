package org.smartregister.fhircore.quest.ui.report.measure.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.atan2

data class PieChartInput(
  val color: Color, val value: Int, val description: String, val isTapped: Boolean = false
)

@Composable
fun PieChart(
  modifier: Modifier = Modifier,
  radius: Float = 300f,
  innerRadius: Float = 150f,
  input: List<PieChartInput>,
  onReportMeasureClicked: (PieChartInput) -> Unit,
  titleColor: Color = Color.White
  ) {
  var circleCenter by remember {
    mutableStateOf(Offset.Zero)
  }

  var inputList by remember {
    mutableStateOf(input)
  }

  Box(
    modifier = modifier, contentAlignment = Alignment.Center
  ) {
    Canvas(modifier = Modifier
      .width(Dp(300f))
      .height(Dp(300f))
      .fillMaxSize()
      .pointerInput(true) {
        detectTapGestures(
          onTap = { offset ->
            val tapAngleInDegrees = (-atan2(
              x = circleCenter.y - offset.y,
              y = circleCenter.x - offset.x
            ) * (180f / PI).toFloat() - 90f).mod(360f)
            if (tapAngleInDegrees < 90) {
              offset.x < circleCenter.x + innerRadius && offset.y < circleCenter.y + innerRadius
            } else if (tapAngleInDegrees < 180) {
              offset.x > circleCenter.x - innerRadius && offset.y < circleCenter.y + innerRadius
            } else if (tapAngleInDegrees < 270) {
              offset.x > circleCenter.x - innerRadius && offset.y > circleCenter.y - innerRadius
            } else {
              offset.x < circleCenter.x + innerRadius && offset.y > circleCenter.y - innerRadius
            }

            val anglePerValue = 360f / input.sumOf {
              it.value
            }
            var currAngle = 0f
            inputList.forEach { pieChartInput ->

              currAngle += pieChartInput.value * anglePerValue
              if (tapAngleInDegrees < currAngle) {
                val description = pieChartInput.description
                inputList = inputList.map {
                  if (description == it.description) {
                    it.copy(isTapped = !it.isTapped)
                  } else {
                    it.copy(isTapped = false)
                  }
                }
                return@detectTapGestures
              }
            }

          }
        )
      }
    ) {
      val width = size.width
      val height = size.height
      circleCenter = Offset(x = width / 2f, y = height / 2f)

      val totalValue = input.sumOf {
        it.value
      }
      val anglePerValue = 360f / totalValue
      var currentStartAngle = 0f

      inputList.forEach { pieChartInput ->
        val scale = if (pieChartInput.isTapped) 1.05f else 1.0f
        val angleToDraw = pieChartInput.value * anglePerValue
        scale(scale) {
          drawArc(
            color = pieChartInput.color,
            startAngle = currentStartAngle,
            sweepAngle = angleToDraw,
            useCenter = true,
            size = Size(
              width = radius * 2f, height = radius * 2f
            ),
            topLeft = Offset(
              (width - radius * 2f) / 2f, (height - radius * 2f) / 2f
            )
          )
          currentStartAngle += angleToDraw
          if (pieChartInput.isTapped) onReportMeasureClicked(pieChartInput)
        }
        var rotateAngle = currentStartAngle - angleToDraw / 2f - 90f
        var factor = 1f
        if (rotateAngle > 90f) {
          rotateAngle = (rotateAngle + 180).mod(360f)
          factor = -0.92f
        }

        val percentage = (pieChartInput.value / totalValue.toFloat() * 100).toInt()

        drawContext.canvas.nativeCanvas.apply {
          rotate(rotateAngle) {
            drawText("$percentage %",
              circleCenter.x,
              circleCenter.y + (radius - (radius - innerRadius) / 2f) * factor,
              Paint().apply {
                textSize = 13.sp.toPx()
                textAlign = Paint.Align.CENTER
                color = titleColor.toArgb()
              })
          }
        }

      }
    }
  }
}
