package com.shall.network.data.component;

import com.shall.MainActivity;
import com.shall.LoginActivity;
import com.shall.network.data.module.AppModule;
import com.shall.network.data.module.NetModule;
import com.shall.user.StudentAssignmentsFragment;
import com.shall.user.StudentProfileFragment;
import com.shall.user.StudentProfileUpdateFragment;
import com.shall.user.courses.CompletedCoursesFragment;
import com.shall.user.courses.RegisteredCoursesFragment;
import com.shall.user.courses.RemainingCoursesFragment;
import com.shall.user.notification.NotificationFragment;
import com.shall.user.plan.StudentFullPlanFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohamed S. El-Shall on 4/8/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    //----------- Inject activities -------------------
    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    //----------- Inject Fragments -------------------
    void inject(NotificationFragment notificationFragment);

    void inject(RemainingCoursesFragment remainingCoursesFragment);

    void inject(CompletedCoursesFragment completedCoursesFragment);

    void inject(StudentAssignmentsFragment studentAssignmentsFragment);

    void inject(RegisteredCoursesFragment registeredCoursesFragment);

    void inject(StudentFullPlanFragment fullPlanFragment);

    void inject(StudentProfileFragment studentProfileFragment);

    //void inject(Fragment fragment);

    void inject(StudentProfileUpdateFragment studentProfileUpdateFragment);
}

