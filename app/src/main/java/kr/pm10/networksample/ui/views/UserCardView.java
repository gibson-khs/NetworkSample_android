package kr.pm10.networksample.ui.views;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.pm10.networksample.R;
import kr.pm10.networksample.api.models.User;
import kr.pm10.networksample.ui.widgets.MyCircleImageView;

public class UserCardView extends FrameLayout {

    @BindView(R.id.profile_image)
    MyCircleImageView profileImage;
    @BindView(R.id.name)
    TextView userName;
    @BindView(R.id.login_id)
    TextView userId;

    Context context;

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
        profileImage.imageLoad(user.avatarUrl);
        userName.setText(user.getUserName());
        userId.setText(user.loginId);
    }
}
