package com.example.literaturaalura.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> dataClass);
}
