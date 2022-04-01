package org.skyfaced.mvp.service.network

import io.reactivex.rxjava3.core.Observable
import org.skyfaced.mvp.model.EmptyBody
import org.skyfaced.mvp.model.FileDto
import org.skyfaced.mvp.model.ImageDto
import org.skyfaced.mvp.service.Service
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WaifuService : Service {
    @GET("{type}/{category}")
    fun waifu(
        @Path("type") type: String,
        @Path("category") category: String,
    ): Observable<ImageDto>

    @POST("/many/{type}/{category}")
    fun waifus(
        @Path("type") type: String,
        @Path("category") category: String,
        @Body body: EmptyBody = EmptyBody(),
    ): Observable<FileDto>
}