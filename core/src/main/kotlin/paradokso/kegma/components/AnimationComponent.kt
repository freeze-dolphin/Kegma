package paradokso.kegma.components

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

enum class AnimationType {
    RUN_UP,
    RUN_DOWN,
    RUN_LEFT,
    RUN_RIGHT,
    DEATH,
    OPEN;

    var atlasKey: String = this.toString().lowercase()
}

data class AnimationComponent(
        var atlasKey: String = "",
        var stateTime: Float = 0f,
        var playMode: Animation.PlayMode = Animation.PlayMode.LOOP
) {
    lateinit var animation: Animation<TextureRegionDrawable>
    var nextAnimation: String = NO_ANIMATION

    fun nextAnimation(atlasKey: String, type: AnimationType) {
        this.atlasKey = atlasKey
        nextAnimation = "$atlasKey/${type.atlasKey}"
    }

    companion object {
        val NO_ANIMATION = ""
    }
}
