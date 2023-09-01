app.inicio = function() {

    if (this.game != null && this.extra != null) {

        var imagens = ['img/facebook.png','img/android.png', 'img/chrome.png','img/firefox.png','img/html5.png','img/googleplus.png','img/twitter.png','img/windows.png','img/cross.png'];
        
        
        $("#btn_start").click( () => this.game.gameStart( imagens ) )

        this.extra.changeFlamieSprite()
        this.extra.flamieDiv.click( () => $("#game_options").slideToggle() )

        $("#option_add_img").change(() => this.options.loadImgs())
    }
    else {
        console.log("Não podê carregar o aplicativo!")
    }
}


app.inicio();