package src.com.entity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import src.com.util.DownloadApkService;

/**
 * Created by Wangsz on 2017/6/9.
 */

public class UpdateManager {
    private Context mContext;

    // 提示语
    private String updateMsg = "有最新的软件包哦，亲快下载吧~";

    // 返回你需要安装的安装包url
    private String apkUrl = "http://192.168.0.105:8888/SchoolProject/apk/teacherApp-debug.apk";
    private Dialog noticeDialog;
    public UpdateManager(Context context) {
        this.mContext = context;
    }

    // 外部接口让主Activity调用
    public void checkUpdateInfo() {
        showNoticeDialog();
    }

    private void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("软件版本更新");
        builder.setMessage(updateMsg);
        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog();
            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    private void showDownloadDialog() {
        Intent intent = new Intent(mContext,DownloadApkService.class);
        intent.putExtra("apkUrl",apkUrl);
        mContext.startService(intent);
    }
}
