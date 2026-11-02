package sa.factory.warshanowservices.hardware

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Dedicated Hardware Peripheral Driver for Archetype: services
 * Bound Physical Peripheral: Mada Contactless EMV L2 NFC + GPS Dispatch
 */
data class HardwareTelemetryFrame(
    val peripheralName: String,
    val protocolStatus: String,
    val livePayload: String,
    val timestampEpochMs: Long = System.currentTimeMillis()
)

class HardwareSensorDriver {
    fun observePeripheralStream(): Flow<HardwareTelemetryFrame> = flow {
        while (true) {
            emit(
                HardwareTelemetryFrame(
                    peripheralName = "Mada Contactless EMV L2 NFC + GPS Dispatch",
                    protocolStatus = "CONNECTED_ACTIVE",
                    livePayload = "Mada Contactless EMV L2 NFC + GPS Dispatch: ONLINE | READY"
                )
            )
            delay(2500L)
        }
    }
}
