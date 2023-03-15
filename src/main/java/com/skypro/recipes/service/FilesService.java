package com.skypro.recipes.service;

public interface FilesService {

  boolean saveToFile(String json);

  String readFromFile();

    boolean cleanDataFile();
}
