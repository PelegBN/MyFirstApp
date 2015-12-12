package com.example.peleg.myfirstapp;

import android.provider.BaseColumns;

/**
 * Created by Peleg on 12/12/2015.
 */
public class TasksDbContract {
    public static final class TaskEntry implements BaseColumns {

        public static final String TABLE_NAME = "tasks";

        public static final String COLUMN_TASK_DESCRIPTION = "task_description";

        public static final String COLUMN_TASK_STATUS = "task_status";
    }
}
