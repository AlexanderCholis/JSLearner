package eu.tkacas.jslearner.domain.model.roadmap

import androidx.compose.ui.graphics.Color
import eu.tkacas.jslearner.R

enum class RoadMapNodeStatus {
    LOCKED,
    IN_PROGRESS,
    COMPLETED
}

fun RoadMapNodeStatus.getColor(): Color {
    return when (this) {
        RoadMapNodeStatus.COMPLETED -> Color.Green
        RoadMapNodeStatus.IN_PROGRESS -> Color(0xFFFFA500) // Orange color in ARGB
        RoadMapNodeStatus.LOCKED -> Color.Gray
    }
}

fun RoadMapNodeStatus.getIcon(): Int {
    return when (this) {
        RoadMapNodeStatus.COMPLETED -> R.drawable.complete
        RoadMapNodeStatus.IN_PROGRESS -> R.drawable.refresh
        RoadMapNodeStatus.LOCKED -> R.drawable.lock
    }
}