package eu.tkacas.jslearner.domain.model.roadmap

import androidx.compose.ui.graphics.Color
import eu.tkacas.jslearner.R

enum class RoadMapNodeStatus {
    LOCKED,
    UNLOCKED,
    IN_PROGRESS,
    COMPLETED
}

fun RoadMapNodeStatus.getColor(): Color {
    return when (this) {
        RoadMapNodeStatus.COMPLETED -> Color(0xFF40679E) // Blue color in ARGB
        RoadMapNodeStatus.UNLOCKED -> Color(0xFFA1DD70) // Green color in ARGB
        RoadMapNodeStatus.IN_PROGRESS -> Color(0xFFFFBF78) // Orange color in ARGB
        RoadMapNodeStatus.LOCKED -> Color(0xFFEE4E4E) // Red color in ARGB
    }
}

fun RoadMapNodeStatus.getIcon(): Int {
    return when (this) {
        RoadMapNodeStatus.COMPLETED -> R.drawable.complete
        RoadMapNodeStatus.UNLOCKED -> R.drawable.unlocked
        RoadMapNodeStatus.IN_PROGRESS -> R.drawable.refresh
        RoadMapNodeStatus.LOCKED -> R.drawable.lock
    }
}