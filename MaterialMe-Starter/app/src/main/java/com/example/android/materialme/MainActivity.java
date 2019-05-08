package com.example.android.materialme;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Sport> mSportsData;
    private SportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);


        //for adding landscape mood
        int gridColumnCount=getResources().getInteger(R.integer.grid_column_count);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        mSportsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SportsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        //swipedirs
        int swipeDirs;
        if(gridColumnCount>1){
            swipeDirs=0;
        }else{
            swipeDirs=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        }

        // Get the data.
        initializeData();

        ///delet by swipe
        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|
                        ItemTouchHelper.DOWN|ItemTouchHelper.UP,swipeDirs) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                int from =viewHolder.getAdapterPosition();
                int to=target.getAdapterPosition();
                Collections.swap(mSportsData,from,to);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                ///delet right index
                mSportsData.remove(viewHolder.getAdapterPosition());
                //delet animation
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecyclerView);


    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources().getStringArray(R.array.sports_titles);

        String[] sportsInfo = getResources().getStringArray(R.array.sports_info);

        TypedArray sportsImageResource=getResources().obtainTypedArray(R.array.sports_images);

        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<sportsList.length;i++){
            mSportsData.add(new Sport(sportsList[i],sportsInfo[i],
                    sportsImageResource.getResourceId(i,0)));
        }

        sportsImageResource.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();


    }

    public void resetSports(View view) {
        initializeData();
    }
}
