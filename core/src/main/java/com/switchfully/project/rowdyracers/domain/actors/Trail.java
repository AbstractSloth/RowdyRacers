package com.switchfully.project.rowdyracers.domain.actors;

public class Trail implements GameObject{

    private int timeLeft;

    private final boolean belongsToPlayerOne;

    public Trail(boolean belongsToPlayerOne) {
        this.belongsToPlayerOne = belongsToPlayerOne;
        timeLeft = 3;
    }

    public boolean isBelongsToPlayerOne() {
        return belongsToPlayerOne;
    }

    public boolean countDown(){
        if(timeLeft-- == 0)return true;

        return false;
    }


}
