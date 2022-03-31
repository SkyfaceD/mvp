package org.skyfaced.mvp.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.skyfaced.mvp.databinding.ItemWaifuBinding;
import org.skyfaced.mvp.model.ImageDto;

import java.util.Arrays;
import java.util.List;

public final class WaifuAdapter extends RecyclerView.Adapter<WaifuAdapter.ViewHolder> {
    private final OnClickListener onClickListener;
    public List<ImageDto> currentList = Arrays.asList();

    public WaifuAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWaifuBinding binding = ItemWaifuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(currentList.get(position));
    }

    @Override
    public int getItemCount() {
        return currentList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(List<ImageDto> items) {
        currentList = items;
        notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onClick(ImageDto item);
    }

    protected final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemWaifuBinding binding;
        private ImageDto item;

        public ViewHolder(ItemWaifuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(v -> onClickListener.onClick(item));
        }

        public void bind(ImageDto image) {
            item = image;
            Glide.with(binding.getRoot()).load(image.getUrl()).into(binding.getRoot());
        }
    }
}
