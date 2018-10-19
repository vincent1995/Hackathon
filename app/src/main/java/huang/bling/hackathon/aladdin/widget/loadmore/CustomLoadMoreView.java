package huang.bling.hackathon.aladdin.widget.loadmore;


import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.sunlandgroup.aladdin.R;


/**
 * Created by zjl on 2017/9/5.
 */

public final class CustomLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
