package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";
    private ViewPager pager;
    private PagerAdapter adapter;
    private SearchFragment sf;
    private MoviesFragment mf;
    private DetailFragment df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sf = SearchFragment.newInstance();

        pager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new MoviePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

    }

//    //respond to search button clicking
//    public void handleSearchClick(View v){
//        EditText text = (EditText)findViewById(R.id.txtSearch);
//        String searchTerm = text.getText().toString();
//
//        //add a new results fragment to the page
//        MoviesFragment fragment = MoviesFragment.newInstance(searchTerm);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.container, fragment, "MoviesFragment");
//        ft.addToBackStack(null); //remember for the back button
//        ft.commit();
//    }

    @Override
    public void onMovieSelected(Movie movie) {
        df = DetailFragment.newInstance(movie.toString(), movie.imdbId);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(2);
    }

    @Override
    public void OnSearchSubmitted(String searchTerm) {
        mf = MoviesFragment.newInstance(searchTerm);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(1);

    }

    private class MoviePagerAdapter extends FragmentStatePagerAdapter {
        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            
            if (position == 0) {
                return sf;
            } else if (position == 1) {
                return mf;
            } else if (position == 2) {
                return df;
            }
            return null;
        }

        public int getCount() {
            if (mf == null) {
                return 1;
            } else if (df == null) {
                return 2;
            } else {
                return 3;
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
