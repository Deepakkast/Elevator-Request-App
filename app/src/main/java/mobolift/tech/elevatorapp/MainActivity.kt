package mobolift.tech.elevatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import mobolift.tech.elevatorapp.ui.theme.ElevatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElevatorApp()
        }

    }
}

@Composable
fun ElevatorApp() {
    val viewModel: ElevatorViewModel = viewModel()
    val currentFloor = viewModel.currentFloor.value
    val direction = viewModel.direction.value

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Floor: $currentFloor")
        Text(text = "Direction: $direction")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.requestElevator(currentFloor + 1, "Up") }) {
            Text("Move Up")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.requestElevator(currentFloor - 1, "Down") }) {
            Text("Move Down")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (direction != "Idle") {
            CircularProgressIndicator()
        }
    }
}