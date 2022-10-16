package paradokso.kegma.systems

import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.ComponentMapper
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import paradokso.kegma.components.AnimationComponent
import paradokso.kegma.components.ImageComponent
import paradokso.kegma.Kegma
import ktx.app.gdxError

@AllOf([AnimationComponent::class, ImageComponent::class])
class AnimationSystem(
    private val textureAtlas: TextureAtlas,
    private val animationCmps: ComponentMapper<AnimationComponent>,
    private val imageCmps: ComponentMapper<ImageComponent>
) : IteratingSystem () {

    private val cachedAnimations = mutableMapOf<String, Animation<TextureRegionDrawable>>()

    override fun onTickEntity(entity: Entity) {
        val aniCmp = animationCmps[entity]
        if (aniCmp.nextAnimation == AnimationComponent.NO_ANIMATION) {
            aniCmp.stateTime += deltaTime
            aniCmp.animation.playMode = aniCmp.playMode

            aniCmp.animation.getKeyFrame(aniCmp.stateTime)
        } else {
            aniCmp.animation = animation(aniCmp.nextAnimation)
            aniCmp.stateTime = 0f
        }

        aniCmp.animation.playMode = aniCmp.playMode
        imageCmps[entity].image.drawable = aniCmp.animation.getKeyFrame(aniCmp.stateTime)
    }

    private fun animation(aniKeyPath: String): Animation<TextureRegionDrawable> {
        return cachedAnimations.getOrPut(aniKeyPath) {
            val regions = textureAtlas.findRegions(aniKeyPath)

            if (regions.isEmpty) {
                gdxError("There are no texture regions for $aniKeyPath!")
            }

            Animation(0.25f, regions.map { TextureRegionDrawable(it) })
        }
    }
}
}
