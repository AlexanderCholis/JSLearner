package eu.tkacas.jslearner.data.models.roadmap

data class RoadMapNodeState(
    val status: RoadMapNodeStatus,
    val position: RoadMapNodePosition,
    val message: String? = null
)
