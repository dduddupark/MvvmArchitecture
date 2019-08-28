package com.example.suyeon.mvvmarchitecture.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.suyeon.mvvmarchitecture.R;
import com.example.suyeon.mvvmarchitecture.base.BaseDialog;
import com.example.suyeon.mvvmarchitecture.databinding.DialogBasicBinding;

public class BasicDialog extends BaseDialog {

    /**
     * 메세지, 버튼 1개
     */
    public static final int TYPE_NO_TITLE_ONE_BUTTON = 0x10;
    /**
     * 메세지, 버튼 2개
     */
    public static final int TYPE_NO_TITLE_TWO_BUTTON = 0x11;
    /**
     * 제목, 메세지, 버튼 1개
     */
    public static final int TYPE_TITLE_ONE_BUTTON = 0x12;
    /**
     * 제목, 메세지, 버튼 2개
     */
    public static final int TYPE_TITLE_TWO_BUTTON = 0x13;

    /**
     * 팝업 타입 int
     */
    private int mType;
    /**
     * 제목 String
     */
    private String mTitle;
    /**
     * 내용 String
     */
    private String mMsg;
    /**
     * 버튼 1개 String
     */
    private String mBtnText;
    /**
     * 버튼 2개 왼쪽 String
     */
    private String mBtnLeftText;
    /**
     * 버튼 2개 오른쪽 String
     */
    private String mBtnRightText;
    /**
     * 버튼 클릭 리스너
     */
    private Builder.OnClickListener mOnNeutralClickListener;
    /**
     * 버튼 클릭 리스너
     */
    private Builder.OnClickListener mOnPositiveClickListener;
    /**
     * 버튼 클릭 리스너
     */
    private Builder.OnClickListener mOnNegativeClickListener;

    /**
     * 데이터 바인딩
     */
    private DialogBasicBinding mBinding;

    /**
     * BaseDialog
     * 투명한 배경 적용
     *
     * @param context Context
     */
    protected BasicDialog(Context context) {
        super(context);
    }

    public BasicDialog(Context context, Builder builder) {
        super(context);
        this.mType = builder.mType;
        this.mTitle = builder.mTitle;
        this.mMsg = builder.mMsg;
        this.mBtnText = builder.mBtnText;
        this.mBtnLeftText = builder.mBtnLeftText;
        this.mBtnRightText = builder.mBtnRightText;
        this.mOnNeutralClickListener = builder.mOnNeutralClickListener;
        this.mOnPositiveClickListener = builder.mOnPositiveClickListener;
        this.mOnNegativeClickListener = builder.mOnNegativeClickListener;
    }

    @Override
    protected int layoutRes() {
        return R.layout.dialog_basic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = (DialogBasicBinding) getViewDataBinding();

        setContent();
    }

    private void setContent() {
        switch (mType) {
            case TYPE_NO_TITLE_ONE_BUTTON:
                mBinding.tvTitle.setVisibility(View.GONE);
                mBinding.tvContent.setText(mMsg);
                mBinding.llTwoBtn.setVisibility(View.GONE);
                mBinding.btnOk.setText(mBtnText);
                mBinding.btnOk.setOnClickListener(this::onClick);
                break;
            case TYPE_NO_TITLE_TWO_BUTTON:
                mBinding.tvTitle.setVisibility(View.GONE);
                mBinding.tvContent.setText(mMsg);
                mBinding.btnOk.setVisibility(View.GONE);
                mBinding.btnLeft.setText(mBtnLeftText);
                mBinding.btnRight.setText(mBtnRightText);
                mBinding.btnLeft.setOnClickListener(this::onClick);
                mBinding.btnRight.setOnClickListener(this::onClick);
                break;
            case TYPE_TITLE_ONE_BUTTON:
                mBinding.tvTitle.setText(mTitle);
                mBinding.tvContent.setText(mMsg);
                mBinding.llTwoBtn.setVisibility(View.GONE);
                mBinding.btnOk.setText(mBtnText);
                mBinding.btnOk.setOnClickListener(this::onClick);
                break;
            case TYPE_TITLE_TWO_BUTTON:
                mBinding.tvTitle.setText(mTitle);
                mBinding.tvContent.setText(mMsg);
                mBinding.btnOk.setVisibility(View.GONE);
                mBinding.btnLeft.setText(mBtnLeftText);
                mBinding.btnRight.setText(mBtnRightText);
                mBinding.btnLeft.setOnClickListener(this::onClick);
                mBinding.btnRight.setOnClickListener(this::onClick);
                break;
        }

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_ok:
                if (mOnNeutralClickListener != null) {
                    mOnNeutralClickListener.onClick();
                }
                break;
            case R.id.btn_left:
                if (mOnPositiveClickListener != null) {
                    mOnPositiveClickListener.onClick();
                }
                break;
            case R.id.btn_right:
                if (mOnNegativeClickListener != null) {
                    mOnNegativeClickListener.onClick();
                }
                break;
        }

        dismiss();
    }

    public static class Builder {

        /**
         * 제목 String
         */
        private String mTitle;
        /**
         * 내용 String
         */
        private String mMsg;
        /**
         * 버튼 1개 String
         */
        private String mBtnText;
        /**
         * 버튼 2개 왼쪽 String
         */
        private String mBtnLeftText;
        /**
         * 버튼 2개 오른쪽 String
         */
        private String mBtnRightText;
        /**
         * 팝업 타입 int
         */
        private int mType;

        /**
         * 버튼 클릭 리스너
         */
        private Builder.OnClickListener mOnNeutralClickListener;
        /**
         * 버튼 클릭 리스너
         */
        private Builder.OnClickListener mOnPositiveClickListener;
        /**
         * 버튼 클릭 리스너
         */
        private Builder.OnClickListener mOnNegativeClickListener;

        public Builder setType(int type) {
            this.mType = type;
            return this;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setContent(String content) {
            this.mMsg = content;
            return this;
        }

        public Builder setBtnText(String btnText) {
            this.mBtnText = btnText;
            return this;
        }

        public Builder setBtnLeftText(String btnLeftText) {
            this.mBtnLeftText = btnLeftText;
            return this;
        }

        public Builder setBtnRightText(String btnRightText) {
            this.mBtnRightText = btnRightText;
            return this;
        }

        public Builder setOnNeutralClickListener(OnClickListener mOnClickListener) {

            this.mOnNeutralClickListener = mOnClickListener;

            return this;
        }

        public Builder setOnPositiveClickListener(OnClickListener mOnClickListener) {

            this.mOnPositiveClickListener = mOnClickListener;

            return this;
        }

        public Builder setOnNagativeClickListener(OnClickListener mOnClickListener) {

            this.mOnNegativeClickListener = mOnClickListener;

            return this;
        }

        public BaseDialog build(Context context) {
            BaseDialog baseDialog = new BasicDialog(context, this);
            return baseDialog;
        }


        public interface OnClickListener {
            void onClick();
        }
    }

}
