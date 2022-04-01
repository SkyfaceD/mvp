package org.skyfaced.mvp.util

sealed class WaifuType(open val innerCategory: Category) {
    class SFW(override val innerCategory: InnerCategory) : WaifuType(innerCategory) {
        enum class InnerCategory : Category {
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
            Cringe;
        }

        override val type: String
            get() = this::class.java.simpleName.lowercase()

        override val category: String
            get() = innerCategory.name.lowercase()
    }

    class NSFW(override val innerCategory: InnerCategory) : WaifuType(innerCategory) {
        enum class InnerCategory : Category {
            Waifu,
            Neko,
            Trap,
            Blowjob;
        }

        override val type: String
            get() = this::class.java.simpleName.lowercase()

        override val category: String
            get() = innerCategory.name.lowercase()
    }

    abstract val type: String

    abstract val category: String
}

interface Category
