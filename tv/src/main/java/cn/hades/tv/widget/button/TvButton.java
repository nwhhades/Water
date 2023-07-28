package cn.hades.tv.widget.button;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.core.content.res.ResourcesCompat;

import com.blankj.utilcode.util.AdaptScreenUtils;

import cn.hades.tv.R;

public class TvButton extends AppCompatCheckedTextView {

    public TvButton(Context context) {
        super(context, null);
        initView();
    }

    public TvButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TvButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //这里判断一下是否配置style
        if (attrs != null) {
            int styleAttribute = attrs.getStyleAttribute();
            if (styleAttribute == 0) {
                initView();
            }
        }
    }

    private void initView() {
        setBackgroundResource(R.drawable.btn_bg_card1);
        setGravity(Gravity.CENTER);
        setSingleLine(true);
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        setTextAlignment(TEXT_ALIGNMENT_GRAVITY);
        setTextColor(ResourcesCompat.getColorStateList(getResources(), R.color.btn_text_color, null));
        int paddingTop = AdaptScreenUtils.pt2Px(20);
        int paddingStart = AdaptScreenUtils.pt2Px(60);
        setPadding(paddingStart, paddingTop, paddingStart, paddingTop);
    }

}
