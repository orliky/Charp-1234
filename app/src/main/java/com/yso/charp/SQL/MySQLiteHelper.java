package com.yso.charp.SQL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    //    private static final String DATABASE_NAME = "";
    private static final String TABLE_USERS = "users";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_UID = "uid";

    private static final String[] COLUMNS = {KEY_NAME, KEY_PHONE, KEY_UID};

    public MySQLiteHelper(Context context)
    {
        super(context, TABLE_USERS, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CLIENT_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_PHONE + " TEXT NOT NULL,"
                + KEY_UID + " TEXT NOT NULL);";

        db.execSQL(CREATE_CLIENT_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        this.onCreate(db);
    }


//    public void addBranch(Branch branch)
//    {
//        Log.d("addBranch", branch.toString());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, branch.getName());
//        values.put(KEY_ADDRESS, branch.getAddress());
//        values.put(KEY_MANAGER, branch.getManager());
//        values.put(KEY_LAT, branch.getLatLon().getLatitude());
//        values.put(KEY_LNG, branch.getLatLon().getLongitude());
//
//        db.insert(TABLE_BRANCHES, null, values);
//
//        db.close();
//    }
//
//    public Branch getBranch(int id)
//    {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_BRANCHES, COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null)
//        {
//            cursor.moveToFirst();
//        }
//
//        Branch branch = new Branch();
//        assert cursor != null;
//        branch.setId(Integer.parseInt(cursor.getString(0)));
//        branch.setName(cursor.getString(1));
//        branch.setAddress(cursor.getString(2));
//        branch.setManager(cursor.getString(3));
//        Branch.LatLon latLon  = new Branch.LatLon(cursor.getDouble(4), cursor.getDouble(5));
//        branch.setLatLon(latLon);
//
//        Log.d("getBranch(" + id + ")", branch.toString());
//
//        return branch;
//    }
//
//    public List<Branch> getAllBranches()
//    {
//        List<Branch> branches = new LinkedList<Branch>();
//
//        String query = "SELECT  * FROM " + TABLE_BRANCHES;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
//
//        Branch branch = null;
//        if (cursor.moveToFirst())
//        {
//            do
//            {
//                branch = new Branch();
//                branch.setId(Integer.parseInt(cursor.getString(0)));
//                branch.setName(cursor.getString(1));
//                branch.setAddress(cursor.getString(2));
//                branch.setManager(cursor.getString(3));
//                Branch.LatLon latLon  = new Branch.LatLon(cursor.getDouble(4), cursor.getDouble(5));
//                branch.setLatLon(latLon);
//
//                branches.add(branch);
//            } while (cursor.moveToNext());
//        }
//
//        Log.d("getAllBranches()", branches.toString());
//
//        return branches;
//    }
//
//    public int updateBranch(Branch branch)
//    {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("name", branch.getName());
//        values.put("address", branch.getAddress());
//
//        int i = db.update(TABLE_BRANCHES, values, KEY_ID + " = ?", new String[]{String.valueOf(branch.getId())});
//
//        db.close();
//
//        return i;
//
//    }
//
//    public void deleteBranch(Branch branch)
//    {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.delete(TABLE_BRANCHES, KEY_ID + " = ?", new String[]{String.valueOf(branch.getId())});
//
//        db.close();
//
//        Log.d("deleteBranch", branch.toString());
//
//    }
//
//    public void deleteAllBranch()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_BRANCHES);
//        db.close();
//    }
}
