package com.example.haowenyu.cloudqcq;

import com.example.haowenyu.cloudqcq.datamodel.user;
import com.example.haowenyu.cloudqcq.datamodel.userplayerlist;
import com.example.haowenyu.cloudqcq.view.Activitylogin;
import com.example.haowenyu.cloudqcq.view.PlayerlistActivity;
import retrofit2.Call;


public interface Contract {

    interface View {
        void loginSuccess(user iuser);

        void loginFailed(String msg);
    }

    interface LoginPresenter {
        void doLogin(String username, String password, Activitylogin login );
        void getplaylist(String id, PlayerlistActivity playerlistActivity);

    }

    interface PlayerlistView{
       void getplaylist(userplayerlist userplayerlist);
       void onerror(String message);
    }

}