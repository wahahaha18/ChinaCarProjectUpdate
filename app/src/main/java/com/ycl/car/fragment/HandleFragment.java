package com.ycl.car.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.contract.UploadPicAndSaveInfoContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.UploadPicAndSaveInfoPresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * 处理
 * Created by y11621546 on 2017/2/15.
 */

public class HandleFragment extends BasePageFragment implements UploadPicAndSaveInfoContract.View, View.OnClickListener {
    private static final String TAG = "HandleFragment";
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.et_result)
    EditText etResult;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    private Bundle bundle;
    private static final int CAMERA_CODE = 1;
    private static final int GALLERY_CODE = 2;
    private static final int CROP_CODE = 3;
    private File img;
    private boolean isSelectPic;
    private RxPermissions rxPermissions;
    private LoginResponse.BBean userInfo;
    private UploadPicAndSaveInfoPresenter presenter;
    private KProgressHUD kProgressHUD;
    private EqRepairFragment fragment;
    private int rstatus = 1;

    public static HandleFragment newInstance(Bundle bundle) {


        HandleFragment fragment = new HandleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_no:
                rstatus = 2;
                btnNo.setSelected(true);
                btnYes.setSelected(false);
                break;
            case R.id.btn_yes:
                rstatus = 1;
                btnNo.setSelected(false);
                btnYes.setSelected(true);
                break;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        rxPermissions = new RxPermissions(getActivity());
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", ""), LoginResponse.BBean.class);
        presenter = new UploadPicAndSaveInfoPresenter(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handle, container, false);
        ButterKnife.bind(this, view);
        tvTop.setText(bundle.getString("date") + "   " + bundle.getString("name") + "\n" + bundle.getString("content"));
        Log.d(TAG, bundle.getString("id"));
        getToolbarRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("tpm".equals(bundle.getString("flag"))) {
                    if (isSelectPic) {
                        presenter.uploadPicAndSaveTPMInfo(String.valueOf(userInfo.getId()), bundle.getString("id"), etResult.getText().toString(), img);
                    } else {
                        presenter.saveTPMInfo(String.valueOf(userInfo.getId()), bundle.getString("id"), etResult.getText().toString());
                    }

                } else if ("pm".equals(bundle.getString("flag"))) {
                    if (isSelectPic) {
                        presenter.uploadPicAndSavePMInfo(String.valueOf(userInfo.getId()), bundle.getString("id"), etResult.getText().toString(), img);
                    } else {
                        presenter.savePMInfo(String.valueOf(userInfo.getId()), bundle.getString("id"), etResult.getText().toString());
                    }

                } else {
                    if (isSelectPic) {
                        presenter.uploadPicAndSaveEqInfo(String.valueOf(userInfo.getId()), etResult.getText().toString(), bundle.getString("id"), String.valueOf(rstatus), img);
                    } else {
                        presenter.saveEqInfo(String.valueOf(userInfo.getId()), etResult.getText().toString(), bundle.getString("id"), String.valueOf(rstatus));
                    }

                }
            }
        });
        if (!bundle.getString("flag", "").equals("repair")) {
            llTop.setVisibility(View.VISIBLE);
            btnNo.setVisibility(View.GONE);
            btnYes.setVisibility(View.GONE);
        } else {
            llTop.setVisibility(View.GONE);
            btnNo.setVisibility(View.VISIBLE);
            btnYes.setVisibility(View.VISIBLE);
            btnYes.setSelected(true);
            btnNo.setSelected(false);
        }
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setTitle("选择图片")
                        .setSingleChoiceItems(new String[]{"拍照", "相册"}, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0://拍照
                                        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                .subscribe(new Consumer<Boolean>() {
                                                    @Override
                                                    public void accept(Boolean aBoolean) throws Exception {
                                                        if (aBoolean) {
                                                            chooseFromCamera();
                                                        } else {
                                                            Toast.makeText(getActivity(), "请授予相机和存储权限", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                        break;
                                    case 1://相册
                                        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                .subscribe(new Consumer<Boolean>() {
                                                    @Override
                                                    public void accept(Boolean aBoolean) throws Exception {
                                                        if (aBoolean) {
                                                            chooseFromGallery();
                                                        } else {
                                                            Toast.makeText(getActivity(), "请授予相机和存储权限", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });


                                        break;
                                }
                                dialogInterface.dismiss();
                            }
                        });
                builder.show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("检测信息");
        setTitleRight("提交");
    }


    /**
     * 拍照选择图片
     */
    private void chooseFromCamera() {
        //构建隐式Intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //调用系统相机
        startActivityForResult(intent, CAMERA_CODE);
    }

    /**
     * 从相册选择图片
     */
    private void chooseFromGallery() {
        //构建一个内容选择的Intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //设置选择类型为图片类型
        intent.setType("image/*");
        //打开图片选择
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("HandleFragment", "requestCode:" + requestCode);
        Uri uri;
        switch (requestCode) {
            case CAMERA_CODE:
                //用户点击了取消
                if (data == null) {
                    return;
                } else {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //获得拍的照片
                        Bitmap bm = extras.getParcelable("data");
                        //将Bitmap转化为uri
                        uri = saveBitmap(bm, "temp");
                        //启动图像裁剪
                        startImageZoom(uri);
//                        UCrop.of(uri,destinationUri)
//                                .withAspectRatio(16, 9)
//                                .withMaxResultSize(maxWidth, maxHeight)
//                                .start(getActivity());
                    }
                }
                break;
            case GALLERY_CODE:
                if (data == null) {
                    return;
                } else {
                    //用户从图库选择图片后会返回所选图片的Uri
//                    Uri uri;
                    //获取到用户所选图片的Uri
                    uri = data.getData();
//                    //返回的Uri为content类型的Uri,不能进行复制等操作,需要转换为文件Uri
//                    uri = convertUri(uri);
                    startImageZoom(uri);

//                    UCrop.of(uri, destinationUri)
//                            .withAspectRatio(16, 9)
//                            .withMaxResultSize(maxWidth, maxHeight)
//                            .start(getActivity());
                }
                break;
            case CROP_CODE:
                if (data == null) {
                    return;
                } else {
//                    final Uri resultUri = UCrop.getOutput(data);
//                    //设置裁剪完成后的图片显示
//                    imgPic.setImageURI(resultUri);
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //获取到裁剪后的图像
                        Bitmap bm = extras.getParcelable("data");
                        saveBitmap(bm, "temp");
                        imgPic.setImageBitmap(bm);
                        isSelectPic = true;
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 将content类型的Uri转化为文件类型的Uri
     *
     * @param uri
     * @return
     */
    private Uri convertUri(Uri uri) {
        InputStream is;
        try {
            //Uri ----> InputStream
            is = getActivity().getContentResolver().openInputStream(uri);
            //InputStream ----> Bitmap
            Bitmap bm = BitmapFactory.decodeStream(is);
            //关闭流
            is.close();
            return saveBitmap(bm, "temp");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Bitmap写入SD卡中的一个文件中,并返回写入文件的Uri
     *
     * @param bm
     * @param dirPath
     * @return
     */
    private Uri saveBitmap(Bitmap bm, String dirPath) {
        //新建文件夹用于存放裁剪后的图片
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/" + dirPath);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }

        //新建文件存储裁剪后的图片
        img = new File(tmpDir.getAbsolutePath() + "/avator.png");
        try {
            //打开文件输出流
            FileOutputStream fos = new FileOutputStream(img);
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            //刷新输出流
            fos.flush();
            //关闭输出流
            fos.close();
            //返回File类型的Uri
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 通过Uri传递图像信息以供裁剪
     *
     * @param uri
     */
    private void startImageZoom(Uri uri) {
        //构建隐式Intent来启动裁剪程序
        Intent intent = new Intent("com.android.camera.action.CROP");
        //设置数据uri和类型为图片类型
        intent.setDataAndType(uri, "image/*");
        //显示View为可裁剪的
        intent.putExtra("crop", true);
        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //输出图片的宽高均为150
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //裁剪之后的数据是通过Intent返回
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_CODE);
    }


    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getActivity())
                .setLabel("正在加载...").show();
    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");

    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
        MyApp.getInstance().setState("afdasfd");
        getActivity().onBackPressed();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), "提交失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(UploadPicAndSaveInfoContract.Presenter presenter) {

    }
}
