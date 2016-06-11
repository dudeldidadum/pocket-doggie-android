package com.n3rditorium.pocketdoggie.models;

public class Metric {

   public static final int UNIT_CENTIMETERS = 100;
   public static final int UNIT_GRAMM = 200;
   public static final int UNIT_KILOGRAMM = 201;
   public static final int UNIT_KILOMETERS = 102;
   public static final int UNIT_METERS = 101;
   private Long dogId;
   private Long id;
   private Long timestamp;
   private Integer unit;
   private Integer value;

   public static Metric buildHeight(int dogId, int height, long timestamp) {
      return new Metric().setValue(height)
            .setUnit(UNIT_CENTIMETERS)
            .setTimestamp(timestamp);
   }

   public static Metric buildWeight(int dogId, int weight, long timestamp) {
      return new Metric().setValue(weight)
            .setUnit(UNIT_GRAMM)
            .setTimestamp(timestamp);
   }

   public Long getDogId() {
      return dogId;
   }

   public Metric setDogId(long dogId) {
      this.dogId = dogId;

      return this;
   }

   public Long getId() {
      return id;
   }

   public Metric setId(long id) {
      this.id = id;
      return this;
   }

   public Long getTimestamp() {
      return timestamp;
   }

   public Metric setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
   }

   public Integer getUnit() {
      return unit;
   }

   public Metric setUnit(int unit) {
      this.unit = unit;
      return this;
   }

   public Integer getValue() {
      return value;
   }

   public Metric setValue(int value) {
      this.value = value;
      return this;
   }
}
