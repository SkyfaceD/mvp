package org.skyfaced.mvp.ui

import moxy.viewstate.strategy.alias.AddToEndSingle
import org.skyfaced.mvp.model.ImageDto
import org.skyfaced.mvp.ui.base.BaseView

interface MainView : BaseView {
    @AddToEndSingle
    fun updateSimpleRecycler(items: List<String>)

    @AddToEndSingle
    fun updateWaifu(image: ImageDto)

    @AddToEndSingle
    fun updateWaifuRecycler(images: List<ImageDto>)
}