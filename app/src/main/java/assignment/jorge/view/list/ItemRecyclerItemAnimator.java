package assignment.jorge.view.list;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import javax.inject.Inject;

class ItemRecyclerItemAnimator extends DefaultItemAnimator {

    @Inject
    ItemRecyclerItemAnimator() {
    }

    @Override
    public boolean canReuseUpdatedViewHolder(final @NonNull RecyclerView.ViewHolder viewHolder) {
        return true;
    }

    @Override
    public boolean animateAdd(final @NonNull RecyclerView.ViewHolder viewHolder) {
        runEnterAnimation(viewHolder);
        return false;
    }

    private void runEnterAnimation(final @NonNull RecyclerView.ViewHolder holder) {
        holder.itemView.setAlpha(0);
        holder.itemView.animate()
                .alphaBy(1)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(holder.itemView.getContext().getResources().getInteger(android.R.integer.config_longAnimTime))
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(final @NonNull Animator animation) {
                        dispatchAddStarting(holder);
                    }

                    @Override
                    public void onAnimationEnd(final @NonNull Animator animation) {
                        dispatchAddFinished(holder);
                    }

                    @Override
                    public void onAnimationCancel(final @NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(final @NonNull Animator animation) {
                    }
                }).start();
    }

    @Override
    public boolean animateRemove(final @NonNull RecyclerView.ViewHolder holder) {
        runExitAnimation(holder);
        return false;
    }

    private void runExitAnimation(final @NonNull RecyclerView.ViewHolder holder) {
        holder.itemView.setAlpha(1);
        holder.itemView.animate()
                .alphaBy(-1)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(holder.itemView.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime))
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(final @NonNull Animator animation) {
                        dispatchRemoveStarting(holder);
                    }

                    @Override
                    public void onAnimationEnd(final @NonNull Animator animation) {
                        dispatchRemoveFinished(holder);
                    }

                    @Override
                    public void onAnimationCancel(final @NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(final @NonNull Animator animation) {
                    }
                }).start();
    }
}
