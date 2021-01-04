package com.etpl.demoencora.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.etpl.demoencora.R;


public class CustomProgressDialog extends ProgressDialog {

    private Animation animRotate;
    private ImageView imageViewProgress;
    private String message;

    public CustomProgressDialog(Context context, String message) {
        super(context);
        this.message = message;
        animRotate = AnimationUtils.loadAnimation(context, R.anim.custom_progress_dialog);
        setIndeterminate(true);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_view_progress);
        imageViewProgress = (ImageView) findViewById(R.id.image_view_progress);
    }

    @Override
    public void show() {
        super.show();
        imageViewProgress.startAnimation(animRotate);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animRotate.cancel();
    }
}
