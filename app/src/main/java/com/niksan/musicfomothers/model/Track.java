package com.niksan.musicfomothers.model;


import android.support.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("duration")
    @Expose
    private String duration;

    private int fileSize;

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    @SerializedName("imgurl")
    @Expose
    private String imgurl;
    @SerializedName("musicurl")
    @Expose
    private String musicurl;

    //private String filePath;

   // private static boolean playStatus = false;
   // private static boolean downloadingStatus = false;
   // private static boolean fileExist = false;
    public static boolean isPlaying = false;

    public static int playingItem =  -1;
    public static boolean referedToActivity = false;


    public  String getDuration() {
        return duration;
    }

    public  void setDuration(String duration) {
        this.duration = duration;
    }
/*
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }*/

  /*  public static boolean isFileExist() {
        return fileExist;
    }

    public static void setFileExist(boolean fileExist) {
        Track.fileExist = fileExist;
    }*/

   /* public static boolean getDownloadingStatus() {
        return downloadingStatus;
    }

    public static void setDownloadingStatus(boolean downloadingStatus) {
        Track.downloadingStatus = downloadingStatus;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getMusicurl() {
        return musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl;
    }

/*    public static boolean getPlayStatus() {
        return playStatus;
    }

    public static void setPlayStatus(boolean playStatus) {
        Track.playStatus = playStatus;
    }*/
}