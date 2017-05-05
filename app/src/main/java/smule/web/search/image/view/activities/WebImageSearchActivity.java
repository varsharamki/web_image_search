package smule.web.search.image.view.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import smule.web.search.R;
import smule.web.search.image.model.data.ImageSearchResults;
import smule.web.search.image.model.data.WebImageSearchResponse;
import smule.web.search.image.presenter.implementations.WebSearchImagePresenterImpl;
import smule.web.search.image.utils.ConnectionManager;
import smule.web.search.image.view.WebImageSearchAdapter;
import smule.web.search.image.view.interfaces.WebSearchImageView;

public class WebImageSearchActivity extends AppCompatActivity implements WebSearchImageView {

    @InjectView(R.id.activity_web_image_search)
    RelativeLayout relativeLayout;
    @InjectView(R.id.searchImagesRecylerView)
    RecyclerView searchImagesRecylerView;
    @InjectView(R.id.searchingProgressBar)
    ProgressBar searchingProgressBar;
    boolean isLoading = false;
    private MenuItem searchImageMenuItem;
    private WebSearchImageView webSearchImageView;
    private WebImageSearchAdapter webImageSearchAdapter;
    private WebSearchImagePresenterImpl imageSearch;
    private RecyclerView.LayoutManager staggeredGrid;
    private WebImageSearchResponse webImageSearchResponse;
    private ArrayList<ImageSearchResults> webImageSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_image_search);
        ButterKnife.inject(this);
        ActionBar aBar = this.getSupportActionBar();
        if(aBar!=null)
        aBar.setDisplayShowTitleEnabled(false);

        webSearchImageView = this;
        imageSearch = new WebSearchImagePresenterImpl(this, webSearchImageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchImageMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchImageMenuItem.getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setIconified(false);
        searchView.clearFocus();
        Random rndm=new Random();
        String[] inital={"Smule","Sing","Piano","Music"};
        searchView.setQuery(inital[rndm.nextInt(inital.length)],true);
        imageSearch.sendSearchQuery(inital[rndm.nextInt(inital.length)],1);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SMULE1 OnquerySubmit ", query);
                imageSearch.sendSearchQuery(query, 1);

                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SMULE1 OnqueryChange ", newText);
                imageSearch.sendSearchQuery(newText, 1);

                return true;
            }
        });
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {

                return true;

            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        searchImageMenuItem = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void displayImageSearch(WebImageSearchResponse response) {
        webImageSearchResponse = response;
        if (response != null && response.getSearchResponse() != null) {
            if (webImageSearchResults != null) {
                webImageSearchResults.addAll(response.getSearchResponse());
            } else {
                webImageSearchResults = (ArrayList<ImageSearchResults>) response.getSearchResponse();
            }
            webImageSearchAdapter = new WebImageSearchAdapter(this, webImageSearchResults);
            webImageSearchAdapter.notifyDataSetChanged();
        }
        populateRecyclerView(webImageSearchResults);
    }

    private void populateRecyclerView(final ArrayList<ImageSearchResults> results) {
        if (results != null && results.size() > 0) {
            final int totalItemsCount = (webImageSearchResponse.getRequest().getTotalResults() > 1000) ? 1000 : webImageSearchResponse.getRequest().getTotalResults();
            try {
                Log.d("SMULE1 ::", "popeulateRec " + results.size());
                webImageSearchAdapter = new WebImageSearchAdapter(this, results);
                staggeredGrid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                searchImagesRecylerView.setAdapter(webImageSearchAdapter);
                searchImagesRecylerView.setLayoutManager(staggeredGrid);
                searchImagesRecylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int firstVisiblePosition[] = ((StaggeredGridLayoutManager) staggeredGrid).findFirstCompletelyVisibleItemPositions(null);

                        if (dy < 0) {
                            if (!isLoading && (firstVisiblePosition != null && firstVisiblePosition[0] == 1) && results.size() < totalItemsCount) {
                                isLoading = true;
                                searchingProgressBar.setVisibility(View.VISIBLE);
                                imageSearch.sendSearchQuery(webImageSearchResponse.getRequest().getSearchTerms(), webImageSearchResponse.getRequest().getStartIndex() + 10);
                            }
                        }

                    }
                });

            } catch (Exception e) {

            } finally {
                webImageSearchResults = null;
                webImageSearchResponse = null;
            }
        } else {

        }
    }

    private void processSearchIntent(Intent intent) {


        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            if (ConnectionManager.isNetworkAvailable(this)) {
                imageSearch.sendSearchQuery(intent.getStringExtra(SearchManager.QUERY), 1);
            } else {
                Toast.makeText(this, "No internet connection available.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processSearchIntent(intent);
    }

}
