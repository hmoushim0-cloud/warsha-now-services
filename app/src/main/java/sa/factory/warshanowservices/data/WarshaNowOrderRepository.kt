package sa.factory.warshanowservices.data

import sa.factory.warshanowservices.model.WarshaNowItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WarshaNowOrderRepository {
    private val _catalog = MutableStateFlow(listOf(
        WarshaNowItem("1", "الباقة التنفيذية المعتمدة", "تكامل مباشر مع Mada Contactless EMV L2 NFC + GPS Dispatch", "180 ر.س", "معتمد"),
        WarshaNowItem("2", "خدمة المعالجة الفورية", "أداء أصيل بسرعة 60 إطار في الثانية", "95 ر.س", "فوري"),
        WarshaNowItem("3", "باقة الصيانة والدعم السحابي", "تشفير كامل للبيانات المحلية ومزامنة آمنة", "250 ر.س", "شامل")
    ))
    val catalog: Flow<List<WarshaNowItem>> = _catalog.asStateFlow()
}
