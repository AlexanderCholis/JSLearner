package eu.tkacas.jslearner.presentation.ui.components.defaults

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.domain.entity.roadmap.CircleParameters
import eu.tkacas.jslearner.domain.entity.roadmap.StrokeParameters


object CircleParametersDefaults {

    private val defaultCircleRadius = 12.dp

    fun circleParameters(
        radius: Dp = defaultCircleRadius,
        backgroundColor: Color = Color.Cyan,
        stroke: StrokeParameters? = null,
        @DrawableRes
        icon: Int? = null
    ) = CircleParameters(
        radius,
        backgroundColor,
        stroke,
        icon
    )
}