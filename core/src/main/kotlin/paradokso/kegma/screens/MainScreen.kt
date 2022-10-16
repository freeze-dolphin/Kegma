package paradokso.kegma.screens

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
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
import paradokso.kegma.systems.AnimationSystem

class MainScreen : KtxScreen {

    private val atlas = TextureAtlas("graphics/gameObjects.atlas".toInternalFile())

    private val stage = Stage(ExtendViewport(16f, 9f))
    private val world = world {
        injectables {
            add(stage)
            add(atlas)
        }
        components { add<ImageComponentListener>() }
        systems {
            add<AnimationSystem>()
            add<RenderSystem>()
        }
    }

    override fun show() {
        log.debug { "shown" }

        world.entity {
            add<ImageComponent> {
                image =
                        Image(TextureRegion(atlas.findRegion("002-Fighter02"), 0, 0, 32, 48))
                                .apply {
                                    setSize(1f, 1.5f)
                                    setPosition(1f, 1f)
                                }
            }
        }
        world.entity {
            add<ImageComponent> {
                image =
                        Image(TextureRegion(atlas.findRegion("039-Mage07"), 0, 0, 32, 48)).apply {
                            setSize(1f, 1.5f)
                            setPosition(8f, 1f)
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
        atlas.disposeSafely()
        stage.disposeSafely()
        world.dispose()
    }

    companion object {
        private val log = logger<MainScreen>()
    }
}
