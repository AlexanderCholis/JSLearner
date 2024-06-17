package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.presentation.ui.component.PathModuleCard

@Composable
fun ExploringPathScreen() {
    val isExpandedModule1 = remember { mutableStateOf(false) }
    val isExpandedModule2 = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 32.dp, end = 32.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoldText(textId = R.string.your_path)
        NormalText(textId = R.string.your_path_description)
        Spacer(modifier = Modifier.padding(8.dp))
        PathModuleCard(
            moduleTitleText = "Demo Module",
            moduleDescriptionText = "This is a demo module to show how the module card looks like",
            isExpanded = isExpandedModule1
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PathModuleCard(
            moduleTitleText = "Demo Module2",
            moduleDescriptionText = "This is a demo module to show how the module card looks like for the second module",
            isExpanded = isExpandedModule2,
        )
    }
}

@Preview
@Composable
fun ExploringPathScreenPreview() {
    ExploringPathScreen()
}