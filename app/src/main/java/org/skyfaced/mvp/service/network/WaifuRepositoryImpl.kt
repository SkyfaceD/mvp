package org.skyfaced.mvp.service.network

import io.reactivex.rxjava3.core.Observable
import org.skyfaced.mvp.model.ImageDto
import org.skyfaced.mvp.util.WaifuType
import kotlin.random.Random.Default.nextInt

class WaifuRepositoryImpl(private val waifuService: WaifuService) : WaifuRepository {
    override fun waifu(type: WaifuType): Observable<ImageDto> {
        return waifuService.waifu(type.type, type.category)
            .doOnEach { require(nextInt() % 2 != 0) }
            .distinct()
    }

    override fun waifus(type: WaifuType): Observable<List<ImageDto>> {
        return waifuService.waifus(type.type, type.category)
            .map { it.files.map(::ImageDto) }
            .distinct()
    }
}