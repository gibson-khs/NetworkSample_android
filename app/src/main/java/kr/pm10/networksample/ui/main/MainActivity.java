package kr.pm10.networksample.ui.main;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import kr.pm10.networksample.R;
import kr.pm10.networksample.api.GitHubApi;
import kr.pm10.networksample.api.NetworkRequest;
import kr.pm10.networksample.api.models.User;
import kr.pm10.networksample.library.utils.Utils;
import kr.pm10.networksample.ui.base.BaseActivity;
import kr.pm10.networksample.ui.views.UserCardView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.container)
    LinearLayout containerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.input_search_user)
    EditText inputSearchUser;
    UserCardView userCardView;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnTextChanged(R.id.input_search_user)
    void onInputUserId(CharSequence text) {
        userId = String.valueOf(text);
    }

    @OnEditorAction(R.id.input_search_user)
    boolean onActionSearch(TextView v, int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH)
            getUser();
        return false;
    }

    @OnClick(R.id.search_button)
    void onClickSearchButton() {
        inputSearchUser.onEditorAction(EditorInfo.IME_ACTION_SEARCH);
    }

    private void getUser() {
        if (userId == null || userId.isEmpty())
            return;
        loadingStart();
        NetworkRequest.request(GitHubApi.getInstance().service().getUserInfomation(userId),
                user -> {
                    setUserCard(user);
                    loadingEnd();
                },
                throwable -> {
                    showErrorToast(throwable);
                    setUserCard(null);
                    loadingEnd();
                });
    }

    private void setUserCard(User user) {
        if (userCardView == null) {
            userCardView = new UserCardView(this);
            containerView.addView(userCardView);
        }
        if (user == null) {
            containerView.removeView(userCardView);
            userCardView = null;
            return;
        }
        userCardView.setData(user);
    }


    private void showErrorToast(Throwable throwable) {
        int errorCode = Utils.getNetworkErrorCode(throwable);
        if (errorCode == 404) {
            Toast.makeText(this, getResources().getString(R.string.network_error_not_exist), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
        }
    }

}
