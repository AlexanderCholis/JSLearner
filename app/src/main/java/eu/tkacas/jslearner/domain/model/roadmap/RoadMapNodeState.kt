package eu.tkacas.jslearner.domain.model.roadmap

data class RoadMapNodeState(
    val id: String,
    val status: RoadMapNodeStatus,
    val position: RoadMapNodePosition,
    val category: RoadMapNodeCategory,
    val title: String? = null
)
