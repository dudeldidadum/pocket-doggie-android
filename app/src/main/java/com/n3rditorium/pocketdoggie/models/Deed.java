package com.n3rditorium.pocketdoggie.models;

import android.content.Context;
import android.text.format.DateUtils;

import com.n3rditorium.pocketdoggie.utils.Formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deed implements Formatter<Deed> {

   public interface Fields {
      String DESCRIPTION = "description";
      String DOG_REFERENCE = "dogReference";
      String IS_INSIDE = "inside";
      String KEY = "key";
      String LATITUDE = "latitude";
      String LONGITUDE = "longitude";
      String TIMESTAMP = "timestamp";
      String TYPE = "type";
   }

   public interface Type {
      int PEE = 0;
      int POOP = 1;
   }

   private String description;
   private String dogReference;
   private Boolean inside = false;
   private String key;
   private Long latitude;
   private Long longitude;
   private Long timestamp;
   private Integer type;

   public static Deed mockItem() {
      return new Deed().setType(Type.POOP)
            .setInside(false)
            .setDescription("a mocked description")
            .setTimestamp(System.currentTimeMillis())
            .setLatitude(42L)
            .setLongitude(12L)
            .setDogReference("123L");
   }

   public static List<Deed> mockList() {
      List<Deed> mockedItems = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
         Deed mockedItem = mockItem();
         mockedItem.setKey("mock_deed-" + i);
         mockedItems.add(mockedItem);
      }
      return mockedItems;
   }

   @Override
   public String formatTimestamp(Context context) {
      String time = DateUtils.formatDateTime(context, timestamp, DateUtils.FORMAT_SHOW_TIME);
      String day = DateUtils.formatDateTime(context, timestamp, DateUtils.FORMAT_SHOW_DATE);

      return time + " - " + day;
   }

   public String getDescription() {
      return description;
   }

   public Deed setDescription(String description) {
      this.description = description;
      return this;
   }

   public String getDogReference() {
      return dogReference;
   }

   public Deed setDogReference(String dogReference) {
      this.dogReference = dogReference;
      return this;
   }

   public Boolean getInside() {
      return inside;
   }

   public Deed setInside(Boolean inside) {
      this.inside = inside;
      return this;
   }

   public String getKey() {
      return key;
   }

   public Deed setKey(String key) {
      this.key = key;
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

   public Map<String, Object> toMap() {
      Map<String, Object> result = new HashMap<>();

      result.put(Fields.DESCRIPTION, description);
      result.put(Fields.DOG_REFERENCE, dogReference);
      result.put(Fields.IS_INSIDE, inside);
      result.put(Fields.LATITUDE, latitude);
      result.put(Fields.LONGITUDE, longitude);
      result.put(Fields.TIMESTAMP, timestamp);
      result.put(Fields.TYPE, type);
      result.put(Fields.KEY, key);

      return result;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder(getClass().getSimpleName());
      builder.append("\n")
            .append(Fields.KEY)
            .append(": ")
            .append(key)
            .append("\n")
            .append(Fields.TIMESTAMP)
            .append(": ")
            .append(timestamp)
            .append("\n")
            .append(Fields.DESCRIPTION)
            .append(": ")
            .append(description)
            .append("\n")
            .append(Fields.TYPE)
            .append(": ")
            .append(type)
            .append("\n")
            .append(Fields.IS_INSIDE)
            .append(": ")
            .append(inside)
            .append("\n")
            .append(Fields.LATITUDE)
            .append(": ")
            .append(latitude)
            .append("\n")
            .append(Fields.LONGITUDE)
            .append(": ")
            .append(longitude)
            .append("\n")
            .append(Fields.DOG_REFERENCE)
            .append(": ")
            .append(dogReference);
      return builder.toString();
   }
}
