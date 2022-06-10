package com.mygdx.game.movement;

public class MyPair<T, V>
{
    public MyPair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T first;
    public V second;
}
