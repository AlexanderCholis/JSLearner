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

