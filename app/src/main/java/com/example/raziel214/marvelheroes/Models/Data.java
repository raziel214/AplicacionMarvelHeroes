package com.example.raziel214.marvelheroes.Models;

public class Data<T> {

    private int total;
    private T result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
