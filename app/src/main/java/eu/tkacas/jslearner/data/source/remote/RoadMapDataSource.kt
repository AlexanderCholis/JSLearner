package eu.tkacas.jslearner.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import eu.tkacas.jslearner.domain.entity.roadmap.RoadMapNodeState
import kotlinx.coroutines.tasks.await

class RoadmapDataSource {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getRoadMapNodes(): List<RoadMapNodeState> {
        val snapshot = db.collection("roadmapNodes").get().await()
        return snapshot.documents.mapNotNull { document ->
            document.toObject(RoadMapNodeState::class.java)
        }
    }
}

//
//import eu.tkacas.jslearner.models.roadmap.RoadMapNodePosition
//import eu.tkacas.jslearner.models.roadmap.RoadMapNodeState
//import eu.tkacas.jslearner.models.roadmap.RoadMapNodeStatus
//
//class RoadmapDataSource {
//    val nodes = listOf(
//        RoadMapNodeState(RoadMapNodeStatus.LOCKED, RoadMapNodePosition.FIRST, "Start Here"),
//        RoadMapNodeState(RoadMapNodeStatus.IN_PROGRESS, RoadMapNodePosition.MIDDLE, "Module 1"),
//        RoadMapNodeState(RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.MIDDLE, "Introduction"),
//        RoadMapNodeState(RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.LAST, "Finish")
//    )
//
//    fun getRoadMapNodes(): List<RoadMapNodeState> {
//        return nodes
//    }
//}
