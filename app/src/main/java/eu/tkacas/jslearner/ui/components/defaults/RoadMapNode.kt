package eu.tkacas.jslearner.ui.components.defaults

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.models.roadmap.CircleParameters
import eu.tkacas.jslearner.models.roadmap.LineParameters
import eu.tkacas.jslearner.models.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.models.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.models.roadmap.RoadMapNodeStatus
import eu.tkacas.jslearner.models.roadmap.StrokeParameters


@Composable
fun RoadMapNode(
    nodeState: RoadMapNodeState,
    circleParameters: CircleParameters,
    lineParameters: LineParameters? = null,
    contentStartOffset: Dp = 16.dp,
    spacer: Dp = 32.dp,
    content: @Composable BoxScope.(modifier: Modifier) -> Unit
) {
    val iconPainter = circleParameters.icon?.let { painterResource(id = it) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .drawBehind {
                val circleRadiusInPx = circleParameters.radius.toPx()

                lineParameters?.let {
                    drawLine(
                        brush = it.brush,
                        start = Offset(x = circleRadiusInPx, y = circleRadiusInPx * 2),
                        end = Offset(x = circleRadiusInPx, y = this.size.height),
                        strokeWidth = it.strokeWidth.toPx()
                    )
                }

                drawCircle(
                    circleParameters.backgroundColor,
                    circleRadiusInPx,
                    center = Offset(x = circleRadiusInPx, y = circleRadiusInPx)
                )

                circleParameters.stroke?.let { stroke ->
                    val strokeWidthInPx = stroke.width.toPx()
                    drawCircle(
                        color = stroke.color,
                        radius = circleRadiusInPx - strokeWidthInPx / 2,
                        center = Offset(x = circleRadiusInPx, y = circleRadiusInPx),
                        style = Stroke(width = strokeWidthInPx)
                    )
                }

                iconPainter?.let { painter ->
                    this.withTransform(
                        transformBlock = {
                            translate(
                                left = circleRadiusInPx - painter.intrinsicSize.width / 2f,
                                top = circleRadiusInPx - painter.intrinsicSize.height / 2f
                            )
                        },
                        drawBlock = {
                            this.drawIntoCanvas {
                                with(painter) {
                                    draw(intrinsicSize)
                                }
                            }
                        })
                }
            }
    ) {
        content(
            Modifier
                .defaultMinSize(minHeight = circleParameters.radius * 2)
                .padding(
                    start = circleParameters.radius * 2 + contentStartOffset,
                    bottom = if (nodeState.position != RoadMapNodePosition.LAST) spacer else 0.dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoadMapPreview() {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            RoadMapNode(
                nodeState = RoadMapNodeState(RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.FIRST, "something"),
                circleParameters = CircleParametersDefaults.circleParameters(
                    backgroundColor = Color.LightGray
                ),
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = Color.LightGray,
                    endColor = Color.Blue
                )
            ) { modifier -> MessageBubble(modifier, containerColor = Color.LightGray, "test 3", onClick = {
                println("Hello")
            } )}

            RoadMapNode(
                nodeState = RoadMapNodeState(RoadMapNodeStatus.LOCKED, RoadMapNodePosition.MIDDLE, "something 2"),
                circleParameters = CircleParametersDefaults.circleParameters(
                    backgroundColor = Color.Blue
                ),
                contentStartOffset = 16.dp,
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = Color.Blue,
                    endColor = Color.Red
                )
            ) { modifier -> MessageBubble(modifier, containerColor = Color.Blue, "test 2", onClick = {
                println("Hello")
            } )}

            RoadMapNode(
                nodeState = RoadMapNodeState(RoadMapNodeStatus.IN_PROGRESS, RoadMapNodePosition.LAST, "something 3"),
                circleParameters = CircleParametersDefaults.circleParameters(
                    backgroundColor = Color.Red,
                    stroke = StrokeParameters(color = Color.Red, width = 2.dp),
                    //icon = R.drawable.ic_launcher_background
                )
            ) { modifier -> MessageBubble(modifier, containerColor = Color.Red, "test 1", onClick = {
                println("Hello")
            } )}
        }
}

@Composable
fun MessageBubble(modifier: Modifier, containerColor: Color, text: String, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .width(300.dp)
            .height(30.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}