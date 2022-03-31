package org.skyfaced.mvp.service.network;

import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.util.WaifuType;

import java.util.List;

public interface WaifuRepository {
    ImageDto waifu(WaifuType type);

    List<ImageDto> waifus(WaifuType type);
}
