package eu.tkacas.jslearner.presentation.ui.component.default

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import eu.tkacas.jslearner.domain.model.roadmap.CircleParameters
import eu.tkacas.jslearner.domain.model.roadmap.LineParameters
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeCategory
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeStatus
import eu.tkacas.jslearner.domain.model.roadmap.StrokeParameters
import eu.tkacas.jslearner.domain.model.roadmap.getColor
import eu.tkacas.jslearner.domain.model.roadmap.getIcon


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
fun RoadMapNodePreview() {
    val nodes = listOf(
        RoadMapNodeState("1", RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.FIRST, RoadMapNodeCategory.COURSE, "First Node"),
        RoadMapNodeState("2", RoadMapNodeStatus.IN_PROGRESS, RoadMapNodePosition.MIDDLE, RoadMapNodeCategory.LESSON, "Middle Node"),
        RoadMapNodeState("3", RoadMapNodeStatus.UNLOCKED, RoadMapNodePosition.MIDDLE, RoadMapNodeCategory.COURSE, "Middle Node"),
        RoadMapNodeState("4", RoadMapNodeStatus.LOCKED, RoadMapNodePosition.LAST, RoadMapNodeCategory.COURSE, "Last Node")
    )

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(nodes) { index, node ->
            val nextNodeColor = if (index < nodes.size - 1) nodes[index + 1].status.getColor() else Color.DarkGray
            val lineParameters = if (node.position != RoadMapNodePosition.LAST) {
                LineParametersDefaults.linearGradient(
                    startColor = node.status.getColor(),
                    endColor = nextNodeColor
                )
            } else null

            RoadMapNode(
                nodeState = node,
                circleParameters = CircleParametersDefaults.circleParameters(
                    backgroundColor = node.status.getColor(),
                    stroke = StrokeParameters(color = node.status.getColor(), width = 2.dp),
                    icon = node.status.getIcon()
                ),
                lineParameters = lineParameters,
                content = { modifier ->
                    node.title?.let {
                        MessageBubble(
                            modifier,
                            containerColor = node.status.getColor(),
                            text = it,
                            onClick = {
                                // No action in preview
                            }
                        )
                    }
                }
            )
        }
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