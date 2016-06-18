package com.n3rditorium.pocketdoggie.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Dog {

   private Long birthday;
   private String description;
   private Integer gender;
   private Integer height;
   private Image image;
   private String name;
   private String key;
   private Integer weight;

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

   public Integer getHeight() {
      return height;
   }

   public Dog setHeight(Integer height) {
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

   public String getKey() {
      return key;
   }

   public Dog setKey(String key) {
      this.key = key;
      return this;
   }

   public Integer getWeight() {
      return weight;
   }

   public Dog setWeight(Integer weight) {
      this.weight = weight;
      return this;
   }

   public Map<String, Object> toMap() {
      Map<String, Object> result = new HashMap<>();
      result.put("key", key);
      result.put("name", name);
      result.put("description", description);
      result.put("birthday", birthday);
      result.put("gender", gender);
      result.put("height_in_cm", height);
      result.put("weight_in_g", weight);

      return result;
   }
}
