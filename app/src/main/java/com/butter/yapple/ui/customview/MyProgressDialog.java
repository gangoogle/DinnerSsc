package com.butter.yapple.ui.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.butter.yapple.R;


/**
 * Progress dialog.
 *
 * @author hang.zhao
 */
public class MyProgressDialog {

    Dialog dialog;
    Context context;
    boolean flags;

    OnCancelListener onCancelListener = new OnCancelListener() {

        @Override
        public void onCancel(DialogInterface dialog) {
            flags = false;
            dialog.dismiss();
        }
    };

    public MyProgressDialog(Context context) {
        this.context = context;
        flags = true;
        dialog = new Dialog(context, R.style.dialog);
        dialog.setOnCancelListener(onCancelListener);
    }

    public void initDialog(String str) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.my_progress_dialog, null);
        TextView txt = (TextView) view.findViewById(R.id.show_txt);
        txt.setText(str);
        dialog.setContentView(view);
        dialog.show();
    }

    public void dissmisDialog() {
        flags = false;
        dialog.dismiss();
    }

    public boolean isShowing() {
        if (dialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set dialog cancelable on touch outside(t/f).
     *
     * @param cancelable t/f.
     */
    public void setCanceledOnTouchOutside(boolean cancelable) {
        dialog.setCanceledOnTouchOutside(cancelable);
    }

    /**
     * set dialog cancelable.
     *
     * @param cancelable
     */
    public void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }
}
