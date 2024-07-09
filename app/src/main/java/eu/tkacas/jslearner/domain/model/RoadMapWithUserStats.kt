package eu.tkacas.jslearner.domain.model

import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState

data class RoadMapWithUserStats(
    var nodes: List<RoadMapNodeState>? = null,
    var user: User? = null
)
