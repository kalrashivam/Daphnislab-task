package com.example.shivamkalra.fab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton fab,fabsmall,fabsmall1,fabsmall2,fabsmall3;
    Animation fab_close,fab_open,rotate_clock,rotate_anti;
    boolean is_open=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabsmall=(FloatingActionButton) findViewById(R.id.fabsmall);

        fabsmall1=(FloatingActionButton) findViewById(R.id.fabsmall1);

        fabsmall2=(FloatingActionButton) findViewById(R.id.fabsmall2);

        fabsmall3=(FloatingActionButton) findViewById(R.id.fabsmall3);


        fab_close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fab_open=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        rotate_anti=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_mainfab_anti);
        rotate_clock=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_mainfab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Snackbar.make(view, "One button is shown always for adding", Snackbar.LENGTH_LONG)
           //             .setAction("Action", null).show();

                if(is_open){
                    fab.startAnimation(rotate_anti);
                    fabsmall.startAnimation(fab_close);
                    fabsmall1.startAnimation(fab_close);
                    fabsmall2.startAnimation(fab_close);
                    fabsmall3.startAnimation(fab_close);
                    fabsmall1.setClickable(false);
                    fabsmall2.setClickable(false);
                    fabsmall3.setClickable(false);
                    fabsmall.setClickable(false);

                    is_open=false;

                }else{
                    fab.startAnimation(rotate_clock);
                    fabsmall.startAnimation(fab_open);
                    fabsmall1.startAnimation(fab_open);
                    fabsmall2.startAnimation(fab_open);
                    fabsmall3.startAnimation(fab_open);
                    fabsmall1.setClickable(true);
                    fabsmall2.setClickable(true);
                    fabsmall3.setClickable(true);
                    fabsmall.setClickable(true);
                    is_open=true;


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
}
