package kr.pm10.networksample.ui.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.pm10.networksample.R;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void loadingStart() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void loadingEnd() {
        progressBar.setVisibility(View.GONE);
    }
}
