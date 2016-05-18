package assignment.jorge.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import assignment.jorge.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatingSearchView extends CardView {

    private SearchCommandCallback mSearchCommandCallback;

    public interface SearchCommandCallback {

        void onSearchRequested(final @NonNull CharSequence query);
    }

    @BindView(R.id.query_field)
    TextView mQueryField;

    @BindView(R.id.search_button)
    View mSearchButton;

    public FloatingSearchView(final @NonNull Context context) {
        this(context, null);
    }

    public FloatingSearchView(final @NonNull Context context, final @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingSearchView(final @NonNull Context context, final @Nullable AttributeSet attrs, final @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflateView(context);
    }

    private void inflateView(final @NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_floating_search_view, this);
        ButterKnife.bind(this);

        mQueryField.setImeOptions(mQueryField.getImeOptions() | EditorInfo.IME_ACTION_SEARCH);
        mQueryField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final @NonNull TextView view, final int actionId, final @NonNull KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
                    mSearchButton.performClick();
                    return true;
                }
                return false;
            }
        });

        final ViewTreeObserver observer = mSearchButton.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final ViewTreeObserver observerRef = observer.isAlive() ? observer : mSearchButton.getViewTreeObserver();

                observerRef.removeOnGlobalLayoutListener(this);

                final ViewGroup.LayoutParams queryFieldLayoutParams = mQueryField.getLayoutParams();
                final int standardPadding = (int) context.getResources().getDimension(R.dimen.padding_standard);
                queryFieldLayoutParams.width = mQueryField.getWidth() - mSearchButton.getWidth() / 2 - standardPadding;
                queryFieldLayoutParams.height = mSearchButton.getHeight() + standardPadding * 2;

                mQueryField.setPadding(standardPadding + mQueryField.getPaddingLeft(), mQueryField.getPaddingTop(), standardPadding + mQueryField.getPaddingRight() + mSearchButton.getWidth() / 2, mQueryField.getPaddingBottom());
            }
        });
    }

    public void show() {
        if (getVisibility() == View.VISIBLE)
            return;

        animate().scaleX(0).scaleY(0).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                setVisibility(View.VISIBLE);
                animate().scaleX(1).scaleY(1).setInterpolator(new DecelerateInterpolator()).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime)).start();
            }
        });
    }

    public void hide() {
        if (getVisibility() == View.INVISIBLE)
            return;

        animate().scaleX(0).scaleY(0).setInterpolator(new AccelerateInterpolator()).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime)).withEndAction(new Runnable() {
            @Override
            public void run() {
                setVisibility(View.INVISIBLE);
            }
        }).start();
    }

    public void setSearchCommandCallback(final @Nullable SearchCommandCallback callback) {
        mSearchCommandCallback = callback;
    }

    @OnClick(R.id.search_button)
    public void postQuery() {
        if (mSearchCommandCallback != null && !TextUtils.isEmpty(mQueryField.getText().toString())) {
            mSearchCommandCallback.onSearchRequested(mQueryField.getText());
        }
    }
}
