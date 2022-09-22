package com.switchfully.project.rowdyracers.domain.actors;

public class Player implements GameObject{

    private final boolean isPLayerOne;

    public Player(boolean isPLayerOne) {
        this.isPLayerOne = isPLayerOne;
    }


    public boolean isPLayerOne() {
        return isPLayerOne;
    }
}
