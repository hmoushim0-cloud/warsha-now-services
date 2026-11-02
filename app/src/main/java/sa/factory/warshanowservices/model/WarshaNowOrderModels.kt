package sa.factory.warshanowservices.model

import kotlinx.serialization.Serializable

@Serializable
data class WarshaNowItem(
    val id: String,
    val title: String,
    val description: String,
    val priceSar: String,
    val badge: String = "معتمد"
)

@Serializable
data class WarshaNowOrder(
    val requestId: String,
    val items: List<WarshaNowItem>,
    val status: String = "ACTIVE"
)
