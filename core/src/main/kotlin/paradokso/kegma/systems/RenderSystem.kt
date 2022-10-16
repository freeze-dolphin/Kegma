package paradokso.kegma.systems

import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.collection.compareEntity
import paradokso.kegma.components.ImageComponent

@AllOf([ImageComponent::class])
class RenderSystem(
    private val stage: Stage,
    private val imageCmps: ComponentMapper<ImageComponent>
) : IteratingSystem(
    comparator = compareEntity { e1, e2 ->
        imageCmps[e1].compareTo(imageCmps[e2])
    }
) {

    override fun onTick() {
        super.onTick()

        with(stage) {
            viewport.apply()
            act(deltaTime)
            draw()
        }
    }

    override fun onTickEntity(entity: Entity) {
        imageCmps[entity].image.toFront()
    }

}
