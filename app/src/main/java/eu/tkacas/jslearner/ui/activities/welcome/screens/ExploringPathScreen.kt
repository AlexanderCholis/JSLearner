package eu.tkacas.jslearner.ui.activities.welcome.screens

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
import eu.tkacas.jslearner.ui.components.PathModuleCard

@Composable
fun ExploringPathScreen() {
    val isExpandedModule1 = remember { mutableStateOf(false) }
    val isExpandedModule2 = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.your_path),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
        Text(
            text = stringResource(id = R.string.your_path_description),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        PathModuleCard(
            moduleTitleText = "Demo Module",
            moduleDescriptionText = "This is a demo module to show how the module card looks like",
            isExpanded = isExpandedModule1
        )
        Spacer(modifier = Modifier.padding(16.dp))
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