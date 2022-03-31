package org.skyfaced.mvp.util;

public class WaifuType {
    public String getName() {
        throw new IllegalArgumentException("Override this method.");
    }

    public String getCategory() {
        throw new IllegalArgumentException("Override this method.");
    }

    public static class SFW extends WaifuType {
        private final Category category;

        public SFW(Category category) {
            this.category = category;
        }

        @Override
        public String getName() {
            return this.getClass().getSimpleName().toLowerCase();
        }

        @Override
        public String getCategory() {
            return category.name().toLowerCase();
        }

        public enum Category {
            Waifu,
            Neko,
            Shinobu,
            Megumin,
            Bully,
            Cuddle,
            Cry,
            Hug,
            Awoo,
            Kiss,
            Lick,
            Pat,
            Smug,
            Bonk,
            Yeet,
            Blush,
            Smile,
            Wave,
            Highfive,
            Handhold,
            Nom,
            Bite,
            Glomp,
            Slap,
            Kill,
            Happy,
            Wink,
            Poke,
            Dance,
            Cringe
        }
    }

    public static class NSFW extends WaifuType {
        private final Category category;

        public NSFW(Category category) {
            this.category = category;
        }

        @Override
        public String getName() {
            return this.getClass().getSimpleName().toLowerCase();
        }

        @Override
        public String getCategory() {
            return category.name().toLowerCase();
        }

        public enum Category {
            Waifu,
            Neko,
            Trap,
            Blowjob
        }
    }
}
