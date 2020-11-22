package com.ski.box.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.ski.box.BuildConfig;
import com.ski.box.R;
import com.ski.box.bean.UpdateBean;
import com.ski.box.bean.UpdateData;
import com.ski.box.mvp.service.UrlConfig;
import com.ski.box.view.view.dialog.update.DownDialog;
import com.ski.box.view.view.dialog.update.UpdateDialog;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate._XUpdate;
import com.xuexiang.xupdate.service.OnFileDownloadListener;
import com.xuexiang.xupdate.utils.FileUtils;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

import static com.xuexiang.xupdate.entity.UpdateError.ERROR.DOWNLOAD_PERMISSION_DENIED;

/**
 * Created by tom on 2020/11/23.
 */
public class UpdateUtil {
    public final static int REQUEST_CODE_REQUEST_PERMISSIONS = 111;
    private static UpdateDialog mUpdateDialog;
    private static DownDialog mDownDialog;
    private static String mVersion;
    private static Activity mActivity;
    private static boolean mIsToast;

    public static void checkVersion(Activity activity, boolean isToast) {
        mActivity = activity;
        mIsToast = isToast;
        mVersion = AppUtil.getVersion(activity);
        String baseUrl = RetrofitHelper.getInstance().getBaseUrl();
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        OkHttpUtils.get().url(baseUrl + UrlConfig.UPDATE_URL).build();
        OkHttpUtils.get()
                .url(baseUrl + UrlConfig.UPDATE_URL)
                .addParams("version", String.valueOf(mVersion))
                .addParams("merAcct", mActivity.getString(R.string.app_code))
                .addParams("mobType", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        // callBack.onError(e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        setData(response);

                    }
                });
    }

    private static void setData(String response) {
        try {
            Gson gson = new Gson();
            UpdateData updateData = gson.fromJson(response.toString(), UpdateData.class);
            UpdateBean updateBean = updateData.getData();
            if (0 == updateData.getCode()) {
                if (null == updateBean && mIsToast) {
                    ToastUtil.showNormal("已是最新版本");
                    return;
                }
                String remoteVersion = updateBean.getVersion();
                String currentVersion = mVersion;

                int remoteVersion_int = getVersionInt(remoteVersion);
                int currentVersion_int = getVersionInt(currentVersion);
                if (remoteVersion_int > currentVersion_int) {
                    judgeUpdate(mActivity, updateBean);
                }  else {
                    if(mIsToast) {
                        ToastUtil.showNormal("已是最新版本");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void judgeUpdate(Context context, UpdateBean updateBean) {
        // 再次判断是否需要更新
        mUpdateDialog = new UpdateDialog(context, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateDialog.dismiss();
                checkPermissionBeforeDownloadApp(mUpdateDialog.getUpdateBean());
            }
        });
        mUpdateDialog.setUpdateBean(updateBean);
        mUpdateDialog.show();
    }

    private static int getVersionInt(String version) {
        int versionInt = 0;
        if (!TextUtils.isEmpty(version) && version.contains(".")) {
            try {
                version = version.replace(".", "");
                versionInt = Integer.parseInt(version);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return versionInt;
    }


    private static void checkPermissionBeforeDownloadApp(UpdateBean updateBean) {
        int flag = ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (flag != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mActivity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_REQUEST_PERMISSIONS);
            }
        } else {
            download(updateBean);
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //升级
                if (mUpdateDialog != null) {
                    checkPermissionBeforeDownloadApp(mUpdateDialog.getUpdateBean());
                }
            } else {
                _XUpdate.onUpdateError(DOWNLOAD_PERMISSION_DENIED);
                if (mUpdateDialog != null) {
                    mUpdateDialog.dismiss();
                }
            }
        }

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_REQUEST_PERMISSIONS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //升级
//                if (mUpdateDialog != null) {
//                    checkPermissionBeforeDownloadApp(mUpdateDialog.getUpdateBean());
//                }
//            } else {
//                _XUpdate.onUpdateError(DOWNLOAD_PERMISSION_DENIED);
//                if (mUpdateDialog != null) {
//                    mUpdateDialog.dismiss();
//                }
//            }
//        }
//
//    }

    private static void download(UpdateBean updateBean) {
        mDownDialog = new DownDialog(mActivity);
        mDownDialog.show();

//        String downloadUrl = updateBean.getUpdate_url();
//        File dir = new File(PathUtils.getExtDownloadsPath() + File.separator + updateBean.getVersion());
//        if(!dir.exists()) {
//            dir.mkdir();
//        }

        String downloadUrl = "https://xuexiangjys.oss-cn-shanghai.aliyuncs.com/apk/xupdate_demo_1.0.2.apk";
        XUpdate.newBuild(mActivity)
                .apkCacheDir(FileUtils.getExtDownloadsPath()) //设置下载缓存的根目录
                .build()
                .download(downloadUrl, new OnFileDownloadListener() {   //设置下载的地址和下载的监听
                    @Override
                    public void onStart() {
                        //   HProgressDialogUtils.showHorizontalProgressDialog(getContext(), "下载进度", false);
                    }

                    @Override
                    public void onProgress(float progress, long total) {
                        //  HProgressDialogUtils.setProgress(Math.round(progress * 100));
                        mDownDialog.setProgress(progress, total);
                    }


                    @Override
                    public boolean onCompleted(File file) {
                        // HProgressDialogUtils.cancel();
                        // ToastUtils.toast("apk下载完毕，文件路径：" + file.getPath());
                        LogUtils.e("apk下载完毕，文件路径：" + file.getPath());
                        return true;
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //   HProgressDialogUtils.cancel();
                        LogUtils.e(throwable.getMessage());
                        mDownDialog.downloadError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDownDialog.dismiss();
                                if (1 == updateBean.getIs_force()) {
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                }
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDownDialog.setDownReadyStatus();
                                checkPermissionBeforeDownloadApp(updateBean);
                            }
                        });
                    }
                });
    }
}
