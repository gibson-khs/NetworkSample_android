package kr.pm10.networksample.ui.views;

import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.pm10.networksample.R;
import kr.pm10.networksample.api.models.User;
import kr.pm10.networksample.library.Constant;
import kr.pm10.networksample.ui.main.WebViewActivity;
import kr.pm10.networksample.ui.widgets.MyCircleImageView;

public class UserCardView extends FrameLayout {

    @BindView(R.id.profile_image)
    MyCircleImageView profileImage;
    @BindView(R.id.name)
    TextView userName;
    @BindView(R.id.login_id)
    TextView userId;

    private Context context;
    private User user;

    public UserCardView(Context context) {
        super(context);
        this.context = context;
        init();
        ButterKnife.bind(this);
    }

    private void init() {
        inflate(context, R.layout.view_user_card, this);
    }

    public void setData(User user) {
        this.user = user;
        profileImage.imageLoad(user.avatarUrl);
        userName.setText(user.getUserName());
        userId.setText(user.loginId);
    }

    @OnClick(R.id.go_github_button)
    void onClickGoGitHubButton() {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constant.INTENT_KEY_URL, user.htmlUrl);
        context.startActivity(intent);
    }
}
