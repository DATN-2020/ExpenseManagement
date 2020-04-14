package datn.datn_expansemanagement.core.app.util.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import datn.datn_expansemanagement.R;

public class GlideImageHelper implements ImageHelper {

    private RequestOptions fitCenter;
    private RequestOptions centerCrop;
    private Context mContext;

    public GlideImageHelper(Context context) {
        mContext = context;

        fitCenter = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_account_circle)
                .priority(Priority.HIGH);


        centerCrop = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bkg_passport)
                .error(R.drawable.bkg_passport)
                .priority(Priority.HIGH);
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadThumbnail(ImageView view, Object path, int resId) {
        if (resId != -1) {
            fitCenter.error(resId)
                    .placeholder(resId);
        }
        loadImageDetail(view, path, true, 0.5f, null, null);
    }

    @Override
    public void loadImageDetail(ImageView view, Object url, boolean isFitCenter, float sizeMultiplier,
                                final OnLoadSuccessListener successListener, final OnLoadFailedListener failedListener) {
        Context context = view.getContext();
        Glide.with(context)
                .load(url)
                .apply(isFitCenter ? fitCenter : centerCrop)
                .thumbnail(sizeMultiplier)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (failedListener != null) {
                            failedListener.onFailed(e);
                        }
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        fitCenter.placeholder(R.drawable.no_image)
                                .error(R.drawable.no_image);

                        if (successListener != null) {
                            successListener.onSuccess(resource);
                        }
                        return false;
                    }
                })
                .into(view);
    }
}
