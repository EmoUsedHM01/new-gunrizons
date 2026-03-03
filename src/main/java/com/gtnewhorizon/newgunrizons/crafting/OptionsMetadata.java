package com.gtnewhorizon.newgunrizons.crafting;

import java.util.LinkedHashMap;

import lombok.Getter;

public class OptionsMetadata {

    public static final Object EMPTY_OPTION = new Object();

    @Getter
    private final OptionsMetadata.OptionMetadata[] metadata;
    private final boolean hasOres;

    private OptionsMetadata(OptionsMetadata.OptionMetadata[] metadata, boolean hasOres) {
        this.metadata = metadata;
        this.hasOres = hasOres;
    }

    public boolean hasOres() {
        return this.hasOres;
    }

    public static class OptionMetadataBuilder {

        LinkedHashMap<Object, OptionsMetadata.OptionMetadata> optionMetadata = new LinkedHashMap<>();
        private int slotCount;

        public OptionsMetadata.OptionMetadataBuilder withSlotCount(int slotCount) {
            this.slotCount = slotCount;
            return this;
        }

        public OptionsMetadata.OptionMetadataBuilder withOption(int minOccurs, int maxOccurs) {
            OptionsMetadata.OptionMetadata metadata = new OptionsMetadata.OptionMetadata(
                OptionsMetadata.EMPTY_OPTION,
                minOccurs,
                maxOccurs);
            metadata.minOccurs = minOccurs;
            metadata.maxOccurs = maxOccurs;
            this.optionMetadata.put(OptionsMetadata.EMPTY_OPTION, metadata);
            return this;
        }

        public OptionsMetadata.OptionMetadataBuilder withOption(Object option, int minOccurs, int maxOccurs) {
            if (minOccurs > maxOccurs) {
                throw new IllegalArgumentException("Min occurs must be less or equals maxOccurs");
            } else {
                OptionsMetadata.OptionMetadata metadata = new OptionsMetadata.OptionMetadata(
                    option,
                    minOccurs,
                    maxOccurs);
                metadata.minOccurs = minOccurs;
                metadata.maxOccurs = maxOccurs;
                this.optionMetadata.put(option, metadata);
                return this;
            }
        }

        public OptionsMetadata build(CraftingComplexity complexity, Object... options) {
            int complexityIndex = complexity.ordinal() + 1;
            if (options.length * complexityIndex > this.slotCount) {
                throw new IllegalArgumentException("Too many options for complexity level " + complexity);
            } else {
                for (Object option : options) {
                    if (option == null) {
                        throw new IllegalArgumentException(
                            "Option cannot be null, make sure to initialize it before generating receipe");
                    }
                    this.withOption(option, complexityIndex, complexityIndex);
                }

                this.withOption(OptionsMetadata.EMPTY_OPTION, 0, this.slotCount - options.length * complexityIndex);
                return this.build();
            }
        }

        public OptionsMetadata build() {
            if (this.slotCount == 0) {
                throw new IllegalStateException("Slot count not set");
            } else {
                int totalMaxOccurs = 0;
                int totalMinOccurs = 0;
                boolean hasOres = false;
                for (OptionMetadata m : this.optionMetadata.values()) {
                    totalMaxOccurs += m.maxOccurs;
                    totalMinOccurs += m.minOccurs;
                    if (m.getOption() instanceof String) {
                        hasOres = true;
                    }
                }

                if (totalMaxOccurs < this.slotCount) {
                    throw new IllegalStateException("Total slot count is less than total max occurs");
                } else if (totalMinOccurs > this.slotCount) {
                    throw new IllegalStateException("Total max occurs exceeds the number of slots");
                } else {
                    OptionsMetadata.OptionMetadata[] metadata = this.optionMetadata.entrySet()
                        .stream()
                        .map((e) -> new OptionMetadata(e.getKey(), e.getValue().minOccurs, e.getValue().maxOccurs))
                        .toArray(OptionMetadata[]::new);
                    return new OptionsMetadata(metadata, hasOres);
                }
            }
        }
    }

    static class OptionMetadata {

        private int minOccurs;
        private int maxOccurs;
        private final Object option;

        private OptionMetadata(Object option, int minOccurs, int maxOccurs) {
            this.option = option;
            this.minOccurs = minOccurs;
            this.maxOccurs = maxOccurs;
        }

        protected int getMinOccurs() {
            return this.minOccurs;
        }

        protected int getMaxOccurs() {
            return this.maxOccurs;
        }

        protected Object getOption() {
            return this.option;
        }
    }
}
