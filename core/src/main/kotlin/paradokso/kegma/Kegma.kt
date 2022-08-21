package paradokso.kegma

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import ktx.app.KtxGame
import ktx.app.KtxScreen
import paradokso.kegma.screens.MainScreen

class Kegma : KtxGame<KtxScreen>() {
    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        addScreen(MainScreen())
        setScreen<MainScreen>()
    }
}
