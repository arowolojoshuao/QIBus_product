package app.iqonicthemes.qibus;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import app.iqonicthemes.qibus.utils.CustomToast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;

public class BaseActivity extends AppCompatActivity {

    /*variable declaration*/
    public CustomToast mToast;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.bg_toolbar);
            window.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    /*show custom toast*/
    public void showToast(String aContent) {
        mToast.setCustomView(aContent);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToast = new CustomToast(this);
        setStatusBarGradiant(this);
    }

    /*set type face*/
    public void setTypeFace(EditText tv) {
        Typeface face = Typeface.createFromAsset(getAssets(), "font/googlesansregular.ttf");
        tv.setTypeface(face);
    }

    /*load fragment*/
    public void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            fadeOutIn(findViewById(R.id.frame_container));
        }
    }

    /*Animation*/
    public void RunLayoutAnimation(RecyclerView recyclerView) {
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.anim_up_to_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    /*Fade out in*/
    public void fadeOutIn(View view) {
        view.setAlpha(0);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 0.5f, 1f);
        ObjectAnimator.ofFloat(view, "alpha", 0f).start();
        animatorAlpha.setDuration(300);
        animatorSet.play(animatorAlpha);
        animatorSet.start();
    }

    /*intent*/
    public void startActivity(Class aClass) {
        startActivity(new Intent(this, aClass));
    }

    /*show view*/
    public void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /*hide view*/
    public void hideView(View view) {
        view.setVisibility(View.GONE);
    }

    /*invisible view*/
    public void invisibleView(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    /* set notification */
    public void SetNotificationImage(ImageView view) {
        Glide.with(this).load(R.raw.gif_bell).into(view);
    }

    /* load image */
    public void setImage(int i,ImageView imageView) {
        Glide.with(this).load(i).into(imageView);
    }
}
