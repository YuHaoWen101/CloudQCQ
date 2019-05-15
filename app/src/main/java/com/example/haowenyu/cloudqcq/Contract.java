package com.example.haowenyu.cloudqcq;

import com.example.haowenyu.cloudqcq.datamodel.playlist_detail;
import com.example.haowenyu.cloudqcq.datamodel.user;
import com.example.haowenyu.cloudqcq.datamodel.userplayerlist;
import com.example.haowenyu.cloudqcq.view.Activitylogin;
import com.example.haowenyu.cloudqcq.view.PlayListDetail;
import com.example.haowenyu.cloudqcq.view.PlayerlistActivity;
import retrofit2.Call;


public interface Contract {

    interface View {
        void loginSuccess(user iuser);

        void loginFailed(String msg);
    }

    interface Presenter {
        void doLogin(String username, String password, Activitylogin login );
        void getplaylist(String id, PlayerlistActivity playerlistActivity);
        void getlistdetail(String id,PlayListDetail playListDetail);
    }

    interface PlayerlistView{
       void getplaylist(userplayerlist userplayerlist);
       void onerror(String message);
    }


    interface Playlist_detail{
        void getplaydetail(playlist_detail detail);
        void onerror(String message);
    }

    interface Song_play{
        void getUrl(String id);
        void onerror(String message);
    }
}