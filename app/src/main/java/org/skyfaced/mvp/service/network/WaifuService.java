package org.skyfaced.mvp.service.network;

import org.skyfaced.mvp.model.EmptyBody;
import org.skyfaced.mvp.model.FileDto;
import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.service.Service;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WaifuService extends Service {
    @GET("{type}/{category}")
    Observable<ImageDto> waifu(@Path("type") String type, @Path("category") String category);

    @POST("/many/{type}/{category}")
    Observable<FileDto> waifus(@Path("type") String type, @Path("category") String category, @Body EmptyBody body);
}
