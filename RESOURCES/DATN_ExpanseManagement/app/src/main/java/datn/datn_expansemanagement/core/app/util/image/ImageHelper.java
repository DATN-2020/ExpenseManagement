package datn.datn_expansemanagement.core.app.util.image;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface ImageHelper {

    void loadThumbnail(ImageView view, Object path, int resId);
    void loadImageDetail(ImageView view, Object url,
                         boolean isFitCenter,
                         float sizeMultiplier,
                         OnLoadSuccessListener successListener,
                         OnLoadFailedListener failedListener);

    interface OnLoadFailedListener {
        void onFailed(Exception e);
    }

    interface OnLoadSuccessListener {
        void onSuccess(Drawable drawable);
    }
}
