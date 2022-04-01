package org.skyfaced.mvp.service.network

import io.reactivex.rxjava3.core.Observable
import org.skyfaced.mvp.model.ImageDto
import org.skyfaced.mvp.util.WaifuType

interface WaifuRepository {
    fun waifu(type: WaifuType): Observable<ImageDto>

    fun waifus(type: WaifuType): Observable<List<ImageDto>>
}