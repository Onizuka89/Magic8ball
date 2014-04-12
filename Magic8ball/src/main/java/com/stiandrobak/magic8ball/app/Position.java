package com.stiandrobak.magic8ball.app;

import java.security.InvalidParameterException;

/**
 * Created by stiansd on 12.04.14.
 */
public class Position extends Coordinate{

    /**
     * Creates a Position from an array of 3 or more floats, uses only the 3 first.
     * @param values float array with x, y and then z position in float
     * @return a new Position object.
     */
    public static Position positionFromArray(float[] values){
        if(values.length < 3){
            throw new InvalidParameterException("Not enought floats in array!");
        }
        Position position = new Position();
        position.x = values[0];
        position.y = values[1];
        position.z = values[2];

        return position;
    }

    /**
     * Returns a delta object with the delta between the two positions.
     * @param deltaPosition the position to compare this position to
     * @return the delta of the positions
     */
    public Delta delta(Position deltaPosition){
        Delta delta = new Delta();
        delta.x = calculateDelta(this.x, deltaPosition.x);
        delta.y = calculateDelta(this.y, deltaPosition.y);
        delta.z = calculateDelta(this.z, deltaPosition.z);
        return delta;
    }

    /**
     * Helper method to calculate deltas
     * @param origin x,z, or y of this delta
     * @param delta x,z, or y of the other position
     * @return the difference between the to positions
     */
    private float calculateDelta(float origin, float delta){
        return Math.abs(origin - delta);
    }

}
