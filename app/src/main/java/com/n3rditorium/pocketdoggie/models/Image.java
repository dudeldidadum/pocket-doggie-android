package com.n3rditorium.pocketdoggie.models;

public abstract class Image {

   static final int TYPE_LOCAL = 0;
   static final int TYPE_REMOTE = 1;

   private long id;
   private String imageUrl;
}
