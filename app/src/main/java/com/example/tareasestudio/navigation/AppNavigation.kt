package com.example.tareasestudio.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tareasestudio.ui.screen.CrearTareaScreen
import com.example.tareasestudio.ui.screen.DetalleTareaScreen
import com.example.tareasestudio.ui.screen.EditarTareaScreen
import com.example.tareasestudio.ui.screen.InicioScreen
import com.example.tareasestudio.ui.screen.ListaTareasScreen
import com.example.tareasestudio.ui.viewModel.TareaViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    // Un solo ViewModel compartido por todas las pantallas.
    val viewModel: TareaViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = InicioRoute,
        modifier = modifier
    ) {

        composable<InicioRoute> {
            InicioScreen(
                onVerTareas = { navController.navigate(ListaTareasRoute) }
            )
        }

        composable<ListaTareasRoute> {
            ListaTareasScreen(
                viewModel = viewModel,
                onVolver = { navController.popBackStack() },
                onVerDetalle = { id -> navController.navigate(DetalleTareaRoute(id)) },
                onNuevaTarea = { navController.navigate(CrearTareaRoute) }
            )
        }

        composable<DetalleTareaRoute> { backStackEntry ->
            val ruta = backStackEntry.toRoute<DetalleTareaRoute>()
            DetalleTareaScreen(
                id = ruta.id,
                viewModel = viewModel,
                onVolver = { navController.popBackStack() },
                onEditar = { navController.navigate(EditarTareaRoute(ruta.id)) }
            )
        }

        composable<CrearTareaRoute> {
            CrearTareaScreen(
                viewModel = viewModel,
                onVolver = { navController.popBackStack() }
            )
        }

        composable<EditarTareaRoute> { backStackEntry ->
            val ruta = backStackEntry.toRoute<EditarTareaRoute>()
            EditarTareaScreen(
                id = ruta.id,
                viewModel = viewModel,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}