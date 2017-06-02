package wang.junqin.chaexpress.view.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

import io.objectbox.query.Query;
import wang.junqin.chaexpress.DAO.DAOUtils;
import wang.junqin.chaexpress.DAO.ExpressEntity;
import wang.junqin.chaexpress.R;

/**
 * Created by KN on 2017/6/2.
 */

public class TestFragment extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.fragment_test_textview);

        Query query = DAOUtils.getClassBox(ExpressEntity.class)
                .query()
                .build();
        List<ExpressEntity> entityList = query.find();

        StringBuilder builder = new StringBuilder();

        for (ExpressEntity entity : entityList){
            String str = new Gson().toJson(entity);
            builder.append(str);
        }

        textView.setText(builder.toString());

    }
}
