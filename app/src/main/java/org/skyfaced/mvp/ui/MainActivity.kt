package org.skyfaced.mvp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.skyfaced.mvp.databinding.ActivityMainBinding
import org.skyfaced.mvp.model.ImageDto
import org.skyfaced.mvp.ui.base.BaseActivity
import org.skyfaced.mvp.util.WaifuType.SFW
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), MainView {
    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val simpleAdapter by lazy(LazyThreadSafetyMode.NONE) { SimpleAdapter(::onSimpleItemClick) }
    private val waifuAdapter by lazy(LazyThreadSafetyMode.NONE) { WaifuAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContent()
    }

    @ProvidePresenter
    fun setupPresenter(): MainPresenter {
        return presenterProvider.get()
    }

    override fun setupBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(inflater)

    private fun setupContent() {
        binding.edt.doOnTextChanged { text, _, _, _ ->
            presenter.changeText(text?.toString().orEmpty())
        }

        binding.btnMessage.setOnClickListener { presenter.onMessageClick() }

        binding.recycler.adapter = simpleAdapter
        updateSimpleRecycler(presenter.strings())

        binding.btnLoadWaifu.setOnClickListener {
            val index = Random().nextInt(SFW.InnerCategory.values().size)
            val category = SFW.InnerCategory.values()[index]
            presenter.onWaifuClick(SFW(category))
        }

        binding.recyclerWaifu.adapter = waifuAdapter

        binding.btnLoadWaifus.setOnClickListener {
            val index = Random().nextInt(SFW.InnerCategory.values().size)
            val category = SFW.InnerCategory.values()[index]
            presenter.onWaifusClick(SFW(category))
        }
    }

    private fun onSimpleItemClick(text: String) {
        presenter.onItemClick(text)
    }

    override fun updateSimpleRecycler(items: List<String>) {
        simpleAdapter.submitList(items)
    }

    override fun updateWaifu(image: ImageDto) {
        binding.imgWaifu.load(image.url)
    }

    override fun updateWaifuRecycler(images: List<ImageDto>) {
        waifuAdapter.submitList(images)
    }

    override fun showOnScreenLoader() {
        binding.progress.isVisible = true
    }

    override fun hideOnScreenLoader() {
        binding.progress.isVisible = false
    }
}