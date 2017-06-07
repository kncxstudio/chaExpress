package wang.junqin.chaexpress.view.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.utils.DAO.model.ExpressEntityModel;

/**
 * Created by KN on 2017/6/7.
 * 此Activity未使用MVP模式
 */

public class EditDialogActivity extends AppCompatActivity {
    Button submitBtn;
    EditText remarlET;
    ExpressEntityModel model;
    ExpressEntity entity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dialog);
        submitBtn = (Button) findViewById(R.id.fragment_edit_dialog_submit);
        remarlET = (EditText) findViewById(R.id.fragment_edit_dialog_ET);
        model = new ExpressEntityModel();

        final Intent intent = getIntent();
        String expNum = intent.getStringExtra(FLAGS.EXP_NUM);
        entity = model.getEntityByExpNum(expNum);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.setRemark(remarlET.getText().toString());
                model.edit(entity);
                intent.putExtra(FLAGS.REMARK,remarlET.getText().toString());
                setResult(FLAGS.EDIT_REMARK_SUCCESS,intent);
                finish();
            }
        });


    }
}
