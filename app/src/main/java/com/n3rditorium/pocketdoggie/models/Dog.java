package com.n3rditorium.pocketdoggie.models;

public class Dog {

   private Long birthday;
   private String description;
   private Metric height;
   private Long id;
   private String identifier;
   private Image image;
   private String name;
   private Metric weight;

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

   public Long getId() {
      return id;
   }

   public Dog setId(Long id) {
      this.id = id;
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

   public Metric getWeight() {
      return weight;
   }

   public Dog setWeight(Metric weight) {
      this.weight = weight;
      return this;
   }
}
