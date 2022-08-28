package paradokso.kegma.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.github.quillraven.fleks.world
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.log.logger
import paradokso.kegma.components.ImageComponent
import paradokso.kegma.components.ImageComponent.Companion.ImageComponentListener
import paradokso.kegma.systems.RenderSystem

class MainScreen : KtxScreen {

    private val texture = Texture("graphics/player.png".toInternalFile())

    //private val image = Texture("logo.png".toInternalFile(), true).apply { setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear) }
    private val stage = Stage(ExtendViewport(4f, 3f))
    private val world = world {
        injectables {
            add(stage)
        }

        components {
            add<ImageComponentListener>()
        }

        systems {
            add<RenderSystem>()
        }
    }

    override fun show() {
        log.debug { "shown" }

        world.entity {
            add<ImageComponent> {
                image = Image(texture).apply {
                    setSize(4f, 4f)
                }
            }
        }
    }

    override fun render(delta: Float) {
        world.update(delta)
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        texture.disposeSafely()
        stage.disposeSafely()
        world.dispose()
    }

    companion object {
        private val log = logger<MainScreen>()
    }

}
