package com.example.shivamkalra.fab;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton fab, fabsmall, fabsmall1, fabsmall2, fabsmall3;
    Animation fab_close, fab_open, rotate_clock, rotate_anti;
    boolean is_open = false;
    int image_count = 0;
    Button but;
    Bitmap image;
    Intent intent;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabsmall = (FloatingActionButton) findViewById(R.id.fabsmall);
        // This is for adding image to a folder

        fabsmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


            }
        });

        fabsmall1 = (FloatingActionButton) findViewById(R.id.fabsmall1);

        fabsmall2 = (FloatingActionButton) findViewById(R.id.fabsmall2);

        fabsmall3 = (FloatingActionButton) findViewById(R.id.fabsmall3);


        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        rotate_anti = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_mainfab_anti);
        rotate_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_mainfab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     Snackbar.make(view, "One button is shown always for adding", Snackbar.LENGTH_LONG)
                //             .setAction("Action", null).show();

                if (is_open) {
                    switch(image_count){
                        case 1:fabsmall1.startAnimation(fab_close);
                            fabsmall1.setClickable(false);
                            break;
                        case 2:fabsmall1.startAnimation(fab_close);
                            fabsmall2.startAnimation(fab_close);
                            fabsmall1.setClickable(false);
                            fabsmall2.setClickable(false);
                            break;
                        case 3:
                              fabsmall1.startAnimation(fab_close);
                              fabsmall2.startAnimation(fab_close);
                              fabsmall3.startAnimation(fab_close);
                              fabsmall1.setClickable(false);
                              fabsmall2.setClickable(false);
                              fabsmall3.setClickable(false);
                              break;
                    }
                    fabsmall.startAnimation(fab_close);
                    fab.startAnimation(rotate_anti);

                    fabsmall.setClickable(false);

                    is_open = false;

                } else {
                    switch(image_count){
                        case 1:fabsmall1.startAnimation(fab_open);
                               fabsmall1.setClickable(true);
                               break;
                        case 2:fabsmall1.startAnimation(fab_open);
                               fabsmall1.setClickable(true);
                               fabsmall2.startAnimation(fab_open);
                               fabsmall2.setClickable(true);
                               break;
                        case 3:fabsmall1.startAnimation(fab_open);
                               fabsmall1.setClickable(true);
                               fabsmall2.startAnimation(fab_open);
                               fabsmall2.setClickable(true);
                               fabsmall3.startAnimation(fab_open);
                               fabsmall3.setClickable(true);
                               break;

                    }
                    fab.startAnimation(rotate_clock);
                    fabsmall.startAnimation(fab_open);
                    fabsmall.setClickable(true);
                    is_open = true;


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

           try {
               image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
               // Log.d(TAG, String.valueOf(bitmap));
               //got image from gallery
               ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
               File file = wrapper.getDir("Myapp",MODE_PRIVATE);
               file = new File(file, image_count+".jpg");
               OutputStream stream = null;
               stream = new FileOutputStream(file);
               image.compress(Bitmap.CompressFormat.JPEG,100,stream);
               stream.flush();
               stream.close();


           }catch(IOException e){
               Log.e("Io exception caught", "Check image");
               e.printStackTrace();
           }


        }

        if(image_count<3){
            image_count++;
        }else{
            image_count=3;
        }
    }

}
