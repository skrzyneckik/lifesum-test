package assignment.jorge.view.list;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import assignment.jorge.R;
import assignment.jorge.domain.entity.DomainFoodModel;
import butterknife.BindView;
import butterknife.ButterKnife;

class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {

    interface SaveToggleCallback {

        void onToggleSaveRequested(final @NonNull DomainFoodModel model, final boolean toSaved);
    }

    private final List<DomainFoodModel> mItems = new ArrayList<>();
    private final Context mContext;

    private SaveToggleCallback mSaveToggleCallback;

    @Inject
    ItemRecyclerAdapter(final @NonNull Context context) {
        mContext = context;
    }

    void setSaveToggleCallback(final @Nullable SaveToggleCallback saveToggleCallback) {
        mSaveToggleCallback = saveToggleCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(final @NonNull ViewGroup parent, final @IntRange(from = 0, to = 0) int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, Boolean.FALSE));
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder holder, final
    @IntRange(from = 0, to = Integer.MAX_VALUE) int position) {
        final DomainFoodModel model = getItem(position);

        holder.setName(model.getName());
        holder.setSaved(model.isSaved());

        holder.restoreAnimationState();
    }

    @Override
    @IntRange(from = 0, to = Integer.MAX_VALUE)
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    @IntRange(from = 0, to = Long.MAX_VALUE)
    public long getItemId(final @IntRange(from = 0, to = Integer.MAX_VALUE) int position) {
        return getItem(position).getName().hashCode();
    }

    @NonNull
    private DomainFoodModel getItem(final int position) {
        return mItems.get(position);
    }

    private void addItem(final @IntRange(from = 0, to = Integer.MAX_VALUE) int position, final @NonNull DomainFoodModel model) {
        mItems.add(position, model);
        notifyItemInserted(position);
    }

    void setItems(final @NonNull List<DomainFoodModel> models) {
        final Collection<DomainFoodModel> toRemove = new ArrayList<>();
        Collections.sort(models, new Comparator<DomainFoodModel>() {
            @Override
            public int compare(final @Nullable DomainFoodModel lhs, final @Nullable DomainFoodModel rhs) {
                int ret;

                if (lhs == null) {
                    ret = rhs == null ? 0 : -1;
                } else if (rhs == null) {
                    ret = 1;
                } else {
                    ret = lhs.getName().toString().compareTo(rhs.getName().toString());
                }

                return ret;
            }
        });

        for (final DomainFoodModel model : mItems) {
            if (!models.contains(model))
                toRemove.add(model);
        }
        if (!mItems.isEmpty()) {
            mItems.removeAll(toRemove);
            //TODO Future work: Make a proper implementation of this by removing and notifying about single items, not the whole bunch - this is not efficient and kills an important performance benefit of recyclerviews
            notifyDataSetChanged();
        }
        for (final DomainFoodModel model : models) {
            if (!mItems.contains(model)) {
                addItem(mItems.size(), model);
            }
        }
    }

    void markModelAsAvailableOffline(final @NonNull DomainFoodModel model, final boolean availableOffline) {
        final int index = mItems.indexOf(model);

        if (index != -1) {
            mItems.get(index).setSaved(availableOffline);
            notifyItemChanged(index);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_view)
        TextView mNameView;

        @BindView(R.id.saved_view)
        ImageView mSavedView;

        public ViewHolder(final @NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mSavedView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final @NonNull View v) {
                    if (mSaveToggleCallback != null) {
                        final DomainFoodModel model = getItem(getAdapterPosition());
                        mSaveToggleCallback.onToggleSaveRequested(model, !model.isSaved());
                    }
                }
            });
        }

        private void setName(final @NonNull CharSequence username) {
            mNameView.setText(username);
        }

        private void setSaved(final boolean saved) {
            //TODO Future work: If we use states properly with a custom attribute we won't need to take care of updating this manually
            mSavedView.setImageTintList(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{saved ? Color.GREEN : Color.WHITE}));
        }

        private void restoreAnimationState() {
            itemView.setAlpha(1);
        }
    }
}
