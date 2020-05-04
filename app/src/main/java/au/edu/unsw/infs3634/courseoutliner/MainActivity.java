package au.edu.unsw.infs3634.courseoutliner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CourseAdapter mAdapter;
    private CourseDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fab.isExtended()) {
                    fab.shrink();
                    fab.setIcon(getDrawable(R.drawable.ic_clear));
                    new GetCoursesTask().execute(true);
                } else {
                    fab.extend();
                    fab.setIcon(getDrawable(R.drawable.ic_filter));
                    new GetCoursesTask().execute(false);
                }
            }
        });

        RecyclerView mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);

        mDb = Room.databaseBuilder(this, CourseDatabase.class, "courses.db").createFromAsset("courses.db").build();

        mAdapter = new CourseAdapter(new ArrayList<Course>(), new CourseAdapter.CourseClickListener() {
            @Override
            public void onClick(int id) {
                //TODO Create an Intent object that launches the DetailActivity using an explicit intent

                intent.putExtra(DetailActivity.CODE_EXTRA, id);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        //TODO Implement code that executes the AsyncTask implemented below without filtering the data

    }

    private class GetCoursesTask extends AsyncTask<Boolean, Void, List<Course>> {

        @Override
        protected List<Course> doInBackground(Boolean... filtered) {
            if(filtered[0]) {
                return mDb.courseDao().getCourses("Info Systems & Tech Mgmt");
            } else {
                return mDb.courseDao().getCourses();
            }
        }

        //TODO Implement an onPostExecute method which updates the CourseAdapter's data

    }
}
