package com.example.suyeon.mvvmarchitecture.view.dialog;

import android.app.Dialog;
import android.content.Context;

import com.example.suyeon.mvvmarchitecture.base.BaseDialog;

/**
 * Dialog 관리
 */
public class DialogFactory {

    /**
     * AlertDialog 생성
     *
     * @param context      Context
     * @param message      메세지
     * @param positiveText 확인 버튼 문구
     * @param listener     확인 버튼 클릭 리스너
     * @return AlertDialog
     */
    public static Dialog createDialog(Context context,
                                      String message,
                                      String positiveText,
                                      BasicDialog.Builder.OnClickListener listener) {


        BaseDialog baseDialog = new BasicDialog.Builder()
                .setType(BasicDialog.TYPE_NO_TITLE_ONE_BUTTON)
                .setContent(message)
                .setBtnText(positiveText)
                .setOnNeutralClickListener(listener)
                .build(context);

        return baseDialog;
    }

    /**
     * AlertDialog 생성
     *
     * @param context      Context
     * @param title        제목
     * @param message      메세지
     * @param positiveText 확인 버튼 문구
     * @param listener     확인 버튼 클릭 리스너
     * @return AlertDialog
     */
    public static Dialog createDialog(Context context,
                                      String title,
                                      String message,
                                      String positiveText,
                                      BasicDialog.Builder.OnClickListener listener) {


        BaseDialog baseDialog = new BasicDialog.Builder()
                .setTitle(title)
                .setType(BasicDialog.TYPE_TITLE_ONE_BUTTON)
                .setContent(message)
                .setBtnText(positiveText)
                .setOnNeutralClickListener(listener)
                .build(context);

        return baseDialog;
    }

    /**
     * AlertDialog 생성
     *
     * @param context          Context
     * @param message          메세지
     * @param negativeText     취소 버튼 문구
     * @param positiveText     확인 버튼 문구
     * @param nagativeListener 취소 버튼 클릭 리스너
     * @param positiveListener 확인 버튼 클릭 리스너
     * @return AlertDialog
     */
    public static Dialog createDialog(Context context,
                                      String message,
                                      String negativeText,
                                      String positiveText,
                                      BasicDialog.Builder.OnClickListener nagativeListener,
                                      BasicDialog.Builder.OnClickListener positiveListener) {

        BaseDialog baseDialog = new BasicDialog.Builder()
                .setType(BasicDialog.TYPE_NO_TITLE_TWO_BUTTON)
                .setContent(message)
                .setBtnLeftText(negativeText)
                .setBtnRightText(positiveText)
                .setOnNagativeClickListener(nagativeListener)
                .setOnPositiveClickListener(positiveListener)
                .build(context);

        return baseDialog;
    }

    /**
     * AlertDialog 생성
     *
     * @param context          Context
     * @param title            제목
     * @param message          메세지
     * @param negativeText     취소 버튼 문구
     * @param positiveText     확인 버튼 문구
     * @param nagativeListener 취소 버튼 클릭 리스너
     * @param positiveListener 확인 버튼 클릭 리스너
     * @return AlertDialog
     */
    public static Dialog createDialog(Context context,
                                      String title,
                                      String message,
                                      String negativeText,
                                      String positiveText,
                                      BasicDialog.Builder.OnClickListener nagativeListener,
                                      BasicDialog.Builder.OnClickListener positiveListener) {

        BaseDialog baseDialog = new BasicDialog.Builder()
                .setType(BasicDialog.TYPE_TITLE_TWO_BUTTON)
                .setTitle(title)
                .setContent(message)
                .setBtnLeftText(negativeText)
                .setBtnRightText(positiveText)
                .setOnNagativeClickListener(nagativeListener)
                .setOnPositiveClickListener(positiveListener)
                .build(context);

        return baseDialog;
    }
}
