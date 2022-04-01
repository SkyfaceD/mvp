package org.skyfaced.mvp.ui

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import org.skyfaced.mvp.model.ImageDto
import org.skyfaced.mvp.service.instant.InstantService
import org.skyfaced.mvp.service.network.WaifuRepository
import org.skyfaced.mvp.util.WaifuType
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val waifuRepository: WaifuRepository,
    private val instantService: InstantService,
) : MvpPresenter<MainView>() {
    private val disposable = CompositeDisposable()

    fun changeText(text: String?) {
        Timber.d(text)
    }

    fun onItemClick(item: String) {
        viewState.showSnackbar("Hello from presenter\n$item")
    }

    fun onMessageClick() {
        val random = Random().nextInt()
        if (random % 2 == 0) {
            viewState.showSnackbar("$random from snack")
        } else {
            viewState.showToast("$random from toast")
        }
    }

    fun strings(): List<String> = instantService.strings.shuffled()

    fun onWaifuClick(type: WaifuType) {
        val d: Disposable = waifuRepository.waifu(type)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { image -> viewState.updateWaifu(image) },
                { cause -> viewState.showSnackbar(cause.message.orEmpty()) }
            ) { viewState.showToast("Вайфу загружена") }
        disposable.add(d)
    }

    fun onWaifuItemClick(image: ImageDto) {
        viewState.showSnackbar(image.url)
    }

    fun onWaifusClick(type: WaifuType) {
        viewState.showOnScreenLoader()
        val d: Disposable = waifuRepository.waifus(type)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { images -> viewState.updateWaifuRecycler(images) },
                { cause ->
                    viewState.showSnackbar(cause.message.orEmpty())
                    viewState.hideOnScreenLoader()
                }
            ) {
                viewState.showToast("Вайфу загружены")
                viewState.hideOnScreenLoader()
            }
        disposable.add(d)
    }

    override fun onDestroy() {
        disposable.clear()
    }
}