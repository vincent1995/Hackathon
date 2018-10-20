package huang.bling.hackathon.util;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zjl on 2016/11/8.
 */

public class ImageUtil {
    private static File f1 = new File(Environment.getExternalStorageDirectory() + "/95128/" + "image");
    private static File f = new File(Environment.getExternalStorageDirectory() + "/95128/" + "image/" + "portrait.jpg");
    private static String imagePath = Environment.getExternalStorageDirectory() + "/95128/image/portrait.jpg";

    public static final String IMAGEURL = "http://img2.yongxt.com/img2";//图片地址前缀

    //图片显示
    public static void getImage(Context context, Bitmap bitmap, SimpleDraweeView simpleDraweeView) {
        BitmapDrawable bd_camera = new BitmapDrawable(context.getResources(), bitmap);
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setPlaceholderImage(bd_camera);
        simpleDraweeView.setHierarchy(hierarchy);
    }


    //fresco 显示本地缩略图，不直接使用setImageURI，防止卡顿和显示不全：纯黑色或纯白色
    private static boolean isInit = false;

    /**
     * 显示缩略图
     *
     * @param draweeView
     * @param url
     * @param resizeWidthDp
     * @param resizeHeightDp
     */
    public static void showThumb(SimpleDraweeView draweeView, String url, int resizeWidthDp, int resizeHeightDp) {
        if (draweeView == null)
            return;
        initialize(draweeView.getContext());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(IMAGEURL + url))
                .setResizeOptions(new ResizeOptions(dip2px(draweeView.getContext(), resizeWidthDp), dip2px(draweeView.getContext(), resizeHeightDp)))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .build();
        draweeView.setController(controller);
    }

    /**
     * initialize
     *
     * @param context context
     */
    public static void initialize(Context context) {
        if (isInit)
            return;
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(context, config);
        isInit = true;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    //保存图片
    public static void saveImage(final Context context, String url) {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(IMAGEURL + url))
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);

        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {
                if (bitmap == null) {
                    Toast.makeText(context, "保存图片失败", Toast.LENGTH_SHORT).show();
                }
                if (!f1.exists()) {
                    f1.mkdirs();
                }
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    assert bitmap != null;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri uri = Uri.fromFile(f);
                // 通知图库更新
                Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                context.sendBroadcast(scannerIntent);

            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
            }
        }, CallerThreadExecutor.getInstance());
    }


    /**
     * 保存图片到本地
     *
     * @param bitmap
     */
    public static void saveBitmap(Context context, Bitmap bitmap) {
        if (!f1.exists()) {
            f1.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(f);
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(f);
        // 通知图库更新
        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scannerIntent);
    }

    /**
     * 保存图片到本地
     *
     * @param bitmap
     */
    public static void saveBimap2(Context context, Bitmap bitmap, File file) {
        if (!f1.exists()) {
            f1.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(file);
        // 通知图库更新
        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scannerIntent);
    }

    public static void delete(Context context, File file) {
        if (file.exists())
            file.delete();
        Uri uri = Uri.fromFile(file);
        // 通知图库更新
        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scannerIntent);
    }
}
