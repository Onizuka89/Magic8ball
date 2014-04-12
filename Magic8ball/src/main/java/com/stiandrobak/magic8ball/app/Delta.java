package com.stiandrobak.magic8ball.app;

/**
 * Created by stiansd on 12.04.14.
 */
public class Delta extends Coordinate{
    public boolean anyLargerThan(float number){
        return xLargerThan(number) || yLargerThan(number) || zLargerThan(number);
    }
    public boolean xLargerThan(float number){
        return this.x > number;
    }
    public boolean yLargerThan(float number){
        return this.y > number;
    }
    public boolean zLargerThan(float number){
        return this.z > number;
    }
}
