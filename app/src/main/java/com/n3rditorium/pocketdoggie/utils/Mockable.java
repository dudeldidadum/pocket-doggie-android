package com.n3rditorium.pocketdoggie.utils;

import java.util.List;

public interface Mockable<Item> {

   Item mockItem();

   List<Item> mockList();
}
