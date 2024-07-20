package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.NavigationDrawer
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.presentation.ui.component.PageIndicator
import eu.tkacas.jslearner.presentation.ui.theme.LightBeige
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.viewmodel.main.HelpViewModel

@Composable
fun HelpScreen(
    navController: NavController,
    viewModel: HelpViewModel,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val texts = viewModel.getHelpTexts()
    var currentIndex by rememberSaveable { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                drawerState = drawerState,
                getNavigationDrawerItemsUseCase = JSLearner.appModule.getNavigationDrawerItemsUseCase,
                logoutUseCase = JSLearner.appModule.logoutUseCase
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                BackAppTopBar(
                    color = Color.White,
                    onBackClick = {
                        if (currentIndex > 0) {
                            currentIndex--
                        } else {
                            navController.navigateUp()
                        }
                    }
                )
            }
        ) { innerPadding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 28.dp, end = 28.dp, bottom = 28.dp)
                ) {
                    BoldText(text = stringResource(id = texts[currentIndex].title))
                    Spacer(modifier = Modifier.height(10.dp))
                    NormalText(text = stringResource(id = texts[currentIndex].content))
                    Spacer(modifier = Modifier.height(30.dp))
                    Image(
                        painter = painterResource(id = texts[currentIndex].image),
                        contentDescription = stringResource(id = R.string.simple_image),
                        modifier = Modifier
                            .size(300.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PageIndicator(
                            numberOfPages = texts.size,
                            selectedPage = currentIndex,
                            selectedColor = PrussianBlue,
                            defaultColor = LightBeige,
                            defaultRadius = 8.dp,
                            selectedLength = 24.dp,
                            space = 12.dp,
                            animationDurationInMillis = 300
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        GeneralButtonComponent(valueId = if (currentIndex < texts.size - 1) R.string.next else R.string.done,
                            onButtonClicked = {
                            if (currentIndex < texts.size - 1) {
                                currentIndex++
                            } else {
                                navController.navigate("roadmap")
                            }
                        })
                    }
                }
            }
        }
    }
}