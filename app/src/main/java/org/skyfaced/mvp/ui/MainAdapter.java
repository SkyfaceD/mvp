package org.skyfaced.mvp.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.skyfaced.mvp.databinding.ItemBinding;

import java.util.List;

public final class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private final OnClickListener onClickListener;
    public List<String> currentList;

    public MainAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
    public void submitList(List<String> items) {
        currentList = items;
        notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onClick(String item);
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemBinding binding;
        private String item;

        public ViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(v -> onClickListener.onClick(item));
        }

        public void bind(String text) {
            item = text;
            binding.getRoot().setText(text);
        }
    }
}
