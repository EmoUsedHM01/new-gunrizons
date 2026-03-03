package com.gtnewhorizon.newgunrizons.crafting;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

final class RecipeGenerator {

    private final SequenceGenerator sequenceGenerator = new SequenceGenerator(9);

    public List<Object> createShapedRecipe(String name, OptionsMetadata metadata) {
        List<Object> sequence = this.sequenceGenerator.generate(name, this.createSeed(name, metadata), metadata);
        LinkedHashMap<Object, Character> encodingMap = new LinkedHashMap<>();
        char startFrom = 'A';
        OptionsMetadata.OptionMetadata[] var6 = metadata.getMetadata();
        int var7 = var6.length;

        int i;
        for (i = 0; i < var7; ++i) {
            OptionsMetadata.OptionMetadata optionMetadata = var6[i];
            char code;
            if (optionMetadata.getOption() == OptionsMetadata.EMPTY_OPTION) {
                code = ' ';
            } else {
                code = startFrom++;
            }

            encodingMap.put(optionMetadata.getOption(), code);
        }

        List<Object> output = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        i = 0;

        for (Object t : sequence) {
            builder.append(encodingMap.get(t));
            ++i;
            if (i % 3 == 0) {
                output.add(builder.toString());
                builder.setLength(0);
            }
        }

        encodingMap.entrySet()
            .stream()
            .filter((e) -> e.getKey() != OptionsMetadata.EMPTY_OPTION)
            .forEach((e) -> {
                output.add(e.getValue());
                output.add(e.getKey());
            });
        return output;
    }

    private byte[] createSeed(String name, OptionsMetadata metadata) {
        return (name + metadata.getMetadata().length).getBytes();
    }
}
