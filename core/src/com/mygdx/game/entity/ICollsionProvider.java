package com.mygdx.game.entity;

import com.mygdx.game.repo.Collision;

import java.util.ArrayList;

public interface ICollsionProvider {
    ArrayList<Collision> getCollisions();
}
