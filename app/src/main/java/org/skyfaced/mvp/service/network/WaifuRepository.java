package org.skyfaced.mvp.service.network;

import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.util.WaifuType;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface WaifuRepository {
    Observable<ImageDto> waifu(WaifuType type);

    Observable<List<ImageDto>> waifus(WaifuType type);
}
