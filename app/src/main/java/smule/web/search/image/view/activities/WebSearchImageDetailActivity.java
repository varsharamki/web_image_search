package smule.web.search.image.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import smule.web.search.R;
import smule.web.search.image.model.data.ImageSearchResults;

public class WebSearchImageDetailActivity extends AppCompatActivity {

    @InjectView(R.id.text)
    TextView text;
    @InjectView(R.id.text1)
    TextView imageDetail;
    @InjectView(R.id.text2)
    TextView imageText;
    @InjectView(R.id.text3)
    TextView imageDetailText;

    private ImageSearchResults imageSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search_image_detail);
        ButterKnife.inject(this);
        getData();
        setUpActionBar();
        populateTextView();
    }

    private void setUpActionBar() {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(getResources().getString(R.string.image_detail_tile));

    }

    private void getData() {
        if (getIntent() != null) {
            if (getIntent().hasExtra(getResources().getString(R.string.image_detail_data))) {
                imageSearchResults = getIntent().getParcelableExtra(getResources().getString(R.string.image_detail_data));
            }
        }
    }

    private void populateTextView() {
        try {
            if (imageSearchResults != null) {
                text.setText(String.format(getResources().getString(R.string.snippet), imageSearchResults.getSnippet()));
                imageText.setText(String.format(getResources().getString(R.string.display_link), imageSearchResults.getDisplayLink()));
                imageDetail.setText(String.format(getResources().getString(R.string.display_title), imageSearchResults.getTitle()));
                imageDetailText.setText(String.format(getResources().getString(R.string.mime), imageSearchResults.getMime()));

            } else {
                // more place holder data for the text view

            }
        } catch (Exception e) {

        } finally {
            imageSearchResults = null;
        }

    }

}
