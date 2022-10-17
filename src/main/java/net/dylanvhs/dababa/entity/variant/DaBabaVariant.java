package net.dylanvhs.dababa.entity.variant;

import net.dylanvhs.dababa.entity.custom.DaBabaEntity;
import net.minecraft.world.level.biome.Biome;

import java.util.Arrays;
import java.util.Comparator;

public enum DaBabaVariant {
    DEFAULT(0),
    SNOW(1);

    private static final DaBabaVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(DaBabaVariant::getId)).toArray(DaBabaVariant[]::new);
    private final int id;

    DaBabaVariant(int p_30984_) {
        this.id = p_30984_;
    }

    public int getId() {
        return this.id;
    }

    public static DaBabaVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
