package mobolift.tech.elevatorapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ElevatorViewModel : ViewModel() {

    private val _currentFloor = mutableStateOf(0)
    val currentFloor = _currentFloor

    private val _direction = mutableStateOf("")
    val direction = _direction

    fun requestElevator(requestedFloor: Int, directionRequested: String) {
        _direction.value = directionRequested
        viewModelScope.launch {
            while (_currentFloor.value != requestedFloor) {
                if (directionRequested == "Up" && _currentFloor.value < requestedFloor) {
                    _currentFloor.value++
                } else if (directionRequested == "Down" && _currentFloor.value > requestedFloor) {
                    _currentFloor.value--
                }
                delay(1000)
            }
            _direction.value = "Idle"
        }
    }
}