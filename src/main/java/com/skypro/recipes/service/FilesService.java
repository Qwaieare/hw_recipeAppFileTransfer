package com.skypro.recipes.service;

public interface FilesService {

  boolean saveToFile(String json);

  boolean saveToFile1(String json);

  String readFromFile();

  String readFromFile1();

  boolean cleanDataFile();

  boolean cleanDataFile1();
}
