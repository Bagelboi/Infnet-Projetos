package com.example.charactermakerrpg

import android.graphics.drawable.Drawable

enum class CHAR_SKILL {
    DPS, TANK, HEAL, PASS
}

fun getSkillTypeDrawable(type : CHAR_SKILL) : Int {
    return when (type) {
        CHAR_SKILL.DPS -> R.drawable.ability_dps
        CHAR_SKILL.TANK -> R.drawable.ability_tank
        CHAR_SKILL.HEAL -> R.drawable.ability_heal
        CHAR_SKILL.PASS -> R.drawable.ability_passive
    }
}

class charskill(
    val type : CHAR_SKILL,
    val name : String,
    val level : UInt,
    val desc : String = ""
){}

class charclass (
    val name : String,
    var skills : List<charskill>,
    val desc : String = "",
    val logo : Int = R.drawable.ic_launcher_background
) {
    init {
        var skills_sorted = skills.toMutableList()
        skills_sorted.sortWith(compareBy({it.level}, {it.type}))
        skills = skills_sorted
    }
}

class character(
  val name : String,
  val gender : String,
  val level : UInt,
  val age : UInt,
  val class_index : Int){

    fun getAgeSuffix() : String {
        if (age >= 300U)
            return "imortal"
        else if (age >= 100U)
            return "ancião"
        else if (age >= 65U)
            return "velho"
        else if (age >= 35U )
            return "de meia-idade"
        else if (age >= 18U)
            return "adulto"
        else if (age >= 13U)
            return "adolescente"
        else if (age >= 8U )
            return "criança"
        else if (age >= 2U)
            return "recem-nascido"
        else
            return "ainda não nascido"
    }

    fun getSkillsIndexList( cskills : List<charskill> ) : ArrayList<Int>{
        val cskills_index = arrayListOf<Int>()

        for (i in 0..cskills.size-1) {
            if (level >= cskills[i].level)
                cskills_index.add(i)
        }

        return cskills_index
    }
}

// Definitions



val CLASS_WARRIOR = charclass( "Guerreiro",
    listOf<charskill>(
        charskill(CHAR_SKILL.DPS, "Soco", 1U, "Usar o punho para machucar, 1 de dano"),
        charskill(CHAR_SKILL.DPS, "Chute", 1U, "Usar o pe para machucar, 2 de dano"),
        charskill(CHAR_SKILL.PASS, "Resistencia Natural", 1U, "Voce possui uma resistencia acima do normal e absorve 2% de qualquer dano"),
        charskill(CHAR_SKILL.TANK, "Resistir", 5U, "Absorve 10% de dano do proximo ataque"),
        charskill(CHAR_SKILL.PASS, "Escudochim", 5U, "Pode usar qualquer tipo de escudo"),
        charskill(CHAR_SKILL.TANK, "Amortecimento Lipidico", 5U, "Voce usa a banha pra absorver 25% de impactos"),
        charskill(CHAR_SKILL.TANK, "Musculacao", 10U, "Voce ativa seus acidos laticos e fica 10 minutos absorvendo 20% de qualquer dano"),
        charskill(CHAR_SKILL.DPS, "Empurrao", 20U, "Voce empurra um alvo e deixa ele tonto por 5 segundos, 50 de dano"),
        charskill(CHAR_SKILL.PASS, "Hardcore", 20U, "Todas suas habilidades protetivas sao amplificadas em 5%"),
        charskill(CHAR_SKILL.TANK, "Martir", 50U, "Voce absorve 30% do dano causado aos seus companheiros por 5 minutos"),
        charskill(CHAR_SKILL.PASS, "Ascenção", 120U, "Voce se torna deus.")
),
"Excelente para resistir ataques e proteger seu grupo de atacantes.", R.drawable.class_logo_tank
    )

val CLASS_SNIPER = charclass( "Atirador",
    listOf<charskill>(
        charskill(CHAR_SKILL.DPS, "Soco", 1U, "Usar o punho para machucar, 1 de dano"),
        charskill(CHAR_SKILL.PASS, "Olho de aguia", 1U, "Voce possui uma visão acima do normal e consegue acertar +10% das vezes"),
        charskill(CHAR_SKILL.DPS, "Tiro Focado", 5U, "Segura uma arma por 5 segundos e atira com foco, causando 2x de dano"),
        charskill(CHAR_SKILL.PASS, "Pistolachim", 5U, "Pode usar qualquer tipo de pistola"),
        charskill(CHAR_SKILL.PASS, "Sniperchim", 10U, "Pode usar qualquer tipo de sniper"),
        charskill(CHAR_SKILL.DPS, "Hollywooded", 10U, "Voce espera 6 segundos para atirar em 3 alvos simultaneamente"),
        charskill(CHAR_SKILL.PASS, "Hardcore", 20U, "Todas suas habilidades de dano sao amplificadas em 5%"),
        charskill(CHAR_SKILL.DPS, "Headshot", 50U, "Voce estoura o coco de um alvo, 10x de dano"),
        charskill(CHAR_SKILL.PASS, "Ascenção", 120U, "Voce se torna deus.")
    ),
    "Tiros certeiros que machucam bem, perfeito para focar em danificar alvos.", R.drawable.class_logo_sniper
)

val CLASS_MEDIC = charclass( "Cirurgião",
    listOf<charskill>(
        charskill(CHAR_SKILL.DPS, "Soco", 1U, "Usar o punho para machucar, 1 de dano"),
        charskill(CHAR_SKILL.HEAL, "Olha", 1U, "a cara desse moleque"),
        charskill(CHAR_SKILL.HEAL, "Da", 5U, "não"),
        charskill(CHAR_SKILL.PASS, "Ascenção", 120U, "Voce se torna deus.")
    ),
    "Recomenda-se para quem gosta de ficar na arquibancada para dar suporte ao seus grupos, principalmente para recuperar HP.", R.drawable.class_logo_medic
)

val CLASS_SOLDIER = charclass("Botanista",
    listOf<charskill>(
        charskill(CHAR_SKILL.DPS, "Tapa", 1U, "Usar a palma da mão para machucar, 1 de dano"),
        charskill(CHAR_SKILL.HEAL, "Apreciar a vida", 1U, "Voce aprecia a vida e recupera 1 HP"),
        charskill(CHAR_SKILL.PASS, "Viciado", 1U, "Voce precisa fumar um [Baseado] a cada 1 hora ou perde 10% de sua HP maxima"),
        charskill(CHAR_SKILL.HEAL, "Brownie", 5U, "Voce cria um brownie de cannabis e come le, recuperando 15% de seu HP"),
        charskill(CHAR_SKILL.TANK, "Brisada", 5U, "Voce começa a dormir e resisti todos ataques incapacitantes por 30 segundos"),
        charskill(CHAR_SKILL.PASS, "Abstinencia", 5U, "Os efeitos de habilidades negativas são reduzidas em 50%"),
        charskill(CHAR_SKILL.DPS, "Crise de ansiedade", 10U, "Voce entra em panico e se joga em um alvo, causando 100 de dano"),
        charskill(CHAR_SKILL.PASS, "Abstinencia total", 20U, "Os efeitos de habilidades negativas são reduzidas em 100%"),
        charskill(CHAR_SKILL.DPS, "Cancer de 2a mão", 50U, "Voce cobre um inimigo com fumaça toxica e diminui a expectativa de vida do inimigo."),
        charskill(CHAR_SKILL.HEAL, "Virar planta", 50U, "Voce se torna uma planta de canabis e usa a fotosintese para recuperar 25% de sua vida a cada 10 segundos"),
        charskill(CHAR_SKILL.TANK, "Alcoolismo", 50U, "Voce mistura maconha e alcool para resistir 90% de qualquer dano por 10 segundos."),
        charskill(CHAR_SKILL.PASS, "Ascenção", 120U, "Voce se torna deus.")
    ),
    "Infinitamente no mundo da lua, é um equilibrio perfeito dos aspectos principais das outras classes.", R.drawable.class_logo_weedsoldier
)