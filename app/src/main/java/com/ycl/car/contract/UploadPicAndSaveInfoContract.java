package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;

import java.io.File;

/**
 * 上传图片并保存信息
 * Created by y11621546 on 2017/2/16.
 */

public interface UploadPicAndSaveInfoContract {
    interface Presenter extends BasePresenter {
        void uploadPicAndSavePMInfo(String uid, String pmid, String rcount, File picFile);

        void savePMInfo(String uid, String pmid, String rcount);

        void uploadPicAndSaveTPMInfo(String uid, String tpmid, String rcount, File picFile);

        void saveTPMInfo(String uid, String tpmid, String rcount);

        void uploadPicAndSaveEqInfo(String uid, String rcont, String id, String rstatus, File picFile);

        void saveEqInfo(String uid, String rcont, String id, String rstatus);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess();

        void onError(String msg);
    }
}
