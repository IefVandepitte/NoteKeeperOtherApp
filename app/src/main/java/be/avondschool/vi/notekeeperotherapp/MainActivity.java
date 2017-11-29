package be.avondschool.vi.notekeeperotherapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int LOADER_NOTEKEEPER_COURSES = 0;
    private SimpleCursorAdapter mCoursesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCoursesAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
                new String[]{"course_title", "course_id"},
                new int[] {android.R.id.text1, android.R.id.text2}, 0);
        ListView listCourses = (ListView)  findViewById(R.id.list_courses);
        listCourses.setAdapter(mCoursesAdapter);

        getLoaderManager().initLoader(LOADER_NOTEKEEPER_COURSES, null, this);
    }


//region LoaderManager implementation
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse("content://com.example.ief.notekeeperpluralsight.provider");
        String[] columns = {"_id", "course_title", "course_id"};

        return  new CursorLoader(this, uri, columns, null, null, "course_title" );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCoursesAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCoursesAdapter.changeCursor(null );
    }
    //endregion
}
