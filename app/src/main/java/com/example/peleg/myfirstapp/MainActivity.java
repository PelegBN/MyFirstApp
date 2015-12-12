package com.example.peleg.myfirstapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnDataSourceChangeListener {

    private TasksController controller;
    private TaskItemBaseAdapter adapter;

    static final int GET_TASK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create the controller.
        controller = new TasksController(this);
        // register for OnDataSourceChangedListener event.
        controller.registerOnDataSourceChanged(this);


        ListView lv = (ListView) findViewById(R.id.tasksList);
        lv.setLongClickable(true);
        //handle the list view
        if (lv != null) {
            //create the adapter and get the data from the controller.
            adapter = new TaskItemBaseAdapter(this,
                    controller.getAllTasks());
            lv.setAdapter(adapter);
            //register for Long item click listener.
            //When the user will do a long press on item in the list, the item will be removed.
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    TaskItem t = (TaskItem) adapter.getItem(position);
                    controller.removeTask(t);
                    return true;
                }
            });
        }
    }


    /** Called when the user clicks the Plus button */
    public void createNewTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivityForResult(intent, GET_TASK_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == GET_TASK_REQUEST) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String taskDesc = extras.getString(AppConst.ExtrasTaskName);
                TaskItem t = new TaskItem();
                t.setDescription(taskDesc);

                long edit_task_id = extras.getLong(AppConst.ExtrasTaskId);
                if (edit_task_id != 0) {
                    t.setTaskId(edit_task_id);
                    t.changeStatus("false");
                    controller.editTask(t);
                }
                else
                {
                    t.changeStatus("false");
                    controller.addTask(t);

                }
            }

        }
    }

    @Override
    public void DataSourceChanged() {
        if (adapter != null) {
            adapter.UpdateDataSource(controller.getAllTasks());
            adapter.notifyDataSetChanged();
        }

    }


    public void editTask(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);

        TaskItem t = (TaskItem) adapter.getItem(position);
        String task_desc = t.getDescription();
        long task_id = t.getTaskId();

        Intent intent = new Intent(this, NewTaskActivity.class);
        intent.putExtra(AppConst.ExtrasTaskName, task_desc);
        intent.putExtra(AppConst.ExtrasTaskId, task_id);
        startActivityForResult(intent, GET_TASK_REQUEST);
    }

    public void changeStatus(View view) {

        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);


        TaskItem t = (TaskItem) adapter.getItem(position);

        String status = t.getStatus().toString();
        String newStatus = "false".toString();

        if (status.equals( newStatus ))
        {
            t.changeStatus("true");
        }
        else t.changeStatus("false");
        controller.changeStatus(t);

    }


}
