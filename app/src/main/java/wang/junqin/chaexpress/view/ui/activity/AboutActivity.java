package wang.junqin.chaexpress.view.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.utils.MyUtils;

/**
 * Created by KN on 2017/6/7.
 */

public class AboutActivity extends AppCompatActivity {
    TextView projectUrl,version;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_about);
        projectUrl = (TextView) findViewById(R.id.about_project_url);
        version = (TextView) findViewById(R.id.about_version);

        try {
            version.setText(MyUtils.getVerisonInfo(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        projectUrl.setText(FLAGS.PROJECT_URL);
        projectUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri content_url = Uri.parse(FLAGS.PROJECT_URL);
                Intent intent = new Intent(Intent.ACTION_VIEW,content_url);
                startActivity(intent);
            }
        });

    }
}
