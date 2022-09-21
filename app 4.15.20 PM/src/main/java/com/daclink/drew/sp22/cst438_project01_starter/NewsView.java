package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityNewsBinding;

import java.util.List;

public class NewsView extends AppCompatActivity implements DialogInterface.OnDismissListener {
    private ActivityNewsBinding binding;
    private UserDb db;
    private ListView newsListView;
    private List<User> newsList;
    private ArrayAdapter<User> newsAdapter;
    private static final String TAG = "newsList";
    private DialogFragment dialogFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        db = UserDb.getInstance(this);
//        db.populateInitialData();
        newsListView = binding.newsList;

//        newsList = db.getPersonDAO().getNewsSource();
        newsAdapter = new ArrayAdapter<>(this, R.layout.item_news, R.id.news_item, newsList);
        newsListView.setAdapter(newsAdapter);

        if(newsAdapter.getCount()==0) {
            Toast.makeText(NewsView.this,"There are no news available",
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(NewsView.this, MainActivity.class);

            startActivity(i);
        }
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int pos,
                                    long arg3) {

//                String bookString = newsList.get(pos).getNewsSource();
//                Intent i = new Intent(NewsView.this, (newsLink?).class);
//                i.putExtra("item", bookString);
//                Toast.makeText(NewsList.this, bookString,
//                        Toast.LENGTH_LONG).show();
//
//                startActivity(i);
//                // Begin the transaction
//                Log.d("TAG","test");
            }

        });


    }



//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.book_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_add_task:
//                Log.d(TAG, "add new question");
//                dialogFragment.show(getSupportFragmentManager(), "");
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Log.d(TAG, "dialog dismissed");
//        updateUI();
    }
}
