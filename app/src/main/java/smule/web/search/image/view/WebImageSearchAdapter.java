package smule.web.search.image.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import smule.web.search.R;
import smule.web.search.image.model.data.ImageSearchResults;
import smule.web.search.image.view.activities.WebImageSearchActivity;
import smule.web.search.image.view.activities.WebSearchImageDetailActivity;


public class WebImageSearchAdapter extends RecyclerView.Adapter<WebImageSearchAdapter.WebImageSearchViewHolder> {

    Context context;
    ArrayList<ImageSearchResults> imageSearchResults;
boolean isImageLoaded=false;
    public WebImageSearchAdapter(Context context, ArrayList<ImageSearchResults> imageSearchResults) {
        this.context = context;
        this.imageSearchResults = imageSearchResults;
        Log.d("SMULE1 ::", "adaptercons " + imageSearchResults.size());
        if (imageSearchResults != null) {
            for (ImageSearchResults search : imageSearchResults) {
                Log.d("SMULE1 ::", search.getImage().getContextLink() + "  " + search.getImage().getThumbnailLink());
            }
        }
    }

    @Override
    public WebImageSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View imageSearchItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_search_item, parent, false);
        return new WebImageSearchViewHolder(imageSearchItemView);
    }

    @Override
    public void onBindViewHolder(WebImageSearchViewHolder holder, int position) {
      try {
          if (imageSearchResults != null) {
              final ImageSearchResults imageResult = imageSearchResults.get(position);
              OkHttpClient okHttpClient = new OkHttpClient();
              OkHttpDownloader downloader = new OkHttpDownloader(okHttpClient);
              Picasso picasso = new Picasso.Builder(context).downloader(downloader).build();
if((imageResult.getImage().getHeight()/imageResult.getImage().getThumbnailHeight()>10)){
                  picasso.load(imageResult.getLink()).fit().resize(imageResult.getImage().getWidth()/10,imageResult.getImage().getHeight()/10).into(holder.imageSearch, new Callback() {
                      @Override
                      public void onSuccess() {
                          isImageLoaded = true;
                      }

                      @Override
                      public void onError() {
                          isImageLoaded = false;
                      }

                  });
              }else{
    picasso.load(imageResult.getLink()).fit().into(holder.imageSearch, new Callback() {
        @Override
        public void onSuccess() {
            isImageLoaded = true;
        }

        @Override
        public void onError() {
            isImageLoaded = false;
        }

    });
              }
                 holder.imageSearch.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          playMusicOnClick();
                          Intent imageDetailIntent = new Intent(context, WebSearchImageDetailActivity.class);
                          imageDetailIntent.putExtra(context.getResources().getString(R.string.image_detail_data), imageResult);
                          context.startActivity(imageDetailIntent);
                      }
                  });
                  holder.textFooter.setText(imageResult.getImage().getContextLink());
                  Linkify.addLinks(holder.textFooter, Linkify.ALL);
                  holder.textFooter.setLinkTextColor(Color.parseColor("#6e1e57"));
                  holder.shareImage.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {

                          Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                          sharingIntent.setType("text/plain");
                          String shareBody = "Here is the share content body";
                          sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                          sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                          context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                      }
                  });

          } else {
              // need a place holder for all of them
          }
      }catch(Exception e){

      }finally {
          isImageLoaded=false;
      }
    }

    private void playMusicOnClick(){
        try{
             Random random=new Random();
             final int[] resID ={R.raw.first,R.raw.second,R.raw.third,R.raw.fourth,R.raw.fifth,R.raw.sixth,
             R.raw.seventh,R.raw.eight,R.raw.nineth,R.raw.tenth,R.raw.eleventh,R.raw.twelth,R.raw.thirteenth};
            final MediaPlayer mp = MediaPlayer.create(context,resID[random.nextInt(resID.length)]);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {

                    mp.reset();
                    mp.release();
                    mp=null;
                }

            });
            mp.start();
        }catch(Exception e){

        }finally {

        }
    }
    @Override
    public int getItemCount() {
        return imageSearchResults.size();
    }

    public class WebImageSearchViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.imageSearch)
        ImageView imageSearch;
        @InjectView(R.id.textFooter)
        TextView textFooter;
        @InjectView(R.id.shareImage)
        ImageView shareImage;


        public WebImageSearchViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageSearch = ButterKnife.findById(itemView, R.id.imageSearch);
            textFooter = ButterKnife.findById(itemView, R.id.textFooter);
            shareImage = ButterKnife.findById(itemView, R.id.shareImage);

        }
    }

}
