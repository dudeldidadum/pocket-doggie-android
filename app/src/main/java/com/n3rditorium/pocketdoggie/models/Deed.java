package com.n3rditorium.pocketdoggie.models;

public class Deed {

   public static final int TYPE_PEE = 0;
   public static final int TYPE_POOP = 1;
   private String description;
   private Long dogId;
   private Boolean inside = false;
   private Long latitude;
   private Long longitude;
   private Long timestamp;
   private Integer type;
   private String uid;

   public String getDescription() {
      return description;
   }

   public Deed setDescription(String description) {
      this.description = description;
      return this;
   }

   public Long getDogId() {
      return dogId;
   }

   public Deed setDogId(Long dogId) {
      this.dogId = dogId;
      return this;
   }

   public Boolean getInside() {
      return inside;
   }

   public Deed setInside(Boolean inside) {
      this.inside = inside;
      return this;
   }

   public Long getLatitude() {
      return latitude;
   }

   public Deed setLatitude(Long latitude) {
      this.latitude = latitude;
      return this;
   }

   public Long getLongitude() {
      return longitude;
   }

   public Deed setLongitude(Long longitude) {
      this.longitude = longitude;
      return this;
   }

   public Long getTimestamp() {
      return timestamp;
   }

   public Deed setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
      return this;
   }

   public Integer getType() {
      return type;
   }

   public Deed setType(Integer type) {
      this.type = type;
      return this;
   }

   public String getUid() {
      return uid;
   }

   public Deed setUid(String uid) {
      this.uid = uid;
      return this;
   }
}
