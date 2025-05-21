package com.example.backbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class BackButton extends LinearLayout {

    private ImageView backArrow;
    private TextView backText;

    public BackButton(Context context) {
        super(context);
        init(context, null);
    }

    public BackButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BackButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Inflate the layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.back_button_layout, this, true);

        // Initialize views
        backArrow = view.findViewById(R.id.back_arrow);
        backText = view.findViewById(R.id.back_text);

        // Set default onClickListener to finish the activity
        setOnClickListener(v -> {
            if (context instanceof android.app.Activity) {
                ((android.app.Activity) context).finish();
            }
        });

        // Apply custom attributes if available
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BackButton);

            // Set back text if provided
            String text = a.getString(R.styleable.BackButton_backText);
            if (text != null && !text.isEmpty()) {
                backText.setText(text);
                backText.setVisibility(VISIBLE);
            } else {
                backText.setVisibility(GONE);
            }

            // Set custom icon if provided
            Drawable icon = a.getDrawable(R.styleable.BackButton_backIcon);
            if (icon != null) {
                backArrow.setImageDrawable(icon);
            }

            a.recycle();
        }
    }

    /**
     * Set the text to display next to the back arrow
     * @param text Text to display
     */
    public void setBackText(String text) {
        if (text != null && !text.isEmpty()) {
            backText.setText(text);
            backText.setVisibility(VISIBLE);
        } else {
            backText.setVisibility(GONE);
        }
    }

    /**
     * Set a custom back icon
     * @param drawable Icon drawable to use
     */
    public void setBackIcon(Drawable drawable) {
        if (drawable != null) {
            backArrow.setImageDrawable(drawable);
        }
    }

    /**
     * Set a custom back icon using resource ID
     * @param resId Resource ID of the drawable
     */
    public void setBackIcon(int resId) {
        backArrow.setImageResource(resId);
    }

    /**
     * Set a custom click listener
     * @param listener OnClickListener to set
     */
    @Override
    public void setOnClickListener(@Nullable OnClickListener listener) {
        super.setOnClickListener(listener);
    }
}