package com.n3rditorium.pocketdoggie.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Dog {

   private Long birthday;
   private String description;
   private Integer gender;
   private Metric height;
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

   public Integer getGender() {
      return gender;
   }

   public Dog setGender(Integer gender) {
      this.gender = gender;
      return this;
   }

   public Metric getHeight() {
      return height;
   }

   public Dog setHeight(Metric height) {
      this.height = height;
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

   public Map<String, Object> toMap() {
      Map<String, Object> result = new HashMap<>();
      result.put("uid", uid);
      result.put("name", name);
      result.put("description", description);
      result.put("birthday", birthday);
      result.put("gender", gender);
      result.put("height_in_cm", height.getValue());
      result.put("weight_in_g", weight.getValue());

      return result;
   }
}
