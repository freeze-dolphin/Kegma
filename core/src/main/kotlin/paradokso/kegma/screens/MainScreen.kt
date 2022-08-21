package paradokso.kegma.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use
import ktx.log.logger

class MainScreen : KtxScreen {

    private val image = Texture("graphics/player.png".toInternalFile())
    //private val image = Texture("logo.png".toInternalFile(), true).apply { setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear) }
    private val batch = SpriteBatch()

    override fun show() {
        log.debug { "shown" }
    }

    override fun render(delta: Float) {
        batch.use {
            it.draw(image, 0f, 0f)
        }
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }

    companion object {
        private val log = logger<MainScreen>()
    }

}
