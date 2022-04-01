package org.skyfaced.mvp.service.network;

import org.skyfaced.mvp.model.EmptyBody;
import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.util.WaifuType;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;

public class WaifuRepositoryImpl implements WaifuRepository {
    private final WaifuService waifuService;

    public WaifuRepositoryImpl(WaifuService waifuService) {
        this.waifuService = waifuService;
    }

    @Override
    public Observable<ImageDto> waifu(WaifuType type) {
        return waifuService.waifu(type.getName(), type.getCategory())
                .doOnEach(imageDtoNotification -> {
                    if (new Random().nextInt() % 2 == 0) throw new IllegalArgumentException();
                })
                .distinct();
    }

    @Override
    public Observable<List<ImageDto>> waifus(WaifuType type) {
        return waifuService.waifus(type.getName(), type.getCategory(), new EmptyBody())
                .map(it -> it.getFiles().stream().map(ImageDto::new).collect(Collectors.toList()))
                .distinct();
    }
}
