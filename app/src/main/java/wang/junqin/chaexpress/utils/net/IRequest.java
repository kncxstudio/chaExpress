package wang.junqin.chaexpress.utils.net;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;

/**
 * Created by KN on 2017/5/29.
 */

public interface IRequest {
    @GET("/autonumber/auto")
    Observable<List<ExpressComBean>> getExpressCom(@Query("num") String num);

    @GET("/query")
    Observable<ExpressInfoBean> getExpressInfo(
            @Query("postid") String postId
            ,@Query("type") String com);


}
