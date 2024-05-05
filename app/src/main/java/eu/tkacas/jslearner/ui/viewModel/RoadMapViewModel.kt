package eu.tkacas.jslearner.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.data.repositories.RoadmapRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class RoadMapViewModel(private val roadmapRepository: RoadmapRepository) : ViewModel() {

    private val _nodes = MutableStateFlow<List<RoadMapNodeState>>(emptyList())
    val nodes: StateFlow<List<RoadMapNodeState>> = _nodes

    init {
        loadRoadMapNodes()
    }

    private fun loadRoadMapNodes() {
        viewModelScope.launch {
            _nodes.value = roadmapRepository.getRoadMapNodes()
        }
    }
}
