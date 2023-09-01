app["extra"] = {

    myManFeeling : 0,
    record2Set: "",
    flamieDiv : $("#flamie"),


    //foguinho
    getFlamieSprite : function(emotion) {
        //pega sprite dependendo de emotion
        let anim = null
        switch (emotion) {
            case -2:
                anim = "fl_snooze.gif"; break;
            case -1:
                anim = "fl_reading.gif"; break;
            case 0:
                anim = "fl_bored.gif"; break;
            case 1: 
                anim = "fl_happy.gif"; break;
            case 2:
                anim = "fl_smooth.gif"; break;
            case 3:
            case 4:
                anim = "fl_disco.gif"; break;
            default:
                anim = "fl_wat.png"; break;
        }

        return "url(\"flamie/" + anim + "\")"
    },

    changeFlamieSprite : function(add=0, emotion=this.myManFeeling) {
        this.calcFlamieEmotion(add) 
        this.flamieDiv.css( "background-image", this.getFlamieSprite( Math.floor( this.myManFeeling ) ) )
    },

    calcFlamieEmotion : function(add) {
        this.myManFeeling = Math.max( Math.min(this.myManFeeling + add, 4), -2)
    },

    //placar
    //jogos com quantidade de cartas, imagens e cartas por par diferentes contem placares separados

    recordLoad : function(card_dupe, card_max, imgs) {
        //carrega placar de local storage
        const scores = localStorage.getItem( [card_dupe, card_max, imgs].join("|") )

        const score_tab = []
        if (scores != null) {
            for (score of scores.split("/")) {
                score = score.split("|")
                score_tab.push([score[0], score[1]])
            }

            score_tab.sort( (a,b) => a[0] - b[0] )

            return score_tab
        }

        return null

    },

    recordSave : function(recordType, time, name) {
        //salva placar no local storage
        const scores = localStorage.getItem( recordType )
        const scores_tab = []
        let added = false

        if (scores != null) {
            for (score of scores.split("/")) {
                score = score.split("|")
                if (score[1] == name) {
                    score[0] = time < score[0] ? time : score[0]
                    added = true
                }
                scores_tab.push(score.join("|"))
            }
        }

        if (!added) {
            scores_tab.push( time + "|" + name )
        }

        localStorage.setItem(recordType, scores_tab.join("/"))
    }, 

    recordParse : function(records) {
        //torna array de placar em string
        if (records == null) {
            return "Nenhum recorde encontrado!"
        }

        let append = ""
        
        for (record of records) {
            append += `<p>${record[0]} segundos - ${record[1]}</p>`
        }

        return append
    },

    recordHTML : function(card_dupe, card_max, imgs) {
        //coloca informacao do placar no html
        document.getElementById("score_title").innerHTML = `Scoreboard<br>(${card_dupe * imgs} cartas total X ${card_max} cartas no par)`
        document.getElementById("scoreboard").innerHTML = this.recordParse( this.recordLoad(card_dupe, card_max, imgs) )
    },

    recordString : function(str) {
        //mesma coisa que recordHTML so que com uma string formatada em "card_dupe|card_max|imgs"
        str = str.split("|")
        this.recordHTML(str[0], str[1], str[2])
    }

}