package sa.factory.warshanowservices.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sa.factory.warshanowservices.data.WarshaNowOrderRepository
import sa.factory.warshanowservices.hardware.HardwareSensorDriver
import sa.factory.warshanowservices.hardware.HardwareTelemetryFrame

private val PrimaryBrand = Color(0xFFF59E0B)
private val SecondaryBrand = Color(0xFF06B6D4)

class MainActivity : ComponentActivity() {
    private val repository = WarshaNowOrderRepository()
    private val sensorDriver = HardwareSensorDriver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = PrimaryBrand,
                    secondary = SecondaryBrand,
                    background = Color(0xFF09090B),
                    surface = Color(0xFF18181B)
                )
            ) {
                val items by repository.catalog.collectAsState(initial = emptyList())
                val telemetry by sensorDriver.observePeripheralStream().collectAsState(
                    initial = HardwareTelemetryFrame("Mada Contactless EMV L2 NFC + GPS Dispatch", "INIT", "Mada Contactless EMV L2 NFC + GPS Dispatch: ONLINE | READY")
                )

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("تطبيق ورشة فوري — الصيانة الميدانية", fontSize = 20.sp, fontWeight = FontWeight.Black, color = Color.White)
                        Text("منظومة services المتكاملة مع ربط Mada Contactless EMV L2 NFC + GPS Dispatch", fontSize = 13.sp, color = Color.Gray)

                        Card(colors = CardDefaults.cardColors(containerColor = PrimaryBrand.copy(alpha = 0.18f))) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("العتاد المتصل: ${telemetry.peripheralName}", fontWeight = FontWeight.Bold, color = PrimaryBrand)
                                Text(telemetry.livePayload, fontSize = 12.sp, color = Color.White)
                            }
                        }

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(items) { item ->
                                Card(modifier = Modifier.fillMaxWidth()) {
                                    Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(item.title, fontWeight = FontWeight.Bold, color = Color.White)
                                            Text(item.description, fontSize = 12.sp, color = Color.Gray)
                                        }
                                        Text(item.priceSar, fontWeight = FontWeight.Black, color = SecondaryBrand)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
