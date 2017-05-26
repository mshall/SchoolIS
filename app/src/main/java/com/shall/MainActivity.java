package com.shall;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    com.shall.util.fragment.FragmentUtils fragmentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        ((com.shall.network.App) getApplication()).getNetComponent().inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setNavigationItemSelectedListener(this);
        fragmentUtils = new com.shall.util.fragment.FragmentUtils(this);
        fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.notification.NotificationFragment(), com.shall.user.notification.NotificationFragment.TAG);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notifications) {
            fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.notification.NotificationFragment(), com.shall.user.notification.NotificationFragment.TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_my_profile:
                fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.StudentProfileFragment(), com.shall.user.StudentProfileFragment.TAG);
                break;
            case R.id.nav_full_plan:
                fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.plan.StudentFullPlanFragment(), com.shall.user.plan.StudentFullPlanFragment.TAG);
                break;
            case R.id.nav_my_assignments:
                fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.StudentAssignmentsFragment(), com.shall.user.StudentAssignmentsFragment.TAG);
                break;
            case R.id.nav_registered_courses:
                fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.courses.RegisteredCoursesFragment(), com.shall.user.courses.RegisteredCoursesFragment.TAG);
                break;
            case R.id.nav_completed_courses:
                fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.courses.CompletedCoursesFragment(), com.shall.user.courses.CompletedCoursesFragment.TAG);
                break;
            case R.id.nav_remaining_courses:
                fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.courses.RemainingCoursesFragment(), com.shall.user.courses.RemainingCoursesFragment.TAG);
                break;
            case R.id.nav_notifications:
                fragmentUtils.navigateToFragment(R.id.content_main, new com.shall.user.notification.NotificationFragment(), com.shall.user.notification.NotificationFragment.TAG);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
