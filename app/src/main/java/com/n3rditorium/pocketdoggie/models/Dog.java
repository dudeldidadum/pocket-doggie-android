package com.n3rditorium.pocketdoggie.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Dog {

   private Long birthday;
   private String description;
   private Metric height;
   private String identifier;
   private Image image;
   private String name;
   private String uid;
   private Metric weight;

   public Dog() {
      // Default constructor required for calls to DataSnapshot.getValue(User.class)
   }

   public Long getBirthday() {
      return birthday;
   }

   public Dog setBirthday(Long birthday) {
      this.birthday = birthday;
      return this;
   }

   public String getDescription() {
      return description;
   }

   public Dog setDescription(String description) {
      this.description = description;
      return this;
   }

   public Metric getHeight() {
      return height;
   }

   public Dog setHeight(Metric height) {
      this.height = height;
      return this;
   }

   public String getIdentifier() {
      return identifier;
   }

   public Dog setIdentifier(String identifier) {
      this.identifier = identifier;
      return this;
   }

   public Image getImage() {
      return image;
   }

   public Dog setImage(Image image) {
      this.image = image;
      return this;
   }

   public String getName() {
      return name;
   }

   public Dog setName(String name) {
      this.name = name;
      return this;
   }

   public String getUid() {
      return uid;
   }

   public Dog setUid(String uid) {
      this.uid = uid;
      return this;
   }

   public Metric getWeight() {
      return weight;
   }

   public Dog setWeight(Metric weight) {
      this.weight = weight;
      return this;
   }
}
