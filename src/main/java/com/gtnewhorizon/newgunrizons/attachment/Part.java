package com.gtnewhorizon.newgunrizons.attachment;

public interface Part {

    Part MAIN_ITEM = new DefaultPart("MAIN_ITEM");
    Part RIGHT_HAND = new DefaultPart("RIGHT_HAND");
    Part LEFT_HAND = new DefaultPart("LEFT_HAND");
    Part INVENTORY = new DefaultPart("INVENTORY");
    Part NONE = new DefaultPart("NONE");
}
