package com.gtnewhorizon.newgunrizons.crafting;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SequenceGenerator {

    private static final String ALG_NAME = "SHA1PRNG";
    private Map<List<Object>, Object> uniqueSequenceMap;
    private SecureRandom random;
    private int slotCount;

    SequenceGenerator(int slotCount) {
        this.slotCount = slotCount;
        this.uniqueSequenceMap = new HashMap<>();

        try {
            this.random = SecureRandom.getInstance(ALG_NAME);
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException("Failed to initialize sequence generator: " + var3, var3);
        }
    }

    public List<Object> generate(Object obj, byte[] seed, OptionsMetadata optionMetadata) {
        this.random.setSeed(seed);
        return this.generateUniqueSequence(obj, optionMetadata);
    }

    private List<Object> generateUniqueSequence(Object obj, OptionsMetadata optionMetadata) {
        List result;
        do {
            result = this.generateSequence(optionMetadata.getMetadata());
        } while (this.uniqueSequenceMap.putIfAbsent(result, obj) != null);

        return result;
    }

    private List<Object> generateSequence(OptionsMetadata.OptionMetadata[] optionMetadata) {
        Object[] slotValues = new Object[this.slotCount];
        int[] optionDistribution = new int[optionMetadata.length];

        do {
            Arrays.fill(optionDistribution, 0);
            Arrays.fill(slotValues, 0);

            for (int slot = 0; slot < this.slotCount; ++slot) {
                int selectedOption = this.random.nextInt(optionMetadata.length);
                optionDistribution[selectedOption]++;
                slotValues[slot] = optionMetadata[selectedOption].getOption();
            }
        } while (!this.hasSufficientDistribution(optionDistribution, optionMetadata));

        return Arrays.asList(slotValues);
    }

    private boolean hasSufficientDistribution(int[] slotDistribution, OptionsMetadata.OptionMetadata[] optionMetadata) {
        boolean result = true;

        for (int i = 0; i < slotDistribution.length; ++i) {
            if (slotDistribution[i] < optionMetadata[i].getMinOccurs()
                || slotDistribution[i] > optionMetadata[i].getMaxOccurs()) {
                result = false;
                break;
            }
        }

        return result;
    }
}
