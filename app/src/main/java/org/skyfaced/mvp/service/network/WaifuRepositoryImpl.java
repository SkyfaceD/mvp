package org.skyfaced.mvp.service.network;

import org.skyfaced.mvp.model.EmptyBody;
import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.util.WaifuType;

import java.util.List;
import java.util.stream.Collectors;

public class WaifuRepositoryImpl implements WaifuRepository {
    private final WaifuService waifuService;

    public WaifuRepositoryImpl(WaifuService waifuService) {
        this.waifuService = waifuService;
    }

    @Override
    public ImageDto waifu(WaifuType type) {
        return waifuService.waifu(type.getName(), type.getCategory())
                .blockingFirst();
    }

    @Override
    public List<ImageDto> waifus(WaifuType type) {
        return waifuService.waifus(type.getName(), type.getCategory(), new EmptyBody())
                .blockingFirst()
                .getFiles()
                .stream().map(ImageDto::new)
                .collect(Collectors.toList());
    }
}
